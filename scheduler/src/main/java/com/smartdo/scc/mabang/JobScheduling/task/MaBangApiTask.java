package com.smartdo.scc.mabang.JobScheduling.task;

import com.smartdo.scc.mabang.JobScheduling.job.MabangApiJob;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class MaBangApiTask {
    /**
     * 开启定时更新任务
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
//        //开启初始化  逻辑一样  纯粹只是怕一小时跑不完，出现重复的情况
//        MabangApiJob mAJ = new MabangApiJob();
//        mAJ.firstInit();
        //开启定时更新任务
        MaBangApiTask mBAT = new MaBangApiTask();
        mBAT.taskStart();
    }

    /**
     * 开启MabangApiJob的定时任务
     * @throws Throwable
     */
    @Test
    public void taskStart() throws Throwable {
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        JobDetail job = JobBuilder.newJob(MabangApiJob.class).withIdentity("job1", "group1").build();
        // @NOTICE
        // 例表达式("0 0 0/1 * * ?")的定义是每个时是1的倍数，或者是0的的整点时候，都触发任务
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/3 * * * ?")).build();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }
}


