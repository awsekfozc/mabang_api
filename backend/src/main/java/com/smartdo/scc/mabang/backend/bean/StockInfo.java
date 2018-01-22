package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

/**
 * 1.1 商品信息
 */
@Data
public class StockInfo extends DataEntity {
    private String stockId;//	商品编号
    private String stockSku;//		库存sku
    private String originalSku;//	原始sku
    private String nameCN;//	中文名称
    private String nameEN;//	英文名称
    private String stockQuantity;//	商品总库存
    private String status;//	状态：1.自动创建	2.待开发	3.正常销售	4.清仓	5.停止销售
    private String parentCategoryId;//	一级商品目录
    private String categoryId;//	二级商品目录
    private String stockPicture;//		商品图片
    private String remark;//备注
    private String timeCreated;//创建时间
    private String updateTime;//最后更新时间
    private String salePrice;//售价
    private String updateDate1;//最后出库时间
    private String updateDate2;//	最后入库时间
    private String defaultCost;//统一成本价
    private String waitingQuantity;//	未发货量
    private String shippingQuantity;//	在途量
    private String isNewType;//新款：1是	0否
    private String livenessType;//	活跃度:1:爆款;2:旺款;3:平款;4:滞销款;
    private String quantityInterval7;//近7天发货量
    private String quantityInterval28;//	近28天发货量
    private String quantityInterval42;//	近42天发货量
    private String declareName;//申报中文名称
    private String declareEname;//	申报英文名称
    private String declareValue;//	申报价格$
    private String weight;//单品重量
    private String declareCode;//	报关编号
    private String hasBattery;//	含电池：	1.是	2.否
    private String isTort;//	侵权：1.是	2.否
    private String magnetic;//	带磁	1.是	2.否
    private String noLiquidCosmetic;//	0:非液体,2:液体(化妆品),1:非液体(化妆品),3:液体(非化妆品)
    private String powder;//粉末:1:是;2:不是
    private String length;//	长
    private String width;//宽
    private String height;//	高
    private String volume;//体积重
    private String developerId;//	开发员
    private String salesId;//	销售员
    private String packageId;//包材编号
    private String packageCount;//	可包材个数
    private String isMachining;//	是否有加工商品	1:有	2	没有
    private String retailId;//供应商编号

}
