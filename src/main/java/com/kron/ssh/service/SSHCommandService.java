package com.kron.ssh.service;

import com.kron.ssh.entity.Device;
import com.kron.ssh.entity.LogHistory;
import com.kron.ssh.exception.ResourceNotFoundException;
import com.kron.ssh.repository.DeviceRepository;
import com.kron.ssh.repository.LogHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SSHCommandService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private LogHistoryRepository logHistoryRepository;

    @Autowired
    private SSHService sshService;

    public String executeCommandOnDevice(Long deviceId, String command) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found"));

        String result = "";

        if (device != null) {
            try {
                result = sshService.connectAndExecuteCommand(device, command);

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String username = authentication.getName();

                LogHistory log = new LogHistory();
                log.setIp(device.getIp());
                log.setCommand(command);
                log.setDateTime(LocalDateTime.now());
                log.setDescription(username + " executed command and got the result as: " + result);

                logHistoryRepository.save(log);

                return result;
            } catch (Exception e) {
                return "Failed to execute the command: " + e.getMessage();
            }
        } else {
            return "Device not found";
        }
    }
}
