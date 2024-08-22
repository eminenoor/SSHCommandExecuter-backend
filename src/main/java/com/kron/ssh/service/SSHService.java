package com.kron.ssh.service;

import com.jcraft.jsch.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import com.kron.ssh.entity.Device;

@Service
public class SSHService {

    public String connectAndExecuteCommand(Device device, String command) {
        StringBuilder result = new StringBuilder();
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(device.getUserName(), device.getIp(), 22);
            session.setPassword(device.getPassword());

            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            System.out.println("Connected to " + device.getIp());

            if (command != null && !command.isEmpty()) {
                ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
                channelExec.setCommand(command);

                channelExec.setErrStream(System.err);

                InputStream in = channelExec.getInputStream();
                channelExec.connect();

                byte[] tmp = new byte[1024];
                while (true) {
                    while (in.available() > 0) {
                        int i = in.read(tmp, 0, 1024);
                        if (i < 0) break;
                        result.append(new String(tmp, 0, i));
                    }
                    if (channelExec.isClosed()) {
                        if (in.available() > 0) continue;
                        result.append("exit-status: ").append(channelExec.getExitStatus());
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                }

                channelExec.disconnect();
            }

            session.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            result.append("Error: ").append(e.getMessage());
        }
        return result.toString();
    }
}
