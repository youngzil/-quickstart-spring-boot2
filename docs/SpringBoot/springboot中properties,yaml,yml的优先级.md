因为springboot有三种后缀的配置文件格式,那这三种文件的优先级和加载顺序或者说是覆盖顺序是怎么样子的呢

在springboot中,配置文件可以是properties,yaml,yml三个格式中的任意一个,其中properties是键值对形式的,yaml和yml其实是同一种格式,只是后缀名不同而已


当三种文件同时存在时,其实三个文件中的配置信息都会生效

但是当三个文件中有配置信息冲突时,优先级是:properties>yaml>yml,

也就是properties里配置的内容会覆盖另外两个的配置


至于原因可以在spring-boot-starter-parent里找到

这里的逻辑顺序是先加载yml再加载yaml再加properties,后加载的自然会把先加载的数据给覆盖掉.

<resources>
  <resource>
    <directory>${basedir}/src/main/resources</directory>
    <filtering>true</filtering>
    <includes>
      <include>**/application*.yml</include>
      <include>**/application*.yaml</include>
      <include>**/application*.properties</include>
    </includes>
  </resource>
  <resource>
    <directory>${basedir}/src/main/resources</directory>
    <excludes>
      <exclude>**/application*.yml</exclude>
      <exclude>**/application*.yaml</exclude>
      <exclude>**/application*.properties</exclude>
    </excludes>
  </resource>
</resources>




[springboot中properties,yaml,yml的优先级](https://jingyan.baidu.com/article/5bbb5a1bbda69413eaa17967.html)  



