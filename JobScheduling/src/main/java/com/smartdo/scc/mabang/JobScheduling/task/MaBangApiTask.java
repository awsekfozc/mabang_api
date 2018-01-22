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


//--------------------------------------
//      每隔5秒执行一次：*/5 * * * * ?
//      每隔1分钟执行一次：0 */1 * * * ?
//      每天23点执行一次：0 0 23 * * ?
//      每天凌晨1点执行一次：0 0 1 * * ?
//      每月1号凌晨1点执行一次：0 0 1 1 * ?
//      每月最后一天23点执行一次：0 0 23 L * ?
//      每周星期天凌晨1点实行一次：0 0 1 ? * L
//      在26分、29分、33分执行一次：0 26,29,33 * * * ?
//      每天的0点、13点、18点、21点都执行一次：0 0 0,13,18,21 * * ?

/*
--------------------------------------
    0 0 12 * * ?            每天12点触发
    0 15 10 ? * *           每天10点15分触发
    0 15 10 * * ?           每天10点15分触发
    0 15 10 * * ? *         每天10点15分触发
    0 15 10 * * ? 2005      2005年每天10点15分触发
    0 * 14 * * ?            每天下午的 2点到2点59分每分触发
    0 0/5 14 * * ?          每天下午的 2点到2点59分(整点开始，每隔5分触发)
    0 0/5 14,18 * * ?       每天下午的 2点到2点59分(整点开始，每隔5分触发) 每天下午的 18点到18点59分(整点开始，每隔5分触发)
    0 0-5 14 * * ?          每天下午的 2点到2点05分每分触发
    0 10,44 14 ? 3 WED      3月分每周三下午的 2点10分和2点44分触发
    0 15 10 ? * MON-FRI     从周一到周五每天上午的10点15分触发
    0 15 10 15 * ?          每月15号上午10点15分触发
    0 15 10 L * ?           每月最后一天的10点15分触发
    0 15 10 ? * 6L          每月最后一周的星期五的10点15分触发
    0 15 10 ? * 6L 2002-2005    从2002年到2005年每月最后一周的星期五的10点15分触发
    0 15 10 ? * 6#3         每月的第三周的星期五开始触发
    0 0 12 1/5 * ?          每月的第一个中午开始每隔5天触发一次
    0 11 11 11 11 ?         每年的11月11号 11点11分触发(光棍节)
--------------------------------------
 */

