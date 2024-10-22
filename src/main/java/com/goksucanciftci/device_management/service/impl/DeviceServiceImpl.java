package com.goksucanciftci.device_management.service.impl;

import com.goksucanciftci.device_management.model.dto.DeviceDTO;
import com.goksucanciftci.device_management.model.entity.Device;
import com.goksucanciftci.device_management.model.mapper.DeviceMapper;
import com.goksucanciftci.device_management.repository.DeviceRepository;
import com.goksucanciftci.device_management.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {


    final DeviceMapper deviceMapper;

    final DeviceRepository deviceRepository;

    @Override
    @Transactional
    @CacheEvict(value = "devices", key = "#deviceDTO.id")
    public DeviceDTO save(DeviceDTO deviceDTO) {
        Device device = deviceMapper.asEntity(deviceDTO);
        device = deviceRepository.save(device);
        return deviceMapper.asDTO(device);
    }

    @Override
    @Cacheable(value = "devices", key = "#id")
    public Optional<DeviceDTO> findById(Long id) {
        return deviceRepository.findById(String.valueOf(id))
                .map(deviceMapper::asDTO);
    }

    @Override
    @Cacheable(value = "devices")
    public List<DeviceDTO> findAll() {
        List<Device> devices = deviceRepository.findAll();
        return deviceMapper.asDTOList(devices);
    }

    @Override
    @Transactional
    @CacheEvict(value = "devices", key = "#deviceDTO.id")
    public Optional<DeviceDTO> update(Long id, DeviceDTO deviceDTO) {
        return deviceRepository.findById(String.valueOf(id))
                .map(device -> {
                    device.setName(deviceDTO.getName());
                    device.setBrand(deviceDTO.getBrand());
                    Device updatedDevice = deviceRepository.save(device);
                    return deviceMapper.asDTO(updatedDevice);
                });
    }

    @Override
    @CacheEvict(value = "devices", key = "#id")
    public void deleteById(Long id) {
        deviceRepository.findById(String.valueOf(id))
                .ifPresent(deviceRepository::delete);
    }

    @Override
    @Cacheable(value = "devices", key = "#brand")
    public List<DeviceDTO> findByBrand(String brand) {
        List<Device> devices = deviceRepository.findByBrandContainingIgnoreCase(brand);
        return deviceMapper.asDTOList(devices);
    }
}
