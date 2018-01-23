package com.smartdo.scc.mabang.JobScheduling.job;

import cn.hutool.core.date.DateUtil;
import com.smartdo.scc.mabang.backend.MabangAPI;
import com.smartdo.scc.mabang.backend.bean.Scheduling;
import com.smartdo.scc.mabang.backend.pipe.*;
import com.smartdo.scc.mabang.backend.request.*;
import com.smartdo.scc.mabang.backend.service.ProductPurchaseStorageInInfoService;
import com.smartdo.scc.mabang.backend.service.SchedulingService;
import com.smartdo.scc.mabang.backend.service.StockMachiningInfoService;
import com.smartdo.scc.mabang.backend.service.StockWarehouseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.List;

/**
 * 定时任务 的Job类
 */
@Slf4j
public class MabangApiJob implements Job {
    /**
     * 定时任务的方法
     *
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            newStockInfoApi();

            stockWarehouseInfoApi();
            stockMachiningInfoApi();
            newStockProviderInfoApi();
            newFbaInfoApi();
            newProductPurchaseInfoApi();
            productPurchaseStorageInInfoApi();
            newStockStorageLogApi();

            newOrderInfoApi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void firstInit() {
        try {
            newStockInfoApi();

            stockWarehouseInfoApi();
            stockMachiningInfoApi();
            newStockProviderInfoApi();
            newFbaInfoApi();
            newProductPurchaseInfoApi();
            productPurchaseStorageInInfoApi();
            newStockStorageLogApi();

            newOrderInfoApi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 调用stockInfo接口更新
     *
     * @param updateTimeStart
     * @param updateTimeEnd
     */
    public boolean stockInfoApi(Date updateTimeStart, Date updateTimeEnd) {
        try {
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
        } catch (Exception ex) {
            log.error("stockInfo接口更新过程失败", ex);
            return false;
        }
        return true;
    }


    /**
     * 取日期，调用stockInfoApi接口更新,存入接口调用信息
     */

    public void newStockInfoApi() {
        SchedulingService schedulingService = new SchedulingService();
        Scheduling scheduling = new Scheduling();
        scheduling.setInterfaceCode("A");
        Date updateTimeEnd = DateUtil.date();
        List<Scheduling> list = schedulingService.getUpdateTime(scheduling);
        if ((list.size() == 0) || (list == null)) {
            //全量
            boolean status = stockInfoApi(null, null);
            scheduling.setUpdateTimeEnd(updateTimeEnd);
            saveUpdateTime(status, scheduling);
        } else {
            //增量
            scheduling = list.get(0);
            Date updateTimeStart = scheduling.getUpdateTimeEnd();
            scheduling.setUpdateTimeStart(updateTimeStart);
            scheduling.setUpdateTimeEnd(updateTimeEnd);
            boolean status = stockInfoApi(updateTimeStart, updateTimeEnd); //1.1
            saveUpdateTime(status, scheduling);
        }
    }

    /**
     * 存入接口调用信息
     *
     * @param flag
     * @param scheduling
     */
    public void saveUpdateTime(Boolean flag, Scheduling scheduling) {
        SchedulingService schedulingService = new SchedulingService();
        if (flag) {
            scheduling.setIsSuccess("0");
            schedulingService.add(scheduling);
        } else {
            scheduling.setIsSuccess("1");
            schedulingService.add(scheduling);
        }
    }

    /**
     * 调用stockWarehouseInfoApi接口
     */
    public boolean stockWarehouseInfoApi() {  //1.2
        try {
            StockWarehouseInfoService stockWarehouseInfoService = new StockWarehouseInfoService();
            List<String> resultList = stockWarehouseInfoService.getStockId();

            StockWarehouseInfoRequest request = new StockWarehouseInfoRequest();
            for (int i = 0; i < resultList.size(); i++) {
                request.setStockIds(resultList.get(i));
                MabangAPI.create(request)
                        .setPipeline(new StockWarehouseInfoPipeline())
                        .start();
            }
        } catch (Exception ex) {
            log.error("stockWarehouseInfo接口更新过程失败", ex);
            return false;
        }
        return true;
    }

    /**
     * 调用stockMachiningInfoApi接口
     */
    public boolean stockMachiningInfoApi() {
        try {
            StockMachiningInfoService stockMachiningInfoService = new StockMachiningInfoService();
            List<String> resultList = stockMachiningInfoService.getStockId();
            StockMachiningInfoRequest request = new StockMachiningInfoRequest();
            for (int i = 0; i < resultList.size(); i++) {
                request.setStockIds(resultList.get(i));
                MabangAPI.create(request)
                        .setPipeline(new StockMachiningInfoPipeline())
                        .start();
            }
        } catch (Exception ex) {
            log.error("stockMachiningInfo接口更新过程失败", ex);
            return false;
        }
        return true;
    }

    /**
     * 调用stockProviderInfoApi接口
     *
     * @param updateTimeStart
     * @param updateTimeEnd
     */
    public boolean stockProviderInfoApi(Date updateTimeStart, Date updateTimeEnd) {  //1.4
        try {
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
        } catch (Exception ex) {
            log.error("stockProviderInfo接口增量过程失败", ex);
            return false;
        }
        return true;
    }

    /**
     * 取日期，调用stockProviderInfoApi接口,存入接口调用信息
     *
     * @return
     */
    public void newStockProviderInfoApi() {  //1.4
        SchedulingService schedulingService = new SchedulingService();
        Scheduling scheduling = new Scheduling();
        scheduling.setInterfaceCode("D");
        Date updateTimeEnd = DateUtil.date();
        List<Scheduling> list = schedulingService.getUpdateTime(scheduling);
        if ((list.size() == 0) || (list == null)) {
            //全量
            boolean status = stockProviderInfoApi(null, null);
            scheduling.setUpdateTimeEnd(updateTimeEnd);
            saveUpdateTime(status, scheduling);
        } else {
            scheduling = list.get(0);
            Date updateTimeStart = scheduling.getUpdateTimeEnd();
            scheduling.setUpdateTimeStart(updateTimeStart);
            scheduling.setUpdateTimeEnd(updateTimeEnd);
            boolean status = stockProviderInfoApi(updateTimeStart, updateTimeEnd);
            saveUpdateTime(status, scheduling);
        }
    }

    /**
     * 调用orderInfoApi接口
     *
     * @param updateTimeStart
     * @param updateTimeEnd
     */
    public boolean orderInfoApi(Date updateTimeStart, Date updateTimeEnd) {  //1.5
        try {
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
            //随着调用接口时间的增加，接口方数据量可能会变，page的总页数可能会变，所以采用了先确定总page，再更新的做法
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
        } catch (Exception ex) {
            log.error("OrderInfo接口更新过程失败", ex);
            return false;
        }
        return true;
    }

    /**
     * 取日期，调用OrderInfo接口,存入接口调用信息
     *
     * @return
     */
    public void newOrderInfoApi() {  //1.5
        SchedulingService schedulingService = new SchedulingService();
        Scheduling scheduling = new Scheduling();
        scheduling.setInterfaceCode("E");
        Date updateTimeEnd = DateUtil.date();
        List<Scheduling> list = schedulingService.getUpdateTime(scheduling);
        if ((list.size() == 0) || (list == null)) {
            //全量
            boolean status = orderInfoApi(null, null);
            scheduling.setUpdateTimeEnd(updateTimeEnd);
            saveUpdateTime(status, scheduling);
        } else {
            scheduling = list.get(0);
            Date updateTimeStart = scheduling.getUpdateTimeEnd();
            scheduling.setUpdateTimeStart(updateTimeStart);
            scheduling.setUpdateTimeEnd(updateTimeEnd);
            boolean status = orderInfoApi(updateTimeStart, updateTimeEnd);
            scheduling.setUpdateTimeEnd(updateTimeEnd);
            saveUpdateTime(status, scheduling);
        }
    }

    /**
     * 调用fbaInfoApi接口
     *
     * @param updateTimeStart
     * @param updateTimeEnd
     */
    public boolean fbaInfoApi(Date updateTimeStart, Date updateTimeEnd) {  //1.6
        try {
            FbaInfoRequst request = new FbaInfoRequst();
            request.setPage(1);
            request.setUpdateTimeStart(updateTimeStart);
            request.setUpdateTimeEnd(updateTimeEnd);

            MabangAPI mabangAPI = new MabangAPI(request);
            mabangAPI.setPipeline(new FbaInfoPipeline());
            mabangAPI.start();
            Integer integer = mabangAPI.getResponse().getPageCount();
            for (int i = 1; i < integer; i++) {
                request.setPage(i + 1);
                mabangAPI.start();
            }
        } catch (Exception ex) {
            log.error("1.6接口更新过程失败", ex);
            return false;
        }
        return true;
    }

    /**
     * 取日期，调用FbaInfo接口,存入接口调用信息
     *
     * @return
     */
    public void newFbaInfoApi() {  //1.6

        SchedulingService schedulingService = new SchedulingService();
        Scheduling scheduling = new Scheduling();
        scheduling.setInterfaceCode("F");
        Date updateTimeEnd = DateUtil.date();
        List<Scheduling> list = schedulingService.getUpdateTime(scheduling);
        if ((list.size() == 0) || (list == null)) {
            //全量
            boolean status = fbaInfoApi(null, null);
            scheduling.setUpdateTimeEnd(updateTimeEnd);
            saveUpdateTime(status, scheduling);
        } else {
            scheduling = list.get(0);
            Date updateTimeStart = scheduling.getUpdateTimeEnd();
            scheduling.setUpdateTimeStart(updateTimeStart);
            scheduling.setUpdateTimeEnd(updateTimeEnd);
            boolean status = fbaInfoApi(updateTimeStart, updateTimeEnd);
            saveUpdateTime(status, scheduling);
        }
    }

    /**
     * 调用productPurchaseInfoApi接口
     *
     * @param updateTimeStart
     * @param updateTimeEnd
     */
    public boolean productPurchaseInfoApi(Date updateTimeStart, Date updateTimeEnd) {  //1.7
        try {
            ProductPurchaseInfoRequest request = new ProductPurchaseInfoRequest(); //7
            request.setPage(1);
            request.setUpdateTimeStart(updateTimeStart);
            request.setUpdateTimeEnd(updateTimeEnd);

            MabangAPI mabangAPI = new MabangAPI(request);
            mabangAPI.setPipeline(new ProductPurchaseInfoPipeline());
            mabangAPI.start();
            Integer integer = mabangAPI.getResponse().getPageCount();
            for (int i = 1; i < integer; i++) {
                request.setPage(i + 1);
                mabangAPI.start();
            }
        } catch (Exception ex) {
            log.error("ProductPurchaseInfo接口更新过程失败", ex);
            return false;
        }
        return true;
    }

    /**
     * 取日期，调用FbaInfo接口,存入接口调用信息
     *
     * @return
     */
    public void newProductPurchaseInfoApi() {  //1.7

        SchedulingService schedulingService = new SchedulingService();
        Scheduling scheduling = new Scheduling();
        scheduling.setInterfaceCode("G");
        Date updateTimeEnd = DateUtil.date();
        List<Scheduling> list = schedulingService.getUpdateTime(scheduling);
        if ((list.size() == 0) || (list == null)) {
            //全量
            boolean status = productPurchaseInfoApi(null, null);
            scheduling.setUpdateTimeEnd(updateTimeEnd);
            saveUpdateTime(status, scheduling);
        } else {
            scheduling = list.get(0);
            Date updateTimeStart = scheduling.getUpdateTimeEnd();
            scheduling.setUpdateTimeStart(updateTimeStart);
            scheduling.setUpdateTimeEnd(updateTimeEnd);
            boolean status = productPurchaseInfoApi(updateTimeStart, updateTimeEnd);
            saveUpdateTime(status, scheduling);
        }
    }

    /**
     * 调用productPurchaseStorageInInfoApi接口
     */
    public boolean productPurchaseStorageInInfoApi() {   //1.8

        try {
            ProductPurchaseStorageInInfoService service = new ProductPurchaseStorageInInfoService();
            List<String> resultList = service.getGroupId();
            ProductPurchaseStorageInInfoRequest request = new ProductPurchaseStorageInInfoRequest();
            for (int i = 0; i < resultList.size(); i++) {
                request.setPurchaseGroups(resultList.get(i));
                MabangAPI.create(request)
                        .setPipeline(new ProductPurchaseStorageInInfoPipeline())
                        .start();
            }
        } catch (Exception ex) {
            log.error("productPurchaseStorageInInfo接口增量过程失败", ex);
            return false;
        }
        return true;
    }


    /**
     * 调用stockStorageLogApi接口
     *
     * @param updateTimeStart
     * @param updateTimeEnd
     */
    public boolean stockStorageLogApi(Date updateTimeStart, Date updateTimeEnd) {  //1.9
        try {
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
        } catch (Exception ex) {
            log.error("StockStorageLog接口增量过程失败", ex);
            return false;
        }
        return true;
    }

    /**
     * 取日期，调用StockStorageLog接口,存入接口调用信息
     *
     * @return
     */
    public void newStockStorageLogApi() {
        SchedulingService schedulingService = new SchedulingService();
        Scheduling scheduling = new Scheduling();
        scheduling.setInterfaceCode("I");
        Date updateTimeEnd = DateUtil.date();
        List<Scheduling> list = schedulingService.getUpdateTime(scheduling);
        if ((list.size() == 0) || (list == null)) {
            //全量
            boolean status = stockStorageLogApi(null, null);
            scheduling.setUpdateTimeEnd(updateTimeEnd);
            saveUpdateTime(status, scheduling);
        } else {
            scheduling = list.get(0);
            Date updateTimeStart = scheduling.getUpdateTimeEnd();
            scheduling.setUpdateTimeStart(updateTimeStart);
            scheduling.setUpdateTimeEnd(updateTimeEnd);
            boolean status = stockStorageLogApi(updateTimeStart, updateTimeEnd);
            saveUpdateTime(status, scheduling);
        }
    }
}
