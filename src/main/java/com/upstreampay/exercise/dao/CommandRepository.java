package com.upstreampay.exercise.dao;

import com.upstreampay.exercise.model.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends JpaRepository<Command,Integer> {
}
