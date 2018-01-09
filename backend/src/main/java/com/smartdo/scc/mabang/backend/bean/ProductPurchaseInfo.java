package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

/**
 * 1.7 企业采购商品信息
 */
@Data
public class ProductPurchaseInfo {
    private String groupId;// 	采购单号
    private String orderBillNO;// 自定义单号
    private String purchaseOrderType;// 	采购单类型:1、正品采购	2、样品采购
    private String paymentStatus;// 采购单支付状态:1、未支付	2、已申请	3、部分支付	4、已完成
    private String flag;//	2.待审核	4、采购中	8、部分到货	10、已完成	11、异常	12、已作废
    private String expressId;//	货运单号（多个以；分开）
    private String createTime;//	下单时间
    private String targetWarehouseId;//	采购仓库
    private String checkTime;//审核时间
    private String estimatedTime;//	预计到货时间
    private String createOperId;//下单员
    private String buyerId;//采购员
    private String amount;//	总金额
    private String expressMoney;//	总运费
    private String discountAmount;//	折扣
    private String taxAmount;//	税金
    private String content;//	备注
    private String provider;//	供应商名称
    private String contactAddress;//	供应商地址
    private String ali1688OrderId;//	1688订单号

    /*start*/
    /*private String  PurchaseDetail ;//	采购单商品  详情见同包目录的PurchaseDetail类*/
    /*end*/

}
