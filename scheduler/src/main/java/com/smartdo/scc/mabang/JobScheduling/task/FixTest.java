package com.smartdo.scc.mabang.JobScheduling.task;

import com.smartdo.scc.mabang.JobScheduling.job.MabangApiJob;

public class FixTest {

    /**
     * 测试使用
     * @param args
     */
    public static void main(String[] args) {
        MabangApiJob mAJ = new MabangApiJob();
        mAJ.newStockInfoApi();
        mAJ.stockWarehouseInfoApi();
        mAJ.stockMachiningInfoApi();
        mAJ.newStockProviderInfoApi();
        mAJ.newFbaInfoApi();
        mAJ.newProductPurchaseInfoApi();
        mAJ.productPurchaseStorageInInfoApi();
        mAJ.newStockStorageLogApi();

        mAJ.newOrderInfoApi();
    }
}
