package com.goksucanciftci.device_management.repository;

import com.goksucanciftci.device_management.model.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {

    List<Device> findByBrandContainingIgnoreCase (String brand);
}
