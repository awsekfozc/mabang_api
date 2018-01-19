package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

/**
 * OrderItem
 */
@Data
public class OrderItem extends DataEntity{
    private String platformOrderId;   //订单编号  (用做与OrderInfo做唯一性关联)

    private String costPrice;   // 	商品成本价
    private String isCombo;   // 	是否是组合 1:是 2:否
    private String itemId;   // 	itemId
    private String pictureUrl;   // 	商品图片
    private String platformQuantity;   // 	平台数量
    private String platformSku;   // 平台sku
    private String productUnit;   // 	产品单位
    private String quantity;   // 数量
    private String sellPrice;   // 售价
    private String specifics;   // 多属性
    private String status;   //状态1未付款订单不发货2已付款订单暂不发货3已发货4作废
    private String stockGrid;   //	仓位
    private String stockWarehouseId;   // 	仓库
    private String stockSku;   //	库存sku
    private String title;   //	商品标题
    private String unitWeight;   //	单品重量
    private String transactionId;   //	transactionId

}



