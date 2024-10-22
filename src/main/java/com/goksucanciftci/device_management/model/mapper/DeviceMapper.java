package com.goksucanciftci.device_management.model.mapper;

import com.goksucanciftci.device_management.model.dto.DeviceDTO;
import com.goksucanciftci.device_management.model.entity.Device;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceMapper {

    public DeviceDTO asDTO(Device device) {
        return DeviceDTO.builder()
                .id(device.getId())
                .name(device.getName())
                .brand(device.getBrand())
                .creationTime(device.getCreationTime())
                .version(device.getVersion())
                .build();
    }

    public Device asEntity(DeviceDTO deviceDTO) {
        return Device.builder()
                .id(deviceDTO.getId())
                .name(deviceDTO.getName())
                .brand(deviceDTO.getBrand())
                .creationTime(deviceDTO.getCreationTime())
                .version(deviceDTO.getVersion())
                .build();
    }


    public List<DeviceDTO> asDTOList(List<Device> devices) {
        return devices.stream()
                .map(this::asDTO)
                .collect(Collectors.toList());
    }
}
