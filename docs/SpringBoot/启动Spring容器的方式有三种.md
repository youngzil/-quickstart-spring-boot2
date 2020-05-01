在Web项目中，启动Spring容器的方式有三种，ContextLoaderListener、ContextLoadServlet、ContextLoaderPlugin。

监听器方式：
web.xml

复制代码
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/classes/applicationContext-*.xml</param-value>
</context-param>
<listener> 
    <listener-class>org.springframework.web.context.ContextLoaderListener
 </listener-class>
 </listener>
复制代码
还可以通过<import resource="classpath:/spring/spring-xxx.xml"/>的方式把其他的配置抱进来。

 

1.2、servlet方式：
<servlet> 
    <servlet-name>context</servlet-name> 
    <servlet-class>org.springframework.web.context.ContextLoaderServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
</servlet> 
这种方式，spring3.0以后不再支持，建议使用监听器方式。你可以查看一下spring3.0的change log 
http://static.springsource.org/spring/docs/3.0.x/changelog.txt 
里面注明： 
removed ContextLoaderServlet and Log4jConfigServlet 

 

1.3、通过plugin配置方式：
<plug-in className="org.springframework.web.struts.ContextLoaderPlugIn">  
    <set-property property="contextConfigLocation" value="/WEB-INF/applicationContext.xml,/WEB-INF/action-servlet.xml" />  
</plug-in> 
该方式适用于，spring与struts等整合，在Struts的配置文件struts-config.xml里面配置一个ContextLoaderPlugIn，用于spring的初始化工作。

 

1.4、补充两点：
1、如果要spring-mvc的话，需要在web.xml文件中配置如下：

复制代码
<servlet>
    <servlet-name>simpleSpringMVC/servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatchServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>simpleSpringMVC</servlet-name>
    <url-pattern>*.htm</url-pattern>
</servlet-mapping>

2、如果要使用springSecurity的话，首先需要在web.xml进行以下配置，
<filter>
    <filter-name>springSecurityFilterChain</filter-name>
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class> 
    <init-param>
        <param-name>targetFilterLifecycle</param-name>
        <param-value>true</param-value>  <!-- 默认是false -->
    </init-param>
 </filter>

<filter-mapping>
  <filter-name>springSecurityFilterChain</filter-name>
  <url-pattern>/*</url-pattern>
 </filter-mapping>
 
 
参考
https://www.cnblogs.com/duanxz/p/5074584.html
https://blog.csdn.net/cuipp0509/article/details/78544497
http://www.huaijiujia.com/2018/06/11/spring%E7%89%B9%E7%82%B9-ioc%E5%AE%B9%E5%99%A8%E5%90%AF%E5%8A%A8%E7%9A%84%E4%B8%89%E7%A7%8D%E6%96%B9%E5%BC%8F/

