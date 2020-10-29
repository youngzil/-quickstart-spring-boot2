Spring中报"Could not resolve placeholder"的解决方案

除去properites文件路径错误、拼写错误外，出现"Could not resolve placeholder"很有可能是使用了多个PropertyPlaceholderConfigurer或者多个<context:property-placeholder>的原因。

比如我有一个dao.xml读取dbConnect.properties，还有一个dfs.xml读取dfsManager.properties，然后web.xml统一load这两个xml文件
<context-param>  
        <param-name>contextConfigLocation</param-name>  
        <param-value>  
                WEB-INF/config/spring/dao.xml,   
                WEB-INF/config/spring/dfs.xml  
        </param-value>  
</context-param>  
如果这两个xml文件中分别有
<!-- dao.xml -->  
<context:property-placeholder location="WEB-INF/config/db/dbConnect.properties" />  
  
<!-- dfs.xml -->  
<context:property-placeholder location="WEB-INF/config/dfs/dfsManager.properties" />  
那么，一定会出"Could not resolve placeholder"。


一定要记住，不管是在一个Spring文件还是在多个Spring文件被统一load的情况下，直接写
<context:property-placeholder location="" />  
<context:property-placeholder location="" />   
是不允许的。


解决方案：
　　(1) 在Spring 3.0中，可以写：
<context:property-placeholder location="xxx.properties" ignore-unresolvable="true"  
/>  
<context:property-placeholder location="yyy.properties" ignore-unresolvable="true"  
/>  
注意两个都要加上ignore-unresolvable="true"，一个加另一个不加也是不行的

　　(2) 在Spring 2.5中，<context:property-placeholder>没有ignore-unresolvable属性，此时可以改用PropertyPlaceholderConfigurer。其实<context:property-placeholder location="xxx.properties" ignore-unresolvable="true" />与下面的配置是等价的

<bean id="随便" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">  
    <property name="location" value="xxx.properties" />  
    <property name="ignoreUnresolvablePlaceholders" value="true" />   
</bean>  
　　正因为如此，写多个PropertyPlaceholderConfigurer不加ignoreUnresolvablePlaceholders属性也是一样会出"Could not resolve placeholder"。

　　虽然两者是的等价的，但估计大家还是喜欢写<context:property-placeholder>多一些，毕竟简单一些嘛。所以如果是Spring 3.0，直接用解决方案(1)再简单不过了；如果是Spring 2.5，需要费点力气改写成PropertyPlaceholderConfigurer














