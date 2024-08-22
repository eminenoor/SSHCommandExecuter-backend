package com.kron.ssh.controller;

import com.kron.ssh.entity.Command;
import com.kron.ssh.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commands")
public class CommandController {

    @Autowired
    private CommandService commandService;

    @GetMapping
    public List<Command> getAllCommands() {
        return commandService.getAllCommands();
    }

    @GetMapping("/{id}")
    public Command getCommandById(@PathVariable Long id) {
        return commandService.getCommandById(id);
    }

    @PostMapping
    public Command createCommand(@RequestBody Command command) {
        return commandService.saveCommand(command);
    }

    @PutMapping("/{id}")
    public Command updateCommand(@PathVariable Long id, @RequestBody Command command) {
        command.setId(id);
        return commandService.saveCommand(command);
    }

    @DeleteMapping("/{id}")
    public void deleteCommand(@PathVariable Long id) {
        commandService.deleteCommand(id);
    }
}
