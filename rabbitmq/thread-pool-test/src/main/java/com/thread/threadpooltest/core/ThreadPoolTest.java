package com.thread.threadpooltest.core;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.*;

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
public class ThreadPoolTest {

    public static void main(String[] args) {
        /*ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 50, 5000L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(20, 50, 5000L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), Executors.defaultThreadFactory());
        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(20, 50, 5L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new ThreadPoolExecutor.AbortPolicy());
        ThreadPoolExecutor threadPoolExecutor3 = new ThreadPoolExecutor(20, 50, 5L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        threadPoolExecutor.execute(new RunnableImpl());


        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ExecutorService executorService1 = Executors.newFixedThreadPool(10, Executors.defaultThreadFactory());
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        ExecutorService executorService3 = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
        ExecutorService executorService4 = Executors.newCachedThreadPool();
        ExecutorService executorService5 = Executors.newCachedThreadPool(Executors.defaultThreadFactory());
        executorService.execute(new RunnableImpl());

        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.execute(new Thread());*/

        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = f.format(new Date());
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parse = f1.parse(format, pos);
        System.out.println(parse);
    }
}
