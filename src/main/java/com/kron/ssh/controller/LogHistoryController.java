package com.kron.ssh.controller;

import com.kron.ssh.entity.LogHistory;
import com.kron.ssh.service.LogHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LogHistoryController {

    private final LogHistoryService logHistoryService;

    @GetMapping("/api/v1/logs")
    public List<LogHistory> getAllLogs() {
        return logHistoryService.getAllLogs();
    }

    @PostMapping("/api/v1/logs")
    public LogHistory addLog(
            @RequestParam String ip,
            @RequestParam String command,
            @RequestParam String description
    ) {
        return logHistoryService.addLog(ip, command, description);
    }

    @GetMapping("/api/v1/logs/search")
    public List<LogHistory> searchLogs(@RequestParam String searchTerm) {
        return logHistoryService.searchLogs(searchTerm);
    }
}
