package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.OrderItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

public interface IOrderItemDao {
    @Insert(" INSERT INTO order_item(platform_order_id,cost_price,is_combo,item_id,picture_url,platform_quantity,platform_sku,product_unit,quantity,sell_price,specifics,status,stock_grid,stock_warehouse_id,stock_sku,title,unit_weight,transaction_id)" +
            " VALUES (#{platformOrderId},#{costPrice},#{isCombo},#{itemId},#{pictureUrl},#{platformQuantity},#{platformSku},#{productUnit},#{quantity},#{sellPrice},#{specifics},#{status},#{stockGrid},#{stockWarehouseId},#{stockSku},#{title},#{unitWeight},#{transactionId})")
    int add(OrderItem orderItem);

    @Delete("delete from order_item where platform_order_id = #{platformOrderId} ")
    int deleteByPlatformOrderId(OrderItem orderItem);
}
