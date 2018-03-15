exception包service接口所遇到的异常
dto 数据传输层，和entity类似，
    entity是业务的封装
    dto关注的是web和service之间的数据传递
    service返回数据的封装

service层设计：
业务接口：站在“使用者”角度设计接口
三个方面：方法定义粒度，参数，返回类型（return 类型/异常）

spring 注解
@Component 适用于所有的注解，当不知道属于什么注解时使用
@Service service层注解
@Dao dao层注解
@Controller controller层注解
@Transactional 注解表示事务方式（推荐）
使用事务控制事务的优点：
1. 开发团队达成一致约定，明确标注事务方法的编程风格
2. 保证事务方法的执行的时间尽可能短，不要穿插其他网络操作RPC/HTTP请求或者剥离到事务方法外部
3. 不是所有的方法都是需要事务，如只有一条修改操作，制度操作不需要事务控制


日志输出：https://logback.qos.ch/manual/configuration.html
在需要日志输出的类中配置
private final Logger logger = LoggerFactory.getLogger(this.getClass());
在方法中
logger.info("list={}", list);在控制台输出list内容
logger.erro();
logger.warn();