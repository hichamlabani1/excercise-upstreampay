package com.upstreampay.exercise.service;

import com.upstreampay.exercise.dao.CommandRepository;
import com.upstreampay.exercise.model.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandService {

    private final CommandRepository commandRepository;

    public List<Command> getAllcommands(){
        return commandRepository.findAll();
    }


}
