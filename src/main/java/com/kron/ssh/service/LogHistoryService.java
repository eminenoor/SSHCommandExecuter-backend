package com.kron.ssh.service;

import com.kron.ssh.entity.LogHistory;
import com.kron.ssh.repository.LogHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogHistoryService {

    private final LogHistoryRepository logHistoryRepository;

    public LogHistory addLog(String ip, String command, String description) {
        LogHistory log = new LogHistory();
        log.setIp(ip);
        log.setCommand(command);
        log.setDateTime(LocalDateTime.now());
        log.setDescription(description);
        return logHistoryRepository.save(log);
    }

    public List<LogHistory> getAllLogs() {
        return logHistoryRepository.findAll();
    }

    public List<LogHistory> searchLogs(String searchTerm) {
        return logHistoryRepository.searchLogs(searchTerm);
    }
}
