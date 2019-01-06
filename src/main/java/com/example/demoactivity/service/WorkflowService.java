package com.example.demoactivity.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkflowService {

    @ActivitiServiceTask
    public void taskListener(DelegateExecution execution) {
        ExecutionEntity executionEntity = (ExecutionEntity) execution;
    }
}
