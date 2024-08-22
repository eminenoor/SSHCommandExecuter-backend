package com.kron.ssh.service;

import com.kron.ssh.entity.Device;
import com.kron.ssh.exception.ResourceNotFoundException;
import com.kron.ssh.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SSHCommandService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private SSHService sshService;

    public String executeCommandOnDevice(Long deviceId, String command) {
        Device device = deviceRepository.findById(deviceId).orElseThrow(() -> new ResourceNotFoundException("Device not found"));
        if (device != null) {
            return sshService.connectAndExecuteCommand(device, command);
        } else {
            return "Device not found";
        }
    }
}
