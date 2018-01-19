package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

import java.util.Date;

/**
 * 1.6 企业FBA商品信息
 */
@Data
public class FbaInfo extends DataEntity{
    private String FNSKU;// 	FNSKU
    private String afnreservedquantity;// 	处理中(res)receiving
    private String afnreservedquantity1;// 	处理中(res)reserved
    private String amazonsite;// 站点
    private String asin;// asin
    private String conditions;// 	商品状态
    private String content;// 备注
    private String dailySales;// 	日均销量
    private String fourteenReturn;// 近14天退货量
    private String fourteenSales;// 近14天销量
    private String headShipping;// 头程运费
    private String inboundrecommendquantity;// 建议补货量
    private String isChange;// 	1.FBA 2.FBM
    private String ninetyReturn;// 近90天退货量
    private String ninetySales;// 近90天销量
    private String onWayQuantity;// 	在途量 afn-inbound-shipped-quantity
    private String pictureUrl;// 	商品图片
    private String platformSku;// 商品sku
    private String processQuantity;// 	平台未发货量
    private String purchasePrice;// 采购成本
    private String quantity;// 亚马逊可售库存
    private String salesDays;// 	销售天数
    private String sevenReturn;// 七天退货量
    private String sevenSales;// 七天销售量
    private String shippingType;// 运输方式 1：海运 2：空运
    private String status;// 状态 1:正常 2删除
    private String stockSku;// 	配对sku
    private String stockType;// 	1库存 2：组合
    private String stockStatus;// 	库存状态 1安全 2预警 3断货
    private String stockWarningDays;// 	警戒天数
    private String thirtyReturn;// 30天退货量
    private String thirtySales;// 30天销售量
    private String title;// 商品标题
    private String weight;// 	单品重量
    private Date LastsyncTime;// 	最后更新时间
    private String shopIds;//	店铺名称，多个已逗号隔开
    private Date openDate;//	上架时间
    private String yesterdaySales;//	昨日销量
    private String beforeYesterdaySales;//	前天销量
    private String threeDaysAgoSales;//	上前销量
    private String Transporttype1;//海运运输周期
    private String Transporttype2;//	空运运输周期
    private String Transporttype3;//	快递运输周期
    private String Transporttype4;//	陆运海运运输周期

    private String uniqueId;//	唯一标识符  后期与接口方沟通才添加,对应参数为返回数据中的id （接口文档中可能没有说明）

}
