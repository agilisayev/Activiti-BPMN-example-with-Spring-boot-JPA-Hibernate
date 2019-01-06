package com.example.demoactivity.controller;

import com.example.demoactivity.service.User;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/act")
public class WorkFlowRestController {

    @GetMapping("/start")
    public String start() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .name("MyServiceTask")
                .category("planProcess")
                .addClasspathResource("diaqram/PlanProcess.bpmn")
                .deploy();
        Map<String, Object> variables = new HashMap<>();

        User initiator = new User();
        initiator.setUsername("Agil Isayev");
        variables.put("initiator", initiator);
        variables.put("prepareNotificationBPS07", "prepareNotificationBPS07");
        variables.put("prepareProtokolOrderMuhafizeSobeMuduruBPS07", "prepareProtokolOrderMuhafizeSobeMuduruBPS07");
        variables.put("documentOrderHuquqSobeMuduruBPS07", "documentOrderHuquqSobeMuduruBPS07");
        variables.put("approveOrderMuhafizeSobeMuduruBPS07", "approveOrderMuhafizeSobeMuduruBPS07");



        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("planProcess-BPS07", variables);
        System.out.println("Started Process instance id " + processInstance.getProcessInstanceId());
        processEngine.getRuntimeService().addUserIdentityLink(processInstance.getId(), "user", IdentityLinkType.STARTER);
        System.out.println("count=" + processEngine.getRuntimeService().createProcessInstanceQuery().count());
        TaskService taskServices = processEngine.getTaskService();
        return null;
    }

    @GetMapping("/next/{id}/{planType}")
    public String test(@PathVariable String id, @PathVariable Boolean planType) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().taskId(id).singleResult();
        if (task == null) {
            throw new NullPointerException("NULL TASK");
        }
        Map<String, Object> variables = new HashMap<>();
        variables.put(task.getTaskDefinitionKey(), planType);
        taskService.complete(task.getId(), variables);
        return "OK";
    }
}

//planType==false
