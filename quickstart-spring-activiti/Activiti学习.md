[Activiti官网](https://www.activiti.org/)  
[Activiti Github](https://github.com/Activiti/Activiti)  
[Activiti用户指南手册](https://www.activiti.org/userguide/)  
[Activiti Example](https://github.com/Activiti/activiti-examples)  
[Activiti下载地址](https://www.activiti.org/get-started)  


Activiti is a light-weight workflow and Business Process Management (BPM) Platform targeted at business people, developers and system admins. Its core is a super-fast and rock-solid BPMN 2 process engine for Java. It's open-source and distributed under the Apache license. Activiti runs in any Java application, on a server, on a cluster or in the cloud. It integrates perfectly with Spring, it is extremely lightweight and based on simple concepts.

Activiti是一个轻量级的工作流和业务流程管理（BPM）平台，面向业务人员，开发人员和系统管理员。 它的核心是用于Java的超快速，坚固的BPMN 2流程引擎。 它是开源的，并根据Apache许可进行分发。 Activiti可在任何Java应用程序，服务器，集群或云中运行。 它与Spring完美集成，非常轻巧，基于简单的概念。



### 参数说明

- databaseSchemaUpdate配置项可以设置流程引擎启动和关闭时数据库执行的策略。 databaseSchemaUpdate有以下四个值：
    - false：false为默认值，设置为该值后，Activiti在启动时，会对比数据库表中保存的版本，如果没有表或者版本不匹配时，将在启动时抛出异常。
    - true：设置为该值后，Activiti会对数据库中所有的表进行更新，如果表不存在，则Activiti会自动创建。
    - create-drop：Activiti启动时，会执行数据库表的创建操作，在Activiti关闭时，执行数据库表的删除操作。
    - drop-create：Activiti启动时，执行数据库表的删除操作在Activiti关闭时，会执行数据库表的创建操作。
- history-level对于历史数据，保存到何种粒度，Activiti提供了history-level属性对其进行配置。history-level属性有点像log4j的日志输出级别，该属性有以下四个值：
    - none：不保存任何的历史数据，因此，在流程执行过程中，这是最高效的。
    - activity：级别高于none，保存流程实例与流程行为，其他数据不保存。
    - audit：除activity级别会保存的数据外，还会保存全部的流程任务及其属性。audit为history的默认值。
    - full：保存历史数据的最高级别，除了会保存audit级别的数据外，还会保存其他全部流程相关的细节数据，包括一些流程参数等。
- db-history-used为true表示使用历史表，如果不配置，则工程启动后可以检查数据库，只建立了17张表，历史表没有建立，则流程图及运行节点无法展示




### 工作流七大service简单介绍
1. RepositoryService：流程仓库service,用于管理流程仓库,增删改查流程资源。
  1. Model 模型对象，BPMN文件的对象
  2. Deploy 部署对象，根据BPMN文件创建的一个部署 
  3. Process 根据部署的Deploy，开启一个流程 
2. RuntimeService：运行时service,处理正在运行状态的流程实例,任务等。
3. TaskService：任务Taskservice,管理,查询任务，例如签收，办理,指派任务。
4. HistoryService：历史Taskservice,可以查询所有历史数据,例如,流程实例,任务,活动，变量，附件等。
5. managementService：引擎管理service,和具体业务无关,主要是查询引擎配置，数据库，作业等。
6. identityService：身份service,可以管理和查询用户,组之间的关系。
7. formService：表单service,处理正在运行状态的流程实例,任务等。



- RepositoryService：Model实体、Deploy部署、Process流程的管理
- ProcessRuntime(RuntimeService)：Process流程的管理
- TaskRuntime(TaskService)：Task任务的管理
- HistoryService：历史Task的管理



开发使用Activiti流程：
1. 设计流程：生成Model实体
2. 流程部署：Deploy部署
3. 启动流程：
4. 查询待办Task任务
5. 任务认领和完成任务：claim拾取任务 和 complete完成任务
6. 查询历史任务、历史流程实例、历史节点




如果你的版本和我是一样的话7.1.0.M5，多半就会报错，sql少字段。这个应该是一个官方的BUG，只需要在 act_re_deployment中加两个字段就好了： VERSION_  int 和 PROJECT_RELEASE_VERSION_  varchar(255) 然后重新运行，应该就对了。  
```
alter table act_re_deployment add VERSION_ int null;
alter table act_re_deployment add PROJECT_RELEASE_VERSION_ varchar(255) null;
```
但是打印的已部署个数却是0。说好的 processes 下自动部署呢，最后自己部署一下，发现一切都对了的。




### 关于表解释
```
Activiti的后台是有数据库的支持，所有的表都以ACT_开头。 第二部分是表示表的用途的两个字母标识。 用途也和服务的API对应。

ACT_RE_*: 'RE'表示repository。 这个前缀的表包含了流程定义和流程静态资源 （图片，规则，等等）。
ACT_RU_*: 'RU'表示runtime。 这些运行时的表，包含流程实例，任务，变量，异步任务，等运行中的数据。 Activiti只在流程实例执行过程中保存这些数据， 在流程结束时就会删除这些记录。 这样运行时表可以一直很小速度很快。
ACT_ID_*: 'ID'表示identity。 这些表包含身份信息，比如用户，组等等。
ACT_HI_*: 'HI'表示history。 这些表包含历史数据，比如历史流程实例， 变量，任务等等。
ACT_GE_*: 通用数据， 用于不同场景下，如存放资源文件。

资源库流程规则表
   1) act_re_deployment 部署信息表
   2) act_re_model  流程设计模型部署表
   3) act_re_procdef  流程定义数据表

运行时数据库表
   1) act_ru_execution运行时流程执行实例表
   2) act_ru_identitylink运行时流程人员表，主要存储任务节点与参与者的相关信息
   3) act_ru_task运行时任务节点表
   4) act_ru_variable运行时流程变量数据表

历史数据库表
    1) act_hi_actinst 历史节点表
    2) act_hi_attachment历史附件表
    3) act_hi_comment历史意见表
    4) act_hi_identitylink历史流程人员表
    5) act_hi_detail历史详情表，提供历史变量的查询
    6) act_hi_procinst历史流程实例表
    7) act_hi_taskinst历史任务实例表
    8) act_hi_varinst历史变量表

通用数据表
    1) act_ge_bytearray二进制数据表
    2) act_ge_property属性数据表存储整个流程引擎级别的数据,初始化表结构时，会默认插入三条记录
    
    
    
    
 
```




### 遇到的问题

目前只能使用spring boot 2.1.x + M5，不然会报错

only spring boot 2.1.x is supported at the moment as springfox is not compatible with 2.2.x yet

可以参考[这个issues](https://github.com/Activiti/Activiti/issues/3229)






第一次启动，没有执行任何流程部署的时候

act_ge_property Activiti框架本身的信息，包括版本等



执行部署流程后：

act_ge_bytearray（2条记录） 流程的信息，包括资源的名称，文件的内容等

act_re_deployment（1条记录） 流程的部署信息，部署名称等

act_re_procdef（1条记录） 流程定义数据信息，name、key（bpmn文件的id）、deployment_id(deployment表id)



启动流程后：

act_hi_actinst（2条记录） 历史节点表

act_hi_procinst（1条记录） 历史流程实例表

act_hi_identitylink（2条记录） 历史流程人员表

act_hi_taskinst（1条记录） 历史任务实例表

act_ru_execution 运行时流程执行实例表

act_ru_identitylink 流程人员信息

act_ru_task 当前的待执行的task任务



执行第一个任务后：

act_hi_actinst（3条记录） 历史节点表

act_hi_procinst（1条记录） 历史流程实例表

act_hi_identitylink（3条记录） 历史流程人员表

act_hi_taskinst（2条记录） 历史任务实例表

act_ru_execution（2条记录） 运行时流程执行实例表

act_ru_identitylink（2条记录） 流程人员信息

act_ru_task（1条记录） 当前的待执行的task任务


执行完了，ru表就没有数据了





[springboot整合activiti7](https://www.jianshu.com/p/17d35230310b)  
[spring boot2与activiti7完美结合（踩坑）](https://cloud.tencent.com/developer/article/1584828)  
[SpringBoot2+Activtiti7 整合](https://github.com/zjialin/SpringBoot2-Activiti7)  
[activiti 7 + springboot2（六） SpringBoot2 整合 Activiti7](https://www.cnblogs.com/zsg88/p/12174750.html)  
[activiti7+springboot2.1.x流程启动](https://www.jianshu.com/u/a7c7ea9fdc9a)  
[《2020/01/06》Activiti7工作流+SpringBoot（一）](https://blog.csdn.net/weixin_42273782/category_9647269.html)  


https://programmer.ink/think/5dd64cea04da7.html

https://gitee.com/active4j/active4j-flow

https://www.baeldung.com/java-activiti

https://activiti.gitbook.io/activiti-7-developers-guide/getting-started/getting-started-activiti-core



