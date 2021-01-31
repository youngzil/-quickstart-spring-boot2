package org.quickstart.springboot.activiti;

import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quickstart.springboot.activiti.config.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * activiti7
 * 主要是对以前项目做了一些封装
 * ProcessRuntime taskRuntime:本质还是以前的各种service;
 * 版本模糊，个版本都有一定的bug。团队实力有待考察。
 */

@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureWebClient(registerRestTemplate = true)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {RestTemplate.class, ProtobufHttpMessageConverter.class})
//@ActiveProfiles("dev")
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BootActivitiApplicationTests {

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private TaskRuntime taskRuntime;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private RepositoryService repositoryService;

    @Resource
    private TaskService taskService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private HistoryService historyService;

    @Test // 部署流程，根据bpmn文件，创建一个新的流程
    public void deploy() {
        securityUtil.logInAs("salaboy");
        String bpmnName = "MyProcess";
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().name("请假流程");
        Deployment
        deployment = deploymentBuilder.addClasspathResource("processes/" + bpmnName + ".bpmn")//添加bpmn资源
            .addClasspathResource("processes/" + bpmnName + ".png")//
            //                .name("请假申请单流程")
            .deploy();

        System.out.println(deployment);
    }

    @Test // 远程外部BPMN
    public void deploy2() throws IOException {
        securityUtil.logInAs("salaboy");
        Deployment deployment = null;
        InputStream in = new FileInputStream(
            new File("C:\\Users\\飞牛\\git\\SpringBoot2_Activiti7\\src\\main\\resources\\processes\\leaveProcess.zip"));
        ZipInputStream zipInputStream = new ZipInputStream(in);
        deployment = repositoryService.createDeployment().name("请假流程2")
            // 指定zip格式的文件完成部署
            .addZipInputStream(zipInputStream).deploy();// 完成部署
        zipInputStream.close();

        System.out.println(deployment);
    }

    @Test // 查看流程
    public void queryDeploy() {
        securityUtil.logInAs("salaboy");
        Page processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        System.err.println("已部署的流程个数：" + processDefinitionPage.getTotalItems());
        for (Object obj : processDefinitionPage.getContent()) {
            System.err.println("流程定义：" + obj);
        }

        // 查询已有的流程
        System.out.println("-------------------------------------------");
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition p : processDefinitions) {
            System.out.println(p.getDeploymentId());
            System.out.println(p);
        }
    }

    /**
     * 删除已有的流程
     */
    @Test
    public void deleteDeploy() {
        //这个id是DeploymentId
        repositoryService.deleteDeployment("6c41300f-aee0-11ea-9969-00ff0d0b7f9d");

        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition p : processDefinitions) {
            System.out.println("DeploymentId:" + p.getDeploymentId());
            System.out.println(p);
        }
    }

    @Test // 启动一个流程实例
    public void startProcessInstance() {
        securityUtil.logInAs("salaboy");

        System.out.println(
            "开启流程前：Number of process definitions : " + repositoryService.createProcessDefinitionQuery().count());
        System.out.println("开启流程前：Number of tasks : " + taskService.createTaskQuery().count());

        ProcessInstance processInstance =
            processRuntime.start(ProcessPayloadBuilder.start().withProcessDefinitionKey("myProcess_1").build());
        System.err.println("流程实例ID：" + processInstance.getId());

        //        runtimeService.startProcessInstanceByKey("myProcess_1");

        System.out.println(
            "开启流程后：Number of process definitions : " + repositoryService.createProcessDefinitionQuery().count());
        System.out.println("开启流程后：Number of tasks : " + taskService.createTaskQuery().count());

        //        System.out.println("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());
        //        org.activiti.engine.runtime.ProcessInstance processInstance2 = runtimeService.startProcessInstanceByKey("myProcess_1");
        //        System.out.println("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());

    }

    @Test // 执行流程
    public void testTask() {
        securityUtil.logInAs("salaboy");
        Page<Task> page = taskRuntime.tasks(Pageable.of(0, 10));
        if (page.getTotalItems() > 0) {
            for (Task task : page.getContent()) {
                System.err.println("当前任务有1：" + task);
                // 拾取
                taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(task.getId()).build());
                // 执行
                taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(task.getId()).build());
            }
        } else {
            System.err.println("没的任务1");
        }

        page = taskRuntime.tasks(Pageable.of(0, 10));
        if (page.getTotalItems() > 0) {
            for (Task task : page.getContent()) {
                System.err.println("当前任务有2：" + task);
            }
        } else {
            System.err.println("没的任务2");
        }
    }

    /**
     * 传递参数、读取参数
     * 查询用户的任务列表
     */
    @Test
    public void taskQuery() {

        securityUtil.logInAs("salaboy");

        //根据流程定义的key,负责人assignee来实现当前用户的任务列表查询
        List<org.activiti.engine.task.Task> list = taskService.createTaskQuery()//
            .processDefinitionKey("myProcess_1")//
            //            .taskCandidateOrAssigned("teamleader")//
            //            .taskAssignee("john")//
            .list();

        if (list == null || list.isEmpty()) {
            System.err.println("没有任务");
            return;
        }

        for (org.activiti.engine.task.Task task : list) {
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("employeeName", "Kermit");
            variables.put("numberOfDays", new Integer(4));
            variables.put("vacationMotivation", "I'm really tired!");

            taskService.setVariable(task.getId(), "参数", variables);
        }

        for (org.activiti.engine.task.Task task : list) {

            System.out.println("任务ID:" + task.getId());
            System.out.println("任务名称:" + task.getName());
            System.out.println("任务的创建时间:" + task.getCreateTime());
            System.out.println("任务的办理人:" + task.getAssignee());
            System.out.println("流程实例ID：" + task.getProcessInstanceId());
            System.out.println("执行对象ID:" + task.getExecutionId());
            System.out.println("流程定义ID:" + task.getProcessDefinitionId());
            System.out.println("getOwner:" + task.getOwner());
            System.out.println("getCategory:" + task.getCategory());
            System.out.println("getDescription:" + task.getDescription());
            System.out.println("getFormKey:" + task.getFormKey());

            Map<String, Object> map1 = (Map<String, Object>)taskService.getVariable(task.getId(), "参数");
            for (Map.Entry<String, Object> m : map1.entrySet()) {
                System.out.println("key:" + m.getKey() + " value:" + m.getValue());
            }
            Map<String, Object> map = task.getProcessVariables();
            for (Map.Entry<String, Object> m : map.entrySet()) {
                System.out.println("key:" + m.getKey() + " value:" + m.getValue());
            }
            for (Map.Entry<String, Object> m : task.getTaskLocalVariables().entrySet()) {
                System.out.println("key:" + m.getKey() + " value:" + m.getValue());
            }

        }

    }

    /**
     * 完成任务
     */
    @Test
    public void completeTask() {
        securityUtil.logInAs("teamleader");        //指定组内任务人
        Page<org.activiti.api.task.model.Task> tasks = taskRuntime.tasks(Pageable.of(0, 10));
        if (tasks.getTotalItems() > 0) {
            for (org.activiti.api.task.model.Task task : tasks.getContent()) {
                System.out.println("任务名称：" + task.getName());
                //拾取任务
                taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(task.getId()).build());
                //执行任务
                taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(task.getId()).build());

                //任务ID
                /*String taskId = "967465fe-3367-11ea-a057-30b49ec7161f";
                taskService.complete(taskId);
                System.out.println("完成任务：任务ID：" + taskId);*/
            }
        }
    }

    /**
     * 历史活动实例查询,参数也能查到
     */
    @Test
    public void queryHistoryTask() {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery() // 创建历史活动实例查询
            // .processInstanceId("9671cdea-3367-11ea-a057-30b49ec7161f") // 执行流程实例id
            .processDefinitionKey("myProcess_1")//
            .includeProcessVariables()//
            .orderByTaskCreateTime()//
            .asc()//
            .list();
        for (HistoricTaskInstance hai : list) {
            System.out.println("-----------------------------------------------");
            System.out.println("活动ID:" + hai.getId());
            System.out.println("流程实例ID:" + hai.getProcessInstanceId());
            System.out.println("活动名称：" + hai.getName());
            System.out.println("办理人：" + hai.getAssignee());
            System.out.println("开始时间：" + hai.getStartTime());
            System.out.println("结束时间：" + hai.getEndTime());
        }
    }

}
