package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IOrderItemDao {
    @Insert(" INSERT INTO order_item(order_item_id,platform_order_id,cost_price,is_combo,item_id,picture_url,platform_quantity,platform_sku,product_unit,quantity,sell_price,specifics,status,stock_grid,stock_warehouse_id,stock_sku,title,unit_weight,transaction_id)" +
            " VALUES (#{orderItemId},#{platformOrderId},#{costPrice},#{isCombo},#{itemId},#{pictureUrl},#{platformQuantity},#{platformSku},#{productUnit},#{quantity},#{sellPrice},#{specifics},#{status},#{stockGrid},#{stockWarehouseId},#{stockSku},#{title},#{unitWeight},#{transactionId})")
    int add(OrderItem orderItem);

    @Update(" update order_item set order_item_id = #{orderItemId},platform_order_id=#{platformOrderId},cost_price=#{costPrice},is_combo=#{isCombo},item_id=#{itemId},picture_url=#{pictureUrl},platform_quantity = #{platformQuantity},platform_sku=#{platformSku},product_unit=#{productUnit},quantity=#{quantity},sell_price=#{sellPrice},specifics=#{specifics},status=#{status},stock_grid=#{stockGrid},stock_warehouse_id=#{stockWarehouseId},stock_sku=#{stockSku},title=#{title},unit_weight=#{unitWeight},transaction_id=#{transactionId}" +
            " where order_item_id = #{orderItemId} and platform_order_id =  #{platformOrderId} ")
    int update(OrderItem orderItem);

    /*数据库添加了组合索引 ALTER TABLE order_item ADD INDEX index_name (order_item_id ,platform_order_id);*/
    @Select(" SELECT count(*) FROM order_item where order_item_id = #{orderItemId} and platform_order_id =  #{platformOrderId}")
    int IsExist(OrderItem orderItem);

}
