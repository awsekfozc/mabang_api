package com.smartdo.scc.mabang.JobScheduling.task;

import com.smartdo.scc.mabang.JobScheduling.job.MabangApiJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class MaBangApiTask {

    /**
     * 开启MabangApiJob的定时任务
     * @throws Throwable
     */
    public void taskStart() throws Throwable {
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        JobDetail job = JobBuilder.newJob(MabangApiJob.class).withIdentity("job1", "group1").build();
        // @NOTICE
        // 当前这个表达式("0 0 0/1 * * ?")的定义是每个时是1的倍数，或者是0的的整点时候，都触发任务
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0/1 * * ?")).build();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }
}


