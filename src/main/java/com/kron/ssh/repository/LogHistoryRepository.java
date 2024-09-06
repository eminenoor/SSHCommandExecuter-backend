package com.kron.ssh.repository;

import com.kron.ssh.entity.LogHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LogHistoryRepository extends JpaRepository<LogHistory, Long> {
    @Query("SELECT l FROM LogHistory l WHERE l.ip LIKE %:searchTerm% OR l.command LIKE %:searchTerm% OR l.description LIKE %:searchTerm%")
    List<LogHistory> searchLogs(@Param("searchTerm") String searchTerm);
}
