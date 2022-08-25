

[SpringBoot 配置文件位置的加载顺序](https://blog.csdn.net/riemann_/article/details/108630781)

SpringBoot配置文件默认可以放到以下目录中，下面四种可以自动读取到：
1. 项目根目录中config目录下
2. 项目根目录下
3. 项目resources目录中config目录下
4. 项目的resources目录下

以上的application.properties优先级都要大于application.yml的哈，然后再按照那个顺序。


如果在不同的目录中存在多个配置文件，它的读取顺序是：
1、config/application.properties（项目根目录中config目录下）
2、config/application.yml
3、application.properties（项目根目录下）
4、application.yml
5、resources/config/application.properties（项目resources目录中config目录下）
6、resources/config/application.yml
7、resources/application.properties（项目的resources目录下）
8、resources/application.yml

注意
1、如果同一个目录下，有application.yml也有application.properties，默认先读取application.properties。
2、如果同一个配置属性，在多个配置文件都配置了，默认使用第1次读取到的，后面读取的不覆盖前面读取到的。
3、创建SpringBoot项目时，一般的配置文件放置在“项目的resources目录下”





