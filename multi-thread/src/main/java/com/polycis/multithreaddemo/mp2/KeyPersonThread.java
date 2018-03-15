package com.polycis.multithreaddemo.mp2;

/**
 * <p> Date             :2018/1/10 </p>
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
public class KeyPersonThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始战斗！");
        for (int i = 0; i < 10; i ++) {
            System.out.println(Thread.currentThread().getName() + "左突右杀，攻击隋军...");
        }
        System.out.println(Thread.currentThread().getName() + "结束了战斗！");
    }
}
