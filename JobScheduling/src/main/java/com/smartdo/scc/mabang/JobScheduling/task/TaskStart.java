package com.smartdo.scc.mabang.JobScheduling.task;

import com.smartdo.scc.mabang.JobScheduling.job.MabangApiInit;
import org.junit.Test;

public class TaskStart {

    @Test
    public void Start() throws Throwable {
        //初始化数据库，进行初始数据填充
        MabangApiInit mabangApiInit = new MabangApiInit();
        mabangApiInit.allDBInit();
        //开启定时更新任务
        MaBangApiTask maBangApiTask = new MaBangApiTask();
        maBangApiTask.taskStart();
    }

}
