MyBatis Generator (MBG) 可以通过以下方式运行:
从 命令提示符 使用 XML 配置文件
作为 Ant 任务 使用 XML 配置文件
作为 Maven Plugin
从另一个 Java 程序 使用 XML 配置文件

**注意:**还有一个可以良好的集成到Eclipse的MBG插件可以提供额外的功能。 Eclipse 启用 Ant 任务，并支持 Java 文件的自动合并。安装Eclipse插件请参考 MyBatis站点页面的信息。

重要: 当运行在像Eclipse这样的IDE环境的外部运行时, MBG解释targetProject 和 targetPackage的所有XML属性如下:

targetProject 被假定为一个已存在的目录结构。 如果目录结构不存在MBG将会失败。有一个例外 - 当MBG通过Maven插件运行时。 请从Maven插件页面参阅targetProject在Maven中的详细解释。
targetPackage 将会转换为 targetProject 适当的子目录结构。 如果有必要，MBG会创建这些子目录。

从命令行运行 MyBatis Generator
MyBatis Generator (MBG) 可以直接从命令行运行。JAR清单包括默认类的名称 (org.mybatis.generator.api.ShellRunner) 或者您可以自己指定它。 ShellRunner 类接受以下的几种详细参数:




参数	值
-configfile file_name	(必须的)	指定配置文件的名称。
-overwrite (可选的)	如果指定了该参数，如果生成的java文件存在已经同名的文件，新生成的文件会覆盖原有的文件。 如果没有指定该参数，如果存在同名的文件，MBG会给新生成的代码文件生成一个唯一的名字(例如： MyClass.java.1, MyClass.java.2 等等)。 重要: 生成器一定会自动合并或覆盖已经生成的XML文件。
-verbose (可选的)	如果指定该参数，执行过程会输出到控制台。
-forceJavaLogging (可选的)	如果指定该参数，MBG将会使用JAVA日志记录而不会使用Log4J,即使Log4J在运行时的类路径中。
-contextids context1,context2,...	
(可选的)	如果指定了该参数，逗号隔开的这些context会被执行。 这些指定的context必须和配置文件中 <context> 元素的 id 属性一致。 只有指定的这些contextid会被激活执行。如果没有指定该参数，所有的context都会被激活执行。
-tables table1, table2,...	
(可选的)	如果指定了该参数，逗号隔开的这个表会被运行， 这些表名必须和 <table> 配置中的表面完全一致。只有指定的这些表会被执行。 如果没有指定该参数，所有的表都会被执行。 按如下方式指定表明:
table
schema.table
catalog..table
等等。 |

从命令行运行MGB时您必须指定 XML 配置文件。如果文件的名字是"generatorConfig.xml",可以用下面任意的命令执行:

   java -jar mybatis-generator-core-x.x.x.jar -configfile generatorConfig.xml
   java -jar mybatis-generator-core-x.x.x.jar -configfile generatorConfig.xml -overwrite
   java -cp mybatis-generator-core-x.x.x.jar org.mybatis.generator.api.ShellRunner -configfile generatorConfig.xml
   java -cp mybatis-generator-core-x.x.x.jar org.mybatis.generator.api.ShellRunner -configfile generatorConfig.xml -overwrite



使用Ant运行 MyBatis Generator
MyBatis Generator (MBG) 包含一个简单的 Ant 任务。这个任务必须定义在您的 build.xml 文件中。任务可以几个参数。这里是一个 build.xml 文件的例子:

   <project default="genfiles" basedir=".">
     <property name="generated.source.dir" value="${basedir}" />

     <target name="genfiles" description="Generate the files">
       <taskdef name="mbgenerator"
                classname="org.mybatis.generator.ant.GeneratorAntTask"
                classpath="mybatis-generator-core-x.x.x.jar" />
       <mbgenerator overwrite="true" configfile="generatorConfig.xml" verbose="false" >
         <propertyset>
           <propertyref name="generated.source.dir"/>
         </propertyset>
       </mbgenerator>
     </target>
   </project>

MyBatis Generator 任务属性如下:

属性	值
configfile (必须的)	指定配置文件的名称。
overwrite (可选的)	如果指定了该参数，如果生成的java文件存在已经同名的文件，新生成的文件会覆盖原有的文件。 如果没有指定该参数，如果存在同名的文件，MBG会给新生成的代码文件生成一个唯一的名字(例如： MyClass.java.1, MyClass.java.2 等等)。 重要: 生成器一定会自动合并或覆盖已经生成的XML文件。
contextids (可选的)	如果指定了该参数，逗号隔开的这些context会被执行。 这些指定的context必须和配置文件中 <context> 元素的 id 属性一致。 只有指定的这些contextid会被激活执行。如果没有指定该参数，所有的context都会被激活执行。
tables (可选的)	如果指定了该参数，逗号隔开的这个表会被运行， 这些表名必须和 <table> 配置中的表面完全一致。只有指定的这些表会被执行。 如果没有指定该参数，所有的表都会被执行。 按如下方式指定表明: tableschema.tablecatalog..table 等等。
verbose (可选的)	如果设置为 "true", "yes"等等,MBG会将操作信息输出到 ant 控制台 (如果 Ant 在verbose模式下运行)。默认值是 "false"。
注意:

<taskdef>标记中的类路径用于告诉 Ant MBG JAR 文件在哪里。 这是可选的，除非您将 MBG 添加到 Ant 类路径中 Ant 手册所描述的其他地方。
任务的名称可以使任何您想要的, "mbgenerator" 只是一个简单的例子。
该任务支持可选嵌套的 &lt; propertyset &gt; 元素，这是标准的 Ant 属性设置的类型。 这可以用于将参数传递到配置文件。例如，上面提到 generated.source.dir这个属性可以在配置文件中使用${generated.source.dir}进行使用。
如果没有指定配置文件中的一个属性，这个属性将会原样输出。




通过Maven运行 MyBatis Generator
MyBatis Generator (MBG) 包含了一个可以集成到Maven构建的Maven插件，按照Maven的配置惯例, 将MBG集成到Maven很容易. 最简配置如下:

   <project ...>
     ...
     <build>
       ...
       <plugins>
        ...
        <plugin>
      	  <groupId>org.mybatis.generator</groupId>
      	  <artifactId>mybatis-generator-maven-plugin</artifactId>
          <version>1.3.0</version>
        </plugin>
        ...
      </plugins>
      ...
    </build>
    ...
  </project>

当然，事情永远不会那么容易！

Maven Goal and Execution（Maven 目标和执行）
The MBG Maven plugin 包含一个目标:

mybatis-generator:generate
这个目标不会被Maven自动执行，他可以通过以下两种方式执行。

可以在命令行通过以下命令执行:

mvn mybatis-generator:generate
您可以通过标准的Maven命令属性传递参数， 例如:

mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
这条命令会使MBG覆盖重名的文件

在一个连续的编译环境中，您可能想让MGB作为Maven构建的一部分自动执行。 这可以通过配置自动执行的目标来实现，这种情况的例子如下:

   <project ...>
     ...
     <build>
       ...
       <plugins>
        ...
        <plugin>
      	  <groupId>org.mybatis.generator</groupId>
      	  <artifactId>mybatis-generator-maven-plugin</artifactId>
          <version>1.3.0</version>
          <executions>
            <execution>
              <id>Generate MyBatis Artifacts</id>
              <goals>
                <goal>generate</goal>
              </goals>
            </execution>
          </executions>
        </plugin>
        ...
      </plugins>
      ...
    </build>
    ...
  </project>

MBG插件将会绑定到Maven构建的 generate-sources 阶段。 所以他会在编译步骤之前执行。 此外注意MBG目标将绑定生成Java和XML资源文件的建立，他们都将包括在生成的JAR包内。

MyBatis Generator 配置属性
所有配置在POM中的属性都可以传递到配置文件，并且可以用通常的方式使用。例如:

   <project ...>
     ...
     <properties>
       <dao.target.dir>src/main/java</dao.target.dir>
     </properties>
     ...
     <build>
       ...
       <plugins>
        ...
        <plugin>
      	  <groupId>org.mybatis.generator</groupId>
      	  <artifactId>mybatis-generator-maven-plugin</artifactId>
          <version>1.3.0</version>
          <executions>
            <execution>
              <id>Generate MyBatis Artifacts</id>
              <goals>
                <goal>generate</goal>
              </goals>
            </execution>
          </executions>
        </plugin>
        ...
      </plugins>
      ...
    </build>
    ...
  </project>

在这种情况下，属性可以在配置文件中被访问的语法是 ${dao.target.dir}.

参考参数
所有的参数都是可选的，大部分都适合的默认值。

参数	表达式	类型	注释
configurationFile	${mybatis.generator.configurationFile}	java.io.File	指定配置文件的名称。默认值:${basedir}/src/main/resources/generatorConfig.xml
contexts	${mybatis.generator.contexts}	java.lang.String	如果指定了该参数，逗号隔开的这些context会被执行。 这些指定的context必须和配置文件中 <context> 元素的 id 属性一致。 只有指定的这些contextid会被激活执行。如果没有指定该参数，所有的context都会被激活执行。
jdbcDriver	${mybatis.generator.jdbcDriver}	java.lang.String	如果您指定了 sqlScript 参数, 当连接数据库时这里的值是JDBC驱动类的权限定名称。
jdbcPassword	${mybatis.generator.jdbcPassword}	java.lang.String	如果您指定了 sqlScript 参数, 这是连接数据库的密码。
jdbcURL	${mybatis.generator.jdbcURL}	java.lang.String	如果您指定了 sqlScript 参数, 这是连接数据库的JDBC URL
jdbcUserId	${mybatis.generator.jdbcUserId}	java.lang.String	如果您指定了 sqlScript 参数, 这里是连接数据库的用户id
outputDirectory	${mybatis.generator.outputDirectory}	java.io.File	将放置 MBG 所生成文件的目录。 这个目录是用于当 targetProject 在配置文件中设置特殊值的"MAVEN"时使用(大小写敏感)。默认值:${project.build.directory}/generated-sources/mybatis-generator
overwrite	${mybatis.generator.overwrite}	boolean	如果指定了该参数，如果生成的java文件存在已经同名的文件，新生成的文件会覆盖原有的文件。 如果没有指定该参数，如果存在同名的文件，MBG会给新生成的代码文件生成一个唯一的名字(例如： MyClass.java.1, MyClass.java.2 等等)。 **重要: 生成器一定会自动合并或覆盖已经生成的XML文件。**默认值:false
sqlScript	${mybatis.generator.sqlScript}	java.lang.String	要在生成代码之前运行的 SQL 脚本文件的位置。 如果空，不会执行任何脚本。 如果不是空，jdbcDriver, jdbcURL 参数必须提供。 另外如果连接数据库需要认证也需要提供 jdbcUserId 和 jdbcPassword 参数。值可以使一个文件系统的绝对路径或者是一个使用"classpath:"开头放在构建的类路径下的路径。
tableNames	${mybatis.generator.tableNames}	java.lang.String	如果指定了该参数，逗号隔开的这个表会被运行， 这些表名必须和 <table> 配置中的表面完全一致。只有指定的这些表会被执行。 如果没有指定该参数，所有的表都会被执行。 按如下方式指定表明: tableschema.tablecatalog..table 等等。
verbose	${mybatis.generator.verbose}	boolean	如果指定该参数，执行过程会输出到控制台。
targetProject 解释
与Maven运行时 生成器 配置的 targetProject 属性有不同的解释。 如果指定值为"MAVEN" (大小写敏感), targetProject 将被设置为插件的输出目录，而且如果不存在这个目录，将会创建这个目录。 如果没有设置为 "MAVEN", 那么targetProject 将会被 MGB 当成普通的 - 它必须是一个已经存在的目录。



使用Java运行 MyBatis Generator
MyBatis Generator (MBG) 可以直接使用Java调用。 对于配置，您可以使用XML配置文件，或者完全使用Java进行配置。

使用XML配置文件从Java运行MBG
下面的代码例子展示了如何通过XML配置文件从Java运行MBG。 他不显示异常处理，但是编译错误是很明显的 :)

   List<String> warnings = new ArrayList<String>();
   boolean overwrite = true;
   File configFile = new File("generatorConfig.xml");
   ConfigurationParser cp = new ConfigurationParser(warnings);
   Configuration config = cp.parseConfiguration(configFile);
   DefaultShellCallback callback = new DefaultShellCallback(overwrite);
   MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
   myBatisGenerator.generate(null);

注意:

配置文件属性可以通过 ConfigurationParser 的构造函数的参数传递给解析器。如果没有显式传递，配置文件的属性将会从JVM的系统属性搜索。 例如，属性 generated.source.dir 可以在配置文件中通过 ${generated.source.dir} 被访问。
如果没有指定配置文件中的一个属性，这个属性将会原样输出。
通过基于Java的配置运行MGB
下面的代码例子展示了如何通过基于Java的配置运行MGB。 他不显示异常处理，但是编译错误是很明显的 :)

   List<String> warnings = new ArrayList<String>();
   boolean overwrite = true;
   Configuration config = new Configuration();

   //   ... fill out the config object as appropriate...

   DefaultShellCallback callback = new DefaultShellCallback(overwrite);
   MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
   myBatisGenerator.generate(null);

   
   
   
   参考
   https://www.kancloud.cn/wizardforcel/java-opensource-doc/152986