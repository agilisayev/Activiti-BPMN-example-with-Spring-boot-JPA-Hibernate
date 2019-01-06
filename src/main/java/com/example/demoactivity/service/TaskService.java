package com.example.demoactivity.service;

import com.example.demoactivity.model.Task;
import com.example.demoactivity.repository.TaskRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {


   private  final TaskRespository taskRespository;

   @Autowired
    public TaskService(TaskRespository taskRespository) {
        this.taskRespository = taskRespository;
    }

    public Task findById(Long id){
        return taskRespository.findOne(id);
    }

    public Task save(Task task){
        return taskRespository.save(task);
    }

    public List<Task> findAll(){
        return taskRespository.findAll();
    }
}
