package com.thread.threadpooltest.core;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * <p> Date             :2018/3/21 </p>
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
public class RunnableImpl implements Runnable {

    private String s = "s";
    @Override
    public void run() {
        test(s);
    }

    @RabbitListener(queues = {"position"})
    private void test(String message) {
        System.out.println("*********************************");
        test1();
    }

    public void test1() {

    }

    /*Runnable r = () -> {
      @RabbitListener(queues = {"position"})
      private void test(String message){

        }
    };*/
}
