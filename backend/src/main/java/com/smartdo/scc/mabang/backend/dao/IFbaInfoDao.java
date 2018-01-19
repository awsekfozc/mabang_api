package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.FbaInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

public interface IFbaInfoDao {
    @Insert(" INSERT INTO fba_info (lastsync_time,shop_ids,open_date,yesterday_sales,before_yesterday_sales, three_days_ago_sales, transporttype1, transporttype2, transporttype3, transporttype4, f_n_s_k_u, afnreservedquantity, afnreservedquantity1, amazonsite, asin, conditions, content, daily_sales, fourteen_return, fourteen_sales, head_shipping, inboundrecommendquantity, is_change, ninety_return, ninety_sales, on_way_quantity, picture_url, platform_sku, process_quantity, purchase_price, quantity, sales_days, seven_return, seven_sales, shipping_type, status, stock_sku, stock_type, stock_status, stock_warning_days, thirty_sales, thirty_return, title, weight )" +
            " VALUES (#{LastsyncTime},#{shopIds},#{openDate},#{yesterdaySales},#{beforeYesterdaySales},#{threeDaysAgoSales},#{Transporttype1},#{Transporttype2},#{Transporttype3},#{Transporttype4},#{FNSKU},#{afnreservedquantity},#{afnreservedquantity1},#{amazonsite},#{asin},#{conditions},#{content},#{dailySales},#{fourteenReturn},#{fourteenSales},#{headShipping},#{inboundrecommendquantity},#{isChange},#{ninetyReturn},#{ninetySales},#{onWayQuantity},#{pictureUrl},#{platformSku},#{processQuantity},#{purchasePrice},#{quantity},#{salesDays},#{sevenReturn},#{sevenSales},#{shippingType},#{status},#{stockSku},#{stockType},#{stockStatus},#{stockWarningDays},#{thirtyReturn},#{thirtySales},#{title},#{weight})")
    int add(FbaInfo fbaInfo);

    //删除表中多余的重复记录，重复记录是根据单个字段（unique_id）来判断，只留有id最大的记录,（因为数据库的id是自增长，大者为后更新）
    @Delete(" delete from fba_info " +
            " where unique_id in( select temp_unique_id from (select  unique_id as temp_unique_id from fba_info  group  by  unique_id   having  count(unique_id) > 1)a)"+
            " and id not in( select temp_max_id from(select max(id) as temp_max_id from  fba_info  group by unique_id  having count(unique_id )>1)b)")
    int removeDuplicates();
}