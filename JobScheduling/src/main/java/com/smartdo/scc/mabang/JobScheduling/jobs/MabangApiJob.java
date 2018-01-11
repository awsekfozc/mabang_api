package com.smartdo.scc.mabang.JobScheduling.jobs;

import com.smartdo.scc.mabang.backend.MabangAPI;
import com.smartdo.scc.mabang.backend.pipe.*;
import com.smartdo.scc.mabang.backend.request.*;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MabangApiJob implements Job {
    public void testStockInfoApi() {
        StockInfoRequst request = new StockInfoRequst(); // 1 验证
        MabangAPI.create(request)
                .setPipeline(new StockInfoPipeline())
                .start();
    }

    public void testStockWarehouseInfoApi() {
        StockWarehouseInfoRequest request = new StockWarehouseInfoRequest(); // 2 验证
        request.setStockIds("1338956,1338991");
        MabangAPI.create(request)
                .setPipeline(new StockWarehouseInfoPipeline())
                .start();
    }

    public void testStockMachiningInfoApi() {
        StockMachiningInfoRequest request = new StockMachiningInfoRequest(); // 3 验证
        request.setStockIds("1338956,1338991"); //有加工信息
        MabangAPI.create(request)
                .setPipeline(new StockMachiningInfoPipeline())
                .start();
    }

    public void testStockProviderInfoApi() {
        StockProviderInfoRequest request = new StockProviderInfoRequest(); // 4 没有测试！

        MabangAPI.create(request)
                .setPipeline(new StockProviderInfoPipeline())
                .start();
    }

    public void testOrderInfoApi() {
        OrderInfoRequest request = new OrderInfoRequest(); //5 验证
        request.setPage(1);
        request.setTableBase(1);

        MabangAPI.create(request)
                .setPipeline(new OrderInfoPipeline())
                .start();
    }

    public void testFbaInfoApi() {
        FbaInfoRequst request = new FbaInfoRequst();   // 6 验证
        request.setPage(1);

        MabangAPI.create(request)
                .setPipeline(new FbaInfoPipeline())
                .start();
    }

    public void testProductPurchaseInfoApi() {
        ProductPurchaseInfoRequest productPurchaseInfoRequest = new ProductPurchaseInfoRequest(); //7
        productPurchaseInfoRequest.setPage(1);
        MabangAPI.create(productPurchaseInfoRequest)
                .setPipeline(new ProductPurchaseInfoPipeline())
                .start();
    }

    public void testProductPurchaseStorageInInfoApi() {
        ProductPurchaseStorageInInfoRequest request = new ProductPurchaseStorageInInfoRequest(); //8
        request.setPurchaseGroups("1100000191");
        MabangAPI.create(request)
                .setPipeline(new ProductPurchaseStorageInInfoPipeline())
                .start();
    }
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        System.out.println("TTT任务正在执行，执行时间: " + Calendar.getInstance().getTime());
        testProductPurchaseStorageInInfoApi();
    }
}
