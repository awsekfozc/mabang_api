package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Scheduling {
    protected String interfaceCode;  //接口代号  用A,B，C...代指各类接口，
    protected String isSuccess;     //本次调用接口及存入数据库是否成功
    protected Date updateTimeStart; // 	 	最后更新开始时间(本地确定时间区间条件查询，不一定等同于第三方的更新区间)
    protected Date updateTimeEnd; // 	 	最后更新结束时间(本地确定时间区间条件查询，不一定等同于第三方的更新区间)

    /*interfaceCode;  接口代号解释（数据库注释）*/
    /*
   ---------------------------------------------------------
    A: 1.1 获取商品信息 get-stock-info-data,
    B: 1.2 获取商品仓库信息 get-stock-warehouse-info-data
    C: 1.3 获取商品加工信息 get-stock-machining-info-data
    D: 1.4 获取商品供应商信息 get-stock-provider-info-data
    E: 1.5 获取企业订单信息	get-order-info-data
    F: 1.6 获取企业 FBA 商品信息 get-fba-info-data
    G: 1.7 获取企业采购商品信息 get-product-purchase-info-data
    H: 1.8 获取企业采购商品入库信息 get-product-purchase-storage-in-info-data
    I: 1.9 获取商品出入库流水信息 get-stock-storage-log-data
    ----------------------------------------------------------
    */

}
