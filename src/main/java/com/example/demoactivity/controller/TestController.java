package com.example.demoactivity.controller;

import com.example.demoactivity.service.User;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/start")
    public String start() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .name("MyServiceTask")
                .category("planProcess")
                .addClasspathResource("diaqram/PlanProcess -NEW.bpmn")
                .deploy();

        Map<String, Object> variables = new HashMap<>();

        User initiator=new User();
        initiator.setUsername("asdaasas");

        variables.put("initiator", initiator);
        variables.put("assigneeHuquqsunasSobeMuduru", "assigneeHuquqsunasSobeMuduru");
        variables.put("assigneeMuhafizeSobeMuduru", "assigneeMuhafizeSobeMuduru");
        variables.put("penaltyStateEnum", "112003");

        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("planProcess-BPS07", variables);
        System.out.println("Started Process instance id " + processInstance.getProcessInstanceId());
        processEngine.getRuntimeService().addUserIdentityLink(processInstance.getId(), "user", IdentityLinkType.STARTER);
        System.out.println("count=" + processEngine.getRuntimeService().createProcessInstanceQuery().count());
        TaskService taskServices = processEngine.getTaskService();
        return null;
    }

    @GetMapping("/next/{id}/{line}")
    public String test(@PathVariable String id,@PathVariable Boolean line) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().taskId(id).singleResult();
        if (task == null) {
            throw new NullPointerException("NULL TASK");
        }
        Map<String, Object> variables = new HashMap<>();
        variables.put(task.getTaskDefinitionKey(), line);
        taskService.complete(task.getId(), variables);
        return "OK";
    }

}
