package com.kron.ssh.service;

import com.kron.ssh.entity.Device;
import com.kron.ssh.exception.ResourceNotFoundException;
import com.kron.ssh.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> getAllDevices() {
        return StreamSupport.stream(deviceRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Device getDeviceById(Long id) {
        return deviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Device found with id: " + id));
    }

    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }
}