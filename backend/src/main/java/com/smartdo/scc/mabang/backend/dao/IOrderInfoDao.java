package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.OrderInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

public interface IOrderInfoDao {
    //此处无order_item字段！！！
    @Insert(" INSERT INTO order_info(oper_time,buyer_name,buyer_user_id,country_code,country_name_en,country_name_cn,express_time,my_logistics_id,my_logistics_channel_id,order_cost,order_fee,order_status,order_weight,paid_time,platform_id,platform_order_id,sales_record_number,shop_id,track_number,track_number1,track_number2,close_date,province,city,street1,street2,doorcode,email,phone1,phone2,post_code,carrier_code,fba_flag,fba_start_date_time,fba_end_date_time,currency_id,currency_rate,fba_commission,fba_weight_based_fee,fba_per_unit_fulfillment_fee,fba_per_order_fulfillment_fee,c_o_d_charge,alliance_fee,insurance_fee,item_total,item_total_cost,package_fee,paypal_fee,platform_fee,promotion_amount,refund_fee_currency_id,shipping_cost,shipping_fee,shipping_pre_cost)" +
            " VALUES(#{operTime},#{buyerName},#{buyerUserId},#{countryCode},#{countryNameEN},#{countryNameCN},#{expressTime},#{myLogisticsId},#{myLogisticsChannelId},#{orderCost},#{orderFee},#{orderStatus},#{orderWeight},#{paidTime},#{platformId},#{platformOrderId},#{salesRecordNumber},#{shopId},#{trackNumber},#{trackNumber1},#{trackNumber2},#{closeDate},#{province},#{city},#{street1},#{street2},#{doorcode},#{email},#{phone1},#{phone2},#{postCode},#{CarrierCode},#{fbaFlag},#{fbaStartDateTime},#{fbaEndDateTime},#{currencyId},#{currencyRate},#{fbaCommission},#{fbaPerOrderFulfillmentFee},#{fbaPerUnitFulfillmentFee},#{fbaWeightBasedFee},#{CODCharge},#{allianceFee},#{insuranceFee},#{itemTotal},#{itemTotalCost},#{packageFee},#{paypalFee},#{platformFee},#{promotionAmount},#{refundFeeCurrencyId},#{shippingCost},#{shippingFee},#{shippingPreCost})")
    int add(OrderInfo rderInfo);

    //删除表中多余的重复记录，重复记录是根据单个字段（platform_order_id）来判断，只留有id最大的记录,（因为数据库的id是自增长，大者为后更新）
    @Delete(" delete from order_info " +
            " where platform_order_id in( select temp_a from (select  platform_order_id as temp_a from order_info  group  by  platform_order_id   having  count(platform_order_id) > 1)a)"+
            " and id not in( select temp_b from(select max(id) as temp_b from  order_info  group by platform_order_id  having count(platform_order_id )>1)b)")
    int removeDuplicates();
}
