package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

/**
 * 1.4 获取商品供应商信息
 */
@Data
public class StockProviderInfo {
    private String stockIds; //3.0 库存 sku 编号
    private String stockId; //商品编号
    private String provider;//供应商名称
    private String contactPerson;//联系人
    private String contactTel;//联系电话
    private String linkAddress;//供应商链接
    private String contactProvince;//供应商省份
    private String contactAddress;//供应商详细地址

}
