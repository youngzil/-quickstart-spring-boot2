最后给一些小技巧：
a) 建表时，字段名称建议用"_"分隔多个单词，比如:AWB_NO、REC_ID...，这样生成的entity，属性名称就会变成漂亮的驼峰命名，即：awbNo、recId
b)oracle中，数值形的字段，如果指定精度，比如Number(12,2)，默认生成entity属性是BigDecimal型 ，如果不指定精度，比如:Number(9)，指默认生成的是Long型
c)oracle中的nvarchar/nvarchar2，mybatis-generator会识别成Object型，建议不要用nvarchar2，改用varchar2


Mybatis-Generator插件
Mybatis-Generator是一个用于自动生成dao层接口、pojo以及mapper xml的一个Mybatis插件
该插件有三种用法：命令行运行、Eclipse插件、maven插件。
个人觉得maven插件最方便，可以在eclipse/intellij idea等ide上通用，本文也是介绍在maven中配置并使用这个插件。

创建并填写适当的配置文件。 至少，您必须指定:
<jdbcConnection> 元素定义如何连接目标数据库
<javaModelGenerator> 元素来指定生成 Java 模型对象所属的包
<sqlMapGenerator> 元素来指定生成 SQL 映射文件所属的包和的目标项目
(可选的) <javaClientGenerator> 元素来指定目标包和目标项目生成的客户端接口和类 （如果您不想生成 Java 客户端代码您可以省略<javaClientGenerator> 元素）

按下面的方式从命令行运行 MBG:
      java -jar mybatis-generator-core-x.x.x.jar -configfile \temp\generatorConfig.xml -overwrite
这会告诉 MBG 使用配置文件去运行。 MBG还会覆盖已经存在的同名Java文件。 如果您想保留已经存在的Java文件，您可以忽略 -overwrite 参数。 如果存在冲突, MBG 会用一个唯一的名字保存新生成的文件（例如：MyClass.java.1)。


在Spring中集成Mybatis
现在后端开发都流行使用SSM框架，而SSM分别指的是Spring、SpringMVC、Mybatis，那么我们就会需要在Spring中集成Mybatis，这样就可以使用Spring容器来进行统一的管理了。想要在在Spring中集成Mybatis需要用到一个包：MyBatis-Spring。

什么是MyBatis-Spring?

MyBatis-Spring 会帮助你将 MyBatis 代码无缝地整合到 Spring 中。 使用这个类库中的类，Spring 将会加载必要的 MyBatis 工厂类和 session 类。 这个类库也提供一个简单的方式来注入 MyBatis 数据映射器和 SqlSession 到业务层的 bean 中。 而且它也会处理事务， 翻译 MyBatis 的异常到 Spring 的 DataAccessException 异常(数据访问异常)中。最终，它并不会依赖于 MyBatis，Spring 或 MyBatis-Spring 来构建应用程序代码。
MyBatis-Spring诞生动机：

正如第二版那样，Spring 3.0 也仅支持 iBatis2。那么，我们就想将 MyBatis3 的支持添加到 Spring3.0(参考 Spring Jira 中的问题)中。而不幸的是，Spring 3.0 的开发在 MyBatis 3.0 官方发布前就结束了。 因为 Spring 开发团队不想发布一个基于非发布版的 MyBatis 的整合支持，那么 Spring 官方的支持就不得不继续等待了。想要在 Spring 中支持 MyBatis，MyBatis 社区认为现在应该是自己团结贡献者和有兴趣的人一起来开始将 Spring 的整合作为 MyBatis 社区的子项目的时候了。

MyBatis-Spring官方文档地址如下：
http://www.mybatis.org/spring/zh/index.html


参考
http://blog.51cto.com/zero01/2103687
https://www.kancloud.cn/wizardforcel/java-opensource-doc/152983
https://www.kancloud.cn/wizardforcel/java-opensource-doc/152986
https://my.oschina.net/lujianing/blog/200135


