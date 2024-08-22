package com.kron.ssh.repository;

import com.kron.ssh.entity.Command;
import org.springframework.data.repository.CrudRepository;

public interface CommandRepository extends CrudRepository<Command, Long> {
}
