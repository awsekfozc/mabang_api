package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

import java.util.Date;

/**
 * 1.4 获取商品供应商信息
 */
@Data
public class StockProviderInfo extends DataEntity {

    private String stockId; //	商品编号
    private String provider; //	供应商名称
    private String contactPerson; //	联系人
    private String contactTel; //	联系电话
    private String linkAddress; //	供应商链接
    private String contactProvince; // 	供应商省份
    private String contactAddress; //	供应商详细地址

    private String deleteFlag; // 	状态1、未删除  2、已删除
    private Date operTime; //	最后更新时间
    private String payee; //	收款人
    private String receiveTermId; //	收款方式 1. 转账 2余额宝3网银4支付宝5现款6 paypal
    private String receiveAccount; //	收款账号
    private String paymentType; // 	付款方式：1、到付 2、分期付款
    private String buyerId; //	采购员
    private String temporaryBuyerId; //	临时采购员
    private String content; //备注
    private String arrivalCycle; //	到货周期/天

}
