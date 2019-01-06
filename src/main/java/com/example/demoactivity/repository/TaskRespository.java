package com.example.demoactivity.repository;

import com.example.demoactivity.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRespository extends CrudRepository<Task,Long> {

    List<Task> findAll();
}
