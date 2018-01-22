package com.smartdo.scc.mabang.JobScheduling.job;

import cn.hutool.core.date.DateUtil;
import com.smartdo.scc.mabang.backend.MabangAPI;
import com.smartdo.scc.mabang.backend.bean.Scheduling;
import com.smartdo.scc.mabang.backend.pipe.*;
import com.smartdo.scc.mabang.backend.request.*;
import com.smartdo.scc.mabang.backend.service.*;
import org.junit.Test;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.List;

/**
 * 定时任务 的Job类
 */
public class MabangApiJob implements Job {

    /**
     * 定时任务的方法
     * @param context
     * @throws JobExecutionException
     */
    @Test
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            System.out.println(new Date());
            SchedulingService schedulingService = new SchedulingService();
            List list = schedulingService.getUpdateTime();
            System.out.println(list.size());
            if ((list.size() == 0) || (list == null)) {
                System.out.println("error");
                throw new JobExecutionException("获取上次的更新时间失败了，无法开启定时任务！");
            } else {
                System.out.println("correct");
                Scheduling scheduling = (Scheduling) list.get(0);
                Date updateTimeStart = scheduling.getUpdateTimeEnd();
                Date updateTimeEnd = DateUtil.date();


                //========  1.2, 1.3必须建立在1.1完成的基础上
                stockInfoApi(updateTimeStart, updateTimeEnd); //1.1
                stockWarehouseInfoApi(scheduling);//1.2
                stockMachiningInfoApi(scheduling);//1.3

                stockProviderInfoApi(updateTimeStart, updateTimeEnd);//1.4
                orderInfoApi(updateTimeStart, updateTimeEnd);//1.5
                fbaInfoApi(updateTimeStart, updateTimeEnd);//1.6
                //========= 1.8必须建立在1.7完成的基础上
                productPurchaseInfoApi(updateTimeStart, updateTimeEnd);//1.7
                productPurchaseStorageInInfoApi(scheduling);//1.8

                stockStorageLogApi(updateTimeStart, updateTimeEnd);//1.9

                //存入本次更新时间区间
                scheduling.setUpdateTimeStart(updateTimeStart);
                scheduling.setUpdateTimeEnd(updateTimeEnd);
                schedulingService.add(scheduling);
                System.out.println(scheduling);
                System.out.println(updateTimeStart);
                System.out.println(updateTimeEnd);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 测试用
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            System.out.println(new Date());
            SchedulingService schedulingService = new SchedulingService();
            List list = schedulingService.getUpdateTime();
            System.out.println(list.size());
            if ((list.size() == 0) || (list == null)) {
                System.out.println("error");
                throw new JobExecutionException("获取上次的更新时间失败了，无法开启定时任务！");
            } else {
                System.out.println("correct");
                Scheduling scheduling = (Scheduling) list.get(0);
                Date updateTimeStart = scheduling.getUpdateTimeEnd();
                Date updateTimeEnd = DateUtil.date();

                MabangApiJob mAJ = new MabangApiJob();

                //========  1.2, 1.3必须建立在1.1完成的基础上
                mAJ.stockInfoApi(updateTimeStart, updateTimeEnd); //1.1
                mAJ.stockWarehouseInfoApi(scheduling);//1.2
                mAJ.stockMachiningInfoApi(scheduling);//1.3

                mAJ.stockProviderInfoApi(updateTimeStart, updateTimeEnd);//1.4
                mAJ.orderInfoApi(updateTimeStart, updateTimeEnd);//1.5
                mAJ.fbaInfoApi(updateTimeStart, updateTimeEnd);//1.6
                //========= 1.8必须建立在1.7完成的基础上
                mAJ.productPurchaseInfoApi(updateTimeStart, updateTimeEnd);//1.7
                mAJ.productPurchaseStorageInInfoApi(scheduling);//1.8

                mAJ.stockStorageLogApi(updateTimeStart, updateTimeEnd);//1.9

                //存入本次更新时间区间
                scheduling.setUpdateTimeStart(updateTimeStart);
                scheduling.setUpdateTimeEnd(updateTimeEnd);
                schedulingService.add(scheduling);
                System.out.println(scheduling);
                System.out.println(updateTimeStart);
                System.out.println(updateTimeEnd);
                System.out.println("存入本次更新时间区间");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 调用stockInfoApi接口
     *
     * @param updateTimeStart
     * @param updateTimeEnd
     */
    @Test
    public void stockInfoApi(Date updateTimeStart, Date updateTimeEnd) throws Exception {  //1.1
        StockInfoRequst request = new StockInfoRequst();
        request.setPage(1);
        request.setUpdateTimeStart(updateTimeStart);
        request.setUpdateTimeEnd(updateTimeEnd);
        MabangAPI mabangAPI = new MabangAPI(request);
        mabangAPI.setPipeline(new StockInfoPipeline());
        mabangAPI.start();
        Integer integer = mabangAPI.getResponse().getPageCount();
        for (int i = 1; i < integer; i++) {
            request.setPage(i + 1);
            mabangAPI.start();
        }
        //去重
        StockInfoService stockInfoService = new StockInfoService();
        stockInfoService.removeDuplicates();
    }

    /**
     * 调用stockWarehouseInfoApi接口
     */
    @Test
    public void stockWarehouseInfoApi(Scheduling sheduling) throws Exception{  //1.2
        StockWarehouseInfoService stockWarehouseInfoService = new StockWarehouseInfoService();
        stockWarehouseInfoService.deleteAll(sheduling);
        List<String> resultList = stockWarehouseInfoService.getStockId();

        StockWarehouseInfoRequest request = new StockWarehouseInfoRequest();
        request.setUpdateTimeStart(sheduling.getUpdateTimeStart());
        request.setUpdateTimeEnd(sheduling.getUpdateTimeEnd());
        for (int i = 0; i < resultList.size(); i++) {
            request.setStockIds(resultList.get(i));
            MabangAPI.create(request)
                    .setPipeline(new StockWarehouseInfoPipeline())
                    .start();
        }
    }

    /**
     * 调用stockMachiningInfoApi接口
     *
     * @param sheduling
     */
    @Test
    public void stockMachiningInfoApi(Scheduling sheduling) throws Exception{   //1.3
        StockMachiningInfoService stockMachiningInfoService = new StockMachiningInfoService();
        stockMachiningInfoService.deleteAll(sheduling);
        List<String> resultList = stockMachiningInfoService.getStockId();

        StockMachiningInfoRequest request = new StockMachiningInfoRequest();
        request.setUpdateTimeStart(sheduling.getUpdateTimeStart());
        request.setUpdateTimeEnd(sheduling.getUpdateTimeEnd());
        for (int i = 0; i < resultList.size(); i++) {
            request.setStockIds(resultList.get(i));
            MabangAPI.create(request)
                    .setPipeline(new StockMachiningInfoPipeline())
                    .start();
        }

    }

    /**
     * 调用stockProviderInfoApi接口
     * @param updateTimeStart
     * @param updateTimeEnd
     */
    @Test
    public void stockProviderInfoApi(Date updateTimeStart, Date updateTimeEnd) throws Exception{  //1.4
        StockProviderInfoRequest request = new StockProviderInfoRequest();
        request.setPage(1);
        request.setUpdateTimeStart(updateTimeStart);
        request.setUpdateTimeEnd(updateTimeEnd);
        MabangAPI mabangAPI = new MabangAPI(request);
        mabangAPI.setPipeline(new StockProviderInfoPipeline());
        mabangAPI.start();
        Integer integer = mabangAPI.getResponse().getPageCount();
        for (int i = 1; i < integer; i++) {
            request.setPage(i + 1);
            mabangAPI.start();
        }
        //去重
        StockProviderInfoService stockProviderInfoService = new StockProviderInfoService();
        stockProviderInfoService.removeDuplicates();
    }

    /**
     * 调用orderInfoApi接口
     * @param updateTimeStart
     * @param updateTimeEnd
     */
    @Test
    public void orderInfoApi(Date updateTimeStart, Date updateTimeEnd) throws Exception{  //1.5

        OrderInfoRequest request1 = new OrderInfoRequest();
        OrderInfoRequest request2 = new OrderInfoRequest();
        request1.setTableBase(1);
        request1.setPage(1);
        request1.setUpdateTimeStart(updateTimeStart);
        request1.setUpdateTimeEnd(updateTimeEnd);

        request2.setTableBase(2);
        request2.setPage(1);
        request2.setUpdateTimeStart(updateTimeStart);
        request2.setUpdateTimeEnd(updateTimeEnd);

        MabangAPI mabangAPI1 = new MabangAPI(request1);
        mabangAPI1.setPipeline(new OrderInfoPipeline());
        MabangAPI mabangAPI2 = new MabangAPI(request2);
        mabangAPI2.setPipeline(new OrderInfoPipeline());
        Integer page1 = 1;
        Integer page2 = 1;
        try {
            mabangAPI1.sendRequest();
            mabangAPI1.initResponse();
            page1 = mabangAPI1.getResponse().getPageCount();
            mabangAPI2.sendRequest();
            mabangAPI2.initResponse();
            page2 = mabangAPI2.getResponse().getPageCount();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //随着调用接口时间的增加，接口方数据量会变，page的总页数会变，所以采用了先确定总page，再更新的做法
        OrderInfoRequest request = new OrderInfoRequest();
        for (int i = 1; i < 3; i++) {
            request.setTimestemp(System.currentTimeMillis() / 1000L);
            request.setTableBase(i);
            request.setPage(1);
            request.setUpdateTimeStart(updateTimeStart);
            request.setUpdateTimeEnd(updateTimeEnd);
            MabangAPI mabangAPI = new MabangAPI(request);
            mabangAPI.setPipeline(new OrderInfoPipeline());
            mabangAPI.start();
            Integer integer = 1;
            if (i == 1) {
                integer = page1;
            } else {
                integer = page2;
            }
            for (int j = 1; j < integer; j++) {
                request.setTimestemp(System.currentTimeMillis() / 1000L);
                request.setTableBase(i);
                request.setPage(j + 1);
                mabangAPI = new MabangAPI(request);
                mabangAPI.setPipeline(new OrderInfoPipeline());
                mabangAPI.start();
            }
        }
        //去重
        OrderInfoService orderInfoService = new OrderInfoService();
        orderInfoService.removeDuplicates();
    }

    /**
     * 调用fbaInfoApi接口
     * @param updateTimeStart
     * @param updateTimeEnd
     */
    @Test
    public void fbaInfoApi(Date updateTimeStart, Date updateTimeEnd) throws Exception{  //1.6
        FbaInfoRequst request = new FbaInfoRequst();
        request.setPage(1);
        request.setUpdateTimeStart(updateTimeStart);
        request.setUpdateTimeEnd(updateTimeEnd);

        MabangAPI mabangAPI = new MabangAPI(request);
        mabangAPI.setPipeline(new FbaInfoPipeline());
        mabangAPI.start();
        Integer integer = mabangAPI.getResponse().getPageCount();
        System.out.println(integer);
        for (int i = 1; i < integer; i++) {
            request.setPage(i + 1);
            mabangAPI.start();
        }
        //去重
        FbaInfoService fbaInfoService = new FbaInfoService();
        fbaInfoService.removeDuplicates();
    }

    /**
     * 调用productPurchaseInfoApi接口
     * @param updateTimeStart
     * @param updateTimeEnd
     */
    @Test
    public void productPurchaseInfoApi(Date updateTimeStart, Date updateTimeEnd) throws Exception{  //1.7

        ProductPurchaseInfoRequest request = new ProductPurchaseInfoRequest(); //7
        request.setPage(1);
        request.setUpdateTimeStart(updateTimeStart);
        request.setUpdateTimeEnd(updateTimeEnd);

        MabangAPI mabangAPI = new MabangAPI(request);
        mabangAPI.setPipeline(new ProductPurchaseInfoPipeline());
        mabangAPI.start();
        Integer integer = mabangAPI.getResponse().getPageCount();
        System.out.println(integer);
        for (int i = 1; i < integer; i++) {
            request.setPage(i + 1);
            mabangAPI.start();
        }
        //去重
        ProductPurchaseInfoService productPurchaseInfoService = new ProductPurchaseInfoService();
        productPurchaseInfoService.removeDuplicates();
    }

    /**
     * 调用productPurchaseStorageInInfoApi接口
     * @param sheduling
     */
    @Test
    public void productPurchaseStorageInInfoApi(Scheduling sheduling) throws Exception{   //1.8
        ProductPurchaseStorageInInfoService service = new ProductPurchaseStorageInInfoService();
        service.deleteAll(sheduling);
        List<String> resultList = service.getGroupId();

        ProductPurchaseStorageInInfoRequest request = new ProductPurchaseStorageInInfoRequest();
        request.setUpdateTimeStart(sheduling.getUpdateTimeStart());
        request.setUpdateTimeEnd(sheduling.getUpdateTimeEnd());
        for (int i = 0; i < resultList.size(); i++) {
            request.setPurchaseGroups(resultList.get(i));
            MabangAPI.create(request)
                    .setPipeline(new ProductPurchaseStorageInInfoPipeline())
                    .start();
        }
    }

    /**
     * 调用stockStorageLogApi接口
     * @param updateTimeStart
     * @param updateTimeEnd
     */
    @Test
    public void stockStorageLogApi(Date updateTimeStart, Date updateTimeEnd) throws Exception{  //1.9
        StockStorageLogRequest request = new StockStorageLogRequest();
        request.setPage(1);
        request.setUpdateTimeStart(updateTimeStart);
        request.setUpdateTimeEnd(updateTimeEnd);
        MabangAPI mabangAPI = new MabangAPI(request);
        mabangAPI.setPipeline(new StockStorageLogPipeline());
        mabangAPI.start();
        Integer integer = mabangAPI.getResponse().getPageCount();
        for (int i = 1; i < integer; i++) {
            request.setPage(i + 1);
            mabangAPI.start();
        }
//        //去重
//        StockStorageLogService stockStorageLogService = new StockStorageLogService();
//        stockStorageLogService.removeDuplicates();
    }

}
