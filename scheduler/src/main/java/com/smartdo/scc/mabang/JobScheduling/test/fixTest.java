package com.smartdo.scc.mabang.JobScheduling.test;

import cn.hutool.core.date.DateUtil;
import com.smartdo.scc.mabang.JobScheduling.job.MabangApiJob;
import com.smartdo.scc.mabang.JobScheduling.task.MaBangApiTask;
import com.smartdo.scc.mabang.backend.bean.Scheduling;
import com.smartdo.scc.mabang.backend.service.SchedulingService;
import org.junit.Test;

import java.util.Date;

public class fixTest {
    @Test
    public void fixTest() throws Throwable {
        //拿到所有原始数据（包括：1.无更新区间的数据，2.有更新区间的数据）
        MabangApiJob mAJ = new MabangApiJob();
        Date d1 = null;
        Date d2 = null;
        mAJ.fbaInfoApi(d1,d2);               //1.6
        mAJ.productPurchaseInfoApi(d1,d2);   //1.7
        mAJ.stockStorageLogApi(d1,d2);       //1.9


        //获取特定更新区间的数据，插入后进行去重
        String dateStr = "1900-01-01 00:00:00";
        Date updateTimeStart = DateUtil.parse(dateStr);
        Date updateTimeEnd = DateUtil.date();
        Scheduling scheduling = new Scheduling();
        scheduling.setUpdateTimeStart(updateTimeStart);
        scheduling.setUpdateTimeEnd(updateTimeEnd);

        //========  1.2, 1.3必须建立在1.1完成的基础上
        mAJ.stockInfoApi(updateTimeStart, updateTimeEnd); //1.1 带时间更新区间的都有偏差！！！
        mAJ.stockWarehouseInfoApi(scheduling);//1.2
        mAJ.stockMachiningInfoApi(scheduling);//1.3
        mAJ.stockProviderInfoApi(updateTimeStart, updateTimeEnd);//1.4
        mAJ.fbaInfoApi(updateTimeStart, updateTimeEnd);//1.6
        // ========= 1.8必须建立在1.7完成的基础上
        mAJ.productPurchaseInfoApi(updateTimeStart, updateTimeEnd);//1.7
        mAJ.productPurchaseStorageInInfoApi(scheduling);//1.8

        mAJ.stockStorageLogApi(updateTimeStart, updateTimeEnd);//1.9

        //1.5的数据量最多
        mAJ.orderInfoApi(updateTimeStart, updateTimeEnd);//1.5

        //上述所有调用与数据库操作无异常，就存入本次更新区间（如果上面抛出异常失败，本次不会存入本次更新区间，那么下次更新就使用上次的更新结束时间作为区间起点，调用后即可修正）
        SchedulingService schedulingService = new SchedulingService();
        schedulingService.add(scheduling);

        //开启定时更新任务
        MaBangApiTask maBangApiTask = new MaBangApiTask();
        maBangApiTask.taskStart();
    }


    public static void main(String[] args) throws Exception {
        MabangApiJob mAJ = new MabangApiJob();
        Date d1 = null;
        Date d2 = null;
        mAJ.stockStorageLogApi(d1,d2);       //1.9
    }


}