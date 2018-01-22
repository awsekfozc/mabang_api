package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

/**
 * 1.5 获取企业订单信息
 */
@Data
public class OrderInfo extends DataEntity{
    private String buyerName;   //买家姓名
    private String buyerUserId;   //	买家账号
    private String countryCode;   //	国家码
    private String countryNameEN;   //	国家英文名称
    private String countryNameCN;   //国家中文名称
    private String expressTime;   //发货时间
    private String myLogisticsId;   //物流公司名称
    private String myLogisticsChannelId;   //	物流渠道名称
    private String orderCost;   //订单成本价
    private String orderFee;   //订单金额
    private String orderStatus;   //	订单状态
    private String orderWeight;   //	订单重量
    private String paidTime;   //付款时间
    private String platformId;   //平台名称
    private String platformOrderId;   //	订单号
    private String salesRecordNumber;   //	交易号
    private String shopId;   //店铺名称
    private String trackNumber;   //物流单号
    private String trackNumber1;   //内部单号
    private String trackNumber2;   //虚拟单号
    private String closeDate;   //	交易关闭时间
    private String province;   //省/州
    private String city;   //城市
    private String street1;   //	邮寄地址
    private String street2;   //备用地址
    private String doorcode;   //门牌号
    private String email;   //邮箱
    private String phone1;   //	联系电话1
    private String phone2;   //	联系电话2
    private String postCode;   //邮编
    private String CarrierCode;   //	fba承运人
    private String fbaFlag;   //亚马逊平台时：1.FBA 2.FBM
    private String fbaStartDateTime;   //	fba配送开始时间
    private String fbaEndDateTime;   //fba配送结束时间
    private String currencyId;   //订单原始币种
    private String currencyRate;   //订单人民币汇率
    private String fbaCommission;   //亚马逊平台佣金
    private String fbaPerOrderFulfillmentFee;   //	FBA每笔订单配送服务费
    private String fbaPerUnitFulfillmentFee;   //	亚马逊物流基础服务费
    private String fbaWeightBasedFee;   //fba亚马逊物流配送费
    private String CODCharge;   //fba货到付款金额
    private String allianceFee;   //联盟佣金
    private String insuranceFee;   // 保险费
    private String itemTotal;   // 	商品总售价
    private String itemTotalCost;   //  	商品总成本
    private String packageFee;   //  	包材费
    private String paypalFee;   //  	贝宝转账费
    private String platformFee;   // 平台费
    private String promotionAmount;   // 折扣RMB金额
    private String refundFeeCurrencyId;   // 	退款金额币种
    private String shippingCost;   //  	真实物流运费
    private String shippingFee;   //  	物流总收入
    private String shippingPreCost;   //  	估算物流运费

    private String operTime;    //最近更新时间

    /*start  此处注释，后期验证，注意SQL中字段！！*/
    //    private String orderItem;   // 详情com.smartdo.scc.mabang.backend.bean.OrderItem类
    /*end*/
}
