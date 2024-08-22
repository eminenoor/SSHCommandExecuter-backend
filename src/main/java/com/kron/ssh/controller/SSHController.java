package com.kron.ssh.controller;

import com.kron.ssh.service.SSHCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ssh")
public class SSHController {

    @Autowired
    private SSHCommandService sshCommandService;

    @PostMapping("/connect/{deviceId}")
    public String connectToDevice(@PathVariable Long deviceId, @RequestParam(required = false) String command) {
        return sshCommandService.executeCommandOnDevice(deviceId, command);
    }
}
