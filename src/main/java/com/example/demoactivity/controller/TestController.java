package com.example.demoactivity.controller;

import com.example.demoactivity.model.Users;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/rest1")
public class TestController {

    /*
    if you want open bpmnfile try Eclipse Luna and install Activiti  then open file.
    */
    private final TaskService taskService;

    @Autowired
    public TestController(TaskService taskService) {
        this.taskService = taskService;
    }

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

        Users initiator = new Users();
        initiator.setUsername("Agil isayev");
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
    public String next(@PathVariable String id, @PathVariable Boolean line) {
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

    @GetMapping("/next1/{id}/{line1}")
    public String next1(@PathVariable String id, @PathVariable Boolean line) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().taskId(id).singleResult();
        if (task == null) {
            throw new NullPointerException("NULL TASK");
        }
        Map<String, Object> variables = new HashMap<>();
        variables.put("controlOrderJuristBPS07", line);
        variables.put("sendOrderCourtBPS07", line);
        variables.put("assigneeMufettish", "assigneeMufettish");
        variables.put("assigneeMuhafizeSobeMuduru", "assigneeMuhafizeSobeMuduru");
        variables.put("addOrderPain", false);
        variables.put("addOrderEnding", true);
        taskService.complete(task.getId(), variables);
        return "OK";
    }

    @GetMapping("/next2/{id}/{line2}")
    public String next2(@PathVariable String id, @PathVariable Boolean line) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().taskId(id).singleResult();
        if (task == null) {
            throw new NullPointerException("NULL TASK");
        }
        Map<String, Object> variables = new HashMap<>();
        variables.put("controlOrderJuristBPS07", line);
        variables.put("sendOrderCourtBPS07", line);
        variables.put("sendWarningBPS07", line);
        variables.put("add30DayOrderBPS07", line);
        variables.put("sendWarningBPS07", line);
        variables.put("cancelCourtDecision", line);
        variables.put("keepInForce", line);
        variables.put("payPenalty", line);
        variables.put("sendDsmfPenaltyOrderBPS07", line);

        variables.put("assigneeMufettish", "assigneeMufettish");
        variables.put("assigneeMuhafizeSobeMuduru", "assigneeMuhafizeSobeMuduru");
        variables.put("addOrderPain", false);
        variables.put("addOrderEnding", true);

        taskService.complete(task.getId(), variables);
        return "OK";
    }

}
