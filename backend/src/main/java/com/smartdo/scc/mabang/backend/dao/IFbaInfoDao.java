package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.FbaInfo;
import org.apache.ibatis.annotations.Insert;

public interface IFbaInfoDao {
    @Insert("INSERT INTO fba_info ( f_n_s_k_u, afnreservedquantity, afnreservedquantity1, amazonsite, asin, conditions, content, daily_sales, fourteen_return, fourteen_sales, head_shipping, inboundrecommendquantity, is_change, ninety_return, ninety_sales, on_way_quantity, picture_url, platform_sku, process_quantity, purchase_price, quantity, sales_days, seven_return, seven_sales, shipping_type, status, stock_sku, stock_type, stock_status, stock_warning_days, thirty_sales, thirty_return, title, weight )" +
            "VALUES (#{FNSKU},#{afnreservedquantity},#{afnreservedquantity1},#{amazonsite},#{asin},#{conditions},#{content},#{dailySales},#{fourteenReturn},#{fourteenSales},#{headShipping},#{inboundrecommendquantity},#{isChange},#{ninetyReturn},#{ninetySales},#{onWayQuantity},#{pictureUrl},#{platformSku},#{processQuantity},#{purchasePrice},#{quantity},#{salesDays},#{sevenReturn},#{sevenSales},#{shippingType},#{status},#{stockSku},#{stockType},#{stockStatus},#{stockWarningDays},#{thirtyReturn},#{thirtySales},#{title},#{weight})")
    int add(FbaInfo fbaInfo);
}
