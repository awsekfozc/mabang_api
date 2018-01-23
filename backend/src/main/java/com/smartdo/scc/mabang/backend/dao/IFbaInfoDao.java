package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.FbaInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IFbaInfoDao {
    @Insert(" INSERT INTO fba_info (unique_id,lastsync_time,shop_ids,open_date,yesterday_sales,before_yesterday_sales, three_days_ago_sales, transporttype1, transporttype2, transporttype3, transporttype4, f_n_s_k_u, afnreservedquantity, afnreservedquantity1, amazonsite, asin, conditions, content, daily_sales, fourteen_return, fourteen_sales, head_shipping, inboundrecommendquantity, is_change, ninety_return, ninety_sales, on_way_quantity, picture_url, platform_sku, process_quantity, purchase_price, quantity, sales_days, seven_return, seven_sales, shipping_type, status, stock_sku, stock_type, stock_status, stock_warning_days, thirty_return,thirty_sales, title, weight )" +
            " VALUES (#{uniqueId},#{LastsyncTime},#{shopIds},#{openDate},#{yesterdaySales},#{beforeYesterdaySales},#{threeDaysAgoSales},#{Transporttype1},#{Transporttype2},#{Transporttype3},#{Transporttype4},#{FNSKU},#{afnreservedquantity},#{afnreservedquantity1},#{amazonsite},#{asin},#{conditions},#{content},#{dailySales},#{fourteenReturn},#{fourteenSales},#{headShipping},#{inboundrecommendquantity},#{isChange},#{ninetyReturn},#{ninetySales},#{onWayQuantity},#{pictureUrl},#{platformSku},#{processQuantity},#{purchasePrice},#{quantity},#{salesDays},#{sevenReturn},#{sevenSales},#{shippingType},#{status},#{stockSku},#{stockType},#{stockStatus},#{stockWarningDays},#{thirtyReturn},#{thirtySales},#{title},#{weight})")
    int add(FbaInfo fbaInfo);

    @Select("SELECT count(*) FROM fba_info where unique_id = #{uniqueId}")
    int IsExist(FbaInfo fbaInfo);

    @Update(" update fba_info set lastsync_time = #{LastsyncTime},shop_ids = #{shopIds},open_date = #{openDate},yesterday_sales = #{yesterdaySales},before_yesterday_sales = #{beforeYesterdaySales}, three_days_ago_sales = #{threeDaysAgoSales}, transporttype1 =#{Transporttype1}, transporttype2=#{Transporttype2}, transporttype3 = #{Transporttype3}, transporttype4 = #{Transporttype4}, f_n_s_k_u = #{FNSKU}, afnreservedquantity =#{afnreservedquantity} , afnreservedquantity1 = #{afnreservedquantity1}, amazonsite =  #{amazonsite}, asin = #{asin}, conditions = #{conditions}, content = #{content}, daily_sales = #{dailySales}, fourteen_return = #{fourteenReturn}, fourteen_sales = #{fourteenSales}, head_shipping = #{headShipping}, inboundrecommendquantity = #{inboundrecommendquantity}, is_change = #{isChange}, ninety_return =#{ninetyReturn} , ninety_sales = #{ninetySales}, on_way_quantity = #{onWayQuantity}, picture_url = #{pictureUrl}, platform_sku = #{platformSku}, process_quantity = #{processQuantity}, purchase_price = #{purchasePrice}, quantity = #{quantity}, sales_days = #{salesDays}, seven_return = #{sevenReturn}, seven_sales = #{sevenSales}, shipping_type = #{shippingType}, status = #{status}, stock_sku = #{stockSku}, stock_type = #{stockType}, stock_status = #{stockStatus}, stock_warning_days = #{stockWarningDays}, thirty_sales = #{thirtySales}, thirty_return = #{thirtyReturn}, title = #{title}, weight = #{weight}" +
            " where unique_id = #{uniqueId}")
    int update(FbaInfo fbaInfo);


}