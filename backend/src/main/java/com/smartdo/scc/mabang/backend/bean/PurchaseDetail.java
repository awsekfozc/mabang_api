package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

/**
 *采购单商品
 */
@Data
public class PurchaseDetail {
    private String groupId ; //采购单号  （用作与ProductPurchaseInfo做唯一性关联）

    private String purchaseNum;// 	采购数量
    private String sellPrice;// 	采购单价
    private String remark;// 备注
    private String stockSku;// 	商品sku
    private String nameCN;// 	商品中文名称
    private String nameEN;// 	商品英文名称
    private String originalSku;//  	原厂sku
    private String stockPicture;//  	商品图片
    private String weight;// 	商品单品重量

}
