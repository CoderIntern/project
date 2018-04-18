package com.daily.dailytest.date;

import java.util.Date;

/**
 * <p> Date             :2018/4/13 </p>
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
public class DateTest {

    public static void main(String[] args) throws InterruptedException {
        Date date1 = new Date();
//        Thread.sleep(5000);
        Date date2 = new Date();
        System.out.println(date1.getTime() + "->" + date2.getTime());
        System.out.println(date2.getTime() - date1.getTime());
    }
}
