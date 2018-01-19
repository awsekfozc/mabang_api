package com.smartdo.scc.mabang.JobScheduling.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Calendar;

public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("任务正在执行，执行时间: " + Calendar.getInstance().getTime());
    }
}