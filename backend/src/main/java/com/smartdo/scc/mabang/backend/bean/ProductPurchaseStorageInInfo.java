package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

/**
 * 1.8 企业采购商品入库信息
 */
@Data
public class ProductPurchaseStorageInInfo extends DataEntity{
    private int purchaseGroup;  //  	采购单号

    private String sku;  //  	库存sku
    private int storageNum;  //  	已入库量
    private int damagedNum;  // 	损耗量
}
