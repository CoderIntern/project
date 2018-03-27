package rabbitmqdemo.rabbbittest.config.thread_consumer_producer;

/**
 * <p> Date             :2018/3/23 </p>
 * <p> Module           : </p>
 * <p> Description      : </p>
 * <p> Remark           : </p>
 *
 * @author yangdejun
 * @version 1.0
 * <p>--------------------------------------------------------------</p>
 * <p>修改历史</p>
 * <p>    序号    日期    修改人    修改原因    </p>
 * <p>    1                                     </p>
 */
public class MQConstant {
    public static final String EXCHANGE_NAME = "testblock";
    public static final String ROUTING_KEY = "testblock";
    public static final String QUEUE_NAME = "testblock";

    public static int PRODUCTER_CONNECTION_SIZE = 1;
    public static int CONSUMER_CONNECTION_SIZE = 1;
    public static final int CONSUMER_SIZE = 1;
    public static int QOS = 10;
    public static long SLEEP_TIME = 0;
    public static boolean AUTOACK = true;
}
