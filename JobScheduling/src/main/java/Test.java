import com.smartdo.scc.mabang.JobScheduling.jobs.MabangApiJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class Test {
    public static void main(String[] args) throws Throwable {
        SchedulerFactory factory = new StdSchedulerFactory();
        // 从工厂里面拿到一个scheduler实例
        Scheduler scheduler = factory.getScheduler();
        // 计算任务的开始时间，DateBuilder.evenMinuteDate方法是取下一个整数分钟
        Date runTime = DateBuilder.evenMinuteDate(new Date());
        // 真正执行的任务并不是Job接口的实例，而是用反射的方式实例化的一个JobDetail实例
//        JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
        JobDetail job = JobBuilder.newJob(MabangApiJob.class).withIdentity("job1", "group1").build();
        // 定义一个触发器，startAt方法定义了任务应当开始的时间
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
        // @NOTICE
        // 与SimpleTrigger对比：类不同了，现在的是Trigger的子类CronTrigger；withSchedule中的参数变为CronScheduleBuilder了
        // CronScheduleBuilder可以通过类似"0/13 * * * * ?"这种表达式来创建定时任务
        // 当前这个表达式的定义是每个秒是13的倍数，或者是0的时候，都触发任务
        //CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
         //       .withSchedule(CronScheduleBuilder.cronSchedule("0/13 * * * * ?")).build();

        // 将任务和Trigger放入scheduler
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
        try {
            // 等待65秒，保证下一个整数分钟出现，这里注意，如果主线程停止，任务是不会执行的
            Thread.sleep(65L * 1000L);
        } catch (Exception e) {

        }
        // scheduler结束
        scheduler.shutdown(true);
    }
}

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
