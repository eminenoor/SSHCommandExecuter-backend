package com.kron.ssh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "log_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;

    private String command;

    private LocalDateTime dateTime;

    private String description;
}
