package com.example.demoactivity.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

@Service
public class ProcessPlanService {

    @ActivitiServiceTask
    public void finishPlanProcess(DelegateExecution execution) {
    }

}
