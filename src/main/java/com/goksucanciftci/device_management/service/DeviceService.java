package com.goksucanciftci.device_management.service;

import com.goksucanciftci.device_management.model.dto.DeviceDTO;

import java.util.List;
import java.util.Optional;

public interface DeviceService {

    DeviceDTO save(DeviceDTO deviceDTO);

    Optional<DeviceDTO> findById(Long id);

    List<DeviceDTO> findAll();

    Optional<DeviceDTO> update(Long id, DeviceDTO deviceDTO);

    void deleteById(Long id);

    List<DeviceDTO> findByBrand(String brand);
}