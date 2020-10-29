java元注解：
@Target(ElementType.TYPE)，说明了Annotation所修饰的对象范围，明晰其修饰的目标
@Retention(RetentionPolicy.RUNTIME)，定义了该Annotation被保留的时间长短
@Inherited，是一个标记注解，某个被标注的类型是被继承的，类并不从它所实现的接口继承annotation，方法并不从它所重载的方法继承annotation。
@Documented，是一个标记注解，没有成员，可以被例如javadoc此类的工具文档化


Javax注解：
@PostConstruct(JSR-250)注解，在Bean初始化之后被Spring容器执行
@PreDestroy(JSR-250)注解，在类销毁之前调用
@Inject	JSR-330提供的注解
@WebFilter Servlet3.0下Filter注解
@WebServlet


Javax.ws
https://blog.csdn.net/chuchus/article/details/45008277
@Path 路径，类或方法
@GET 请求类型
@Consumes 注释代表的是一个资源可以接受的 MIME 类型。
@Produces 注释代表的是一个资源可以返回的 MIME 类型。

@PathParam 获取url中指定参数名称
@HeaderParam 头部参数
@BeanParam 获取请求参数中的数据，用实体Bean进行封装
@FormParam 获取post请求中表单中的数据：
@QueryParam 获取get请求中的查询参数
@DefaultValue 为参数设置默认值
@Context 用于获取环境信息



javax.xml注解
@XmlType 用在class类的注解，可以设置生成元素的顺序
@XmlAccessorType(XmlAccessType.FIELD) 访问级别
@XmlRootElement 用于类级别的注解，对应xml的根元素 

@XmlElement 将Java对象的属性映射为xml的节点，在使用@XmlElement时，可通过name属性改变java对象属性在xml中显示的名称
@XmlAttribute 用于把java对象的属性映射为xml的属性,并可通过name属性为生成的xml属性指定别名
@XmlTransient 用于标示在由java对象映射xml时，忽略此属性
@XmlAccessorOrder 用于对java对象生成的xml元素进行排序
@XmlJavaTypeAdapter 使用定制的适配器（即扩展抽象类XmlAdapter并覆盖marshal()和unmarshal()方法），以序列化Java类为XML

@XmlSchema 生成的XML的Schema信息
@XmlRegistry 用于标注在充当ObjectFactory角色的类上
@XmlSeeAlso 用于指定相关的Class


@WebService
@WebMethod 
@WebServiceClient
@WebServiceRef

@WebServlet Servlet3.0注解
@WebFilter Servlet3.0下Filter注解



Spring注解：

类上：
@Controller用于标注控制层组件(如struts中的action) 
@Service用于标注业务层组件 
@Repository用于标注数据访问组件，即DAO组件. 
@Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。    
@Scope("session") ，定义一个Bean的作用范围
@Order(value = 5) 值越小拥有越高的优先级。
@Primary 优先考虑，当相同的实现时候，优先考虑被注解的对象注入
@Import 把用到的bean导入到了当前容器中



方法上
@Bean 
@PostConstruct(JSR-250)注解，在Bean初始化之后被Spring容器执行
@PreDestroy(JSR-250)注解，在类销毁之前调用


变量上：
@Inject	JSR-330提供的注解
@Resource JSR-250标准注解，默认按byName自动注入
@Autowired Spring注入，按byType自动注入
@Qualifier 名字声明，声明后对名字进行使用，按照bean的 nama属性进行注入




配置类：
@Value 注入配置
@Configuration 配置类，继承@Component，声明当前类是一个配置类（相当于一个Spring配置的xml文件）

@ConfigurationProperties 配置参数前缀


缓存Cache：
@Cacheable



spring框架还提供了很多@Condition给我们用：
@ConditionalOnBean（仅仅在当前上下文中存在某个对象时，才会实例化一个Bean）
@ConditionalOnClass（某个class位于类路径上，才会实例化一个Bean）
@ConditionalOnExpression（当表达式为true的时候，才会实例化一个Bean）
@ConditionalOnMissingBean（仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean）
@ConditionalOnMissingClass（某个class类路径上不存在的时候，才会实例化一个Bean）
@ConditionalOnNotWebApplication（不是web应用）

另一种总结
@ConditionalOnClass：该注解的参数对应的类必须存在，否则不解析该注解修饰的配置类；
@ConditionalOnMissingBean：该注解表示，如果存在它修饰的类的bean，则不需要再创建这个bean；可以给该注解传入参数例如@ConditionOnMissingBean(name = "example")，这个表示如果name为“example”的bean存在，这该注解修饰的代码块不执行。




WEB开发：
@Controller 类，标识Controller层类
@RestController Rest Web层
@RequestMapping 类或方法，拦截路径
@PostMapping Post请求路径
@GetMapping Get请求路径
@ResponseBody 方法返回，方法返回
@RequestBody 方法参数，请求参数



异常统一拦截处理：
@ControllerAdvice Controller层切面拦截类
@ExceptionHandler 方法上，处理的异常类型




AOP切面功能：
@Aspect 切面类
@Pointcut 切点，定义拦截规则
@Around 切面方法，拦截的切点 或 配置拦截的包路径和拦截条件表达式
@Before
@After
@AfterReturning


测试：
@RunWith(SpringRunner.class) junit注解
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ApplicationStarter.class)
@DirtiesContext




SpringBoot和SpringCloud注解：

@SpringBootApplication SpringBoot启动类
@ComponentScan(AiConstants.COMPONENT_SCAN)，自动扫描指定包下所有使用@Service,@Component,@Controller,@Repository的类并注册
@ServletComponentScan(AiConstants.COMPONENT_SCAN)

@EnableAdminServer 启动admin健康检查
@EnableScheduling 启动定时任务
@EnableCaching 启动Spring cache
@EnableConfigurationProperties
@EnableAutoConfiguration


@SpringCloudApplication 默认开启Eureka和Hystrix、继承@SpringBootApplication、@EnableDiscoveryClient、@EnableCircuitBreaker



1、注册中心Eureka：Provider和Consumer注册
@EnableEurekaServer 继承@EnableDiscoveryClient
@EnableEurekaClient 继承@EnableDiscoveryClient

2、Consumer使用ribbon+restTemplate/feign调用服务
@EnableFeignClients 开启Feign客户端，配合@FeignClient使用，默认集成了Ribbon
@LoadBalanced 开启Ribbon负载均衡，结合RestTemplate使用

3、Consumer+请求合并Hystrix Collapser+负载均衡的工具Ribbon+服务网关Zuul+服务容错保护Hystrix

@EnableHystrix 继承@EnableCircuitBreaker，
@HystrixCommand(fallbackMethod = "fallback") 熔断器
@HystrixCollapser 请求合并，@HystrixCollapser(scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL, batchMethod = "findByIds", collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "100")})
@EnableHystrixDashboard Hystrix控制台
@EnableTurbine 汇总系统内多个服务的数据并显示到Hystrix Dashboard上
@EnableTurbineStream 通过流，消息传输

@EnableZuulProxy 继承@EnableCircuitBreaker、@EnableDiscoveryClient


@EnableZipkinServer Zipkin服务跟踪
@EnableZipkinStreamServer 通过流，消息传输


配置中心
@EnableConfigServer 开启Spring-Cloud-Config服务





Mybstis注解：
@MapperScan 启动类，配置扫描路径
@Intercepts sql执行前进行拦截
@Mapper Mapper接口类



