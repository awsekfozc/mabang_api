package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

/**
 * 1.8 企业采购商品入库信息
 */
@Data
public class ProductPurchaseStorageInInfo {
    private String purchaseGroups;  //   采购单号(条件中)
    private String purchaseGroup;  //  	采购单号
    //PurchaseDetail 	采购单商品
    private String sku;  //  	库存sku
    private String storageNum;  //  	已入库量
    private String damagedNum;  // 	损耗量
}
