package com.kron.ssh.service;

import com.kron.ssh.entity.Command;
import com.kron.ssh.exception.ResourceNotFoundException;
import com.kron.ssh.repository.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CommandService {
    @Autowired
    private CommandRepository commandRepository;

    public List<Command> getAllCommands() {
        return StreamSupport.stream(commandRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Command getCommandById(Long id) {
        return commandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No command found with id: " + id));
    }

    public Command saveCommand(Command command) {
        return commandRepository.save(command);
    }

    public void deleteCommand(Long id) {
        commandRepository.deleteById(id);
    }
}