package org.cathay.rxjava;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

import static java.util.concurrent.Executors.newFixedThreadPool;
public class SchedulerFactory {

    public static ExecutorService poolA = newFixedThreadPool(10, threadFactory("Sched-A-%d"));
    public static Scheduler schedulerA = Schedulers.from(poolA);
    public static  ExecutorService poolB = newFixedThreadPool(10, threadFactory("Sched-B-%d"));
    public static Scheduler schedulerB = Schedulers.from(poolB);
    public static ExecutorService poolC = newFixedThreadPool(10, threadFactory("Sched-C-%d"));
    public static Scheduler schedulerC = Schedulers.from(poolC);

    private static ThreadFactory threadFactory(String pattern) {
        return new ThreadFactoryBuilder()
                .setNameFormat(pattern).build();
    }
}
