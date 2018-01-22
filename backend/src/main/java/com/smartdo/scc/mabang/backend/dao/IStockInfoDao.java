package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.StockInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface IStockInfoDao {

    @Insert(" INSERT INTO stock_info_temp ( update_date1,update_date2,stock_id, stock_sku, original_sku, name_cn, name_en, stock_quantity, status, parent_category_id, category_id, stock_picture, remark, time_created, update_time, is_machining, retail_id, sale_price, default_cost, waiting_quantity, shipping_quantity, is_new_type, liveness_type, declare_name, declare_ename, declare_value, weight, declare_code, has_battery, is_tort, magnetic, no_liquid_cosmetic, powder, length, width, height, volume, developer_id, sales_id, package_id, package_count, quantity_interval7, quantity_interval28, quantity_interval42 )" +
            " VALUES ( #{updateDate1},#{updateDate2},#{stockId},#{stockSku},#{originalSku},#{nameCN},#{nameEN},#{stockQuantity},#{status},#{parentCategoryId},#{categoryId},#{stockPicture},#{remark},#{timeCreated},#{updateTime},#{isMachining},#{retailId},#{salePrice},#{defaultCost},#{waitingQuantity},#{shippingQuantity},#{isNewType},#{ livenessType},#{declareName},#{declareEname},#{declareValue},#{weight},#{declareCode},#{hasBattery},#{isTort},#{magnetic},#{noLiquidCosmetic},#{powder},#{length},#{width},#{height},#{volume},#{developerId},#{salesId},#{packageId},#{packageCount},#{quantityInterval7},#{quantityInterval28},#{quantityInterval42})")
    int add(StockInfo stockInfo);

    @Update(" Update  stock_info_temp  set( update_date1,update_date2,stock_id, stock_sku, original_sku, name_cn, name_en, stock_quantity, status, parent_category_id, category_id, stock_picture, remark, time_created, update_time, is_machining, retail_id, sale_price, default_cost, waiting_quantity, shipping_quantity, is_new_type, liveness_type, declare_name, declare_ename, declare_value, weight, declare_code, has_battery, is_tort, magnetic, no_liquid_cosmetic, powder, length, width, height, volume, developer_id, sales_id, package_id, package_count, quantity_interval7, quantity_interval28, quantity_interval42 )" +
            " VALUES ( #{updateDate1},#{updateDate2},#{stockId},#{stockSku},#{originalSku},#{nameCN},#{nameEN},#{stockQuantity},#{status},#{parentCategoryId},#{categoryId},#{stockPicture},#{remark},#{timeCreated},#{updateTime},#{isMachining},#{retailId},#{salePrice},#{defaultCost},#{waitingQuantity},#{shippingQuantity},#{isNewType},#{ livenessType},#{declareName},#{declareEname},#{declareValue},#{weight},#{declareCode},#{hasBattery},#{isTort},#{magnetic},#{noLiquidCosmetic},#{powder},#{length},#{width},#{height},#{volume},#{developerId},#{salesId},#{packageId},#{packageCount},#{quantityInterval7},#{quantityInterval28},#{quantityInterval42})"+
            " where stock_id = #{stockId}")
    int update(StockInfo stockInfo);


    //删除表中多余的重复记录，重复记录是根据单个字段（stock_id）来判断，只留有id最大的记录,（因为数据库的id是自增长，大者为后更新）
    @Delete(" delete from stock_info_temp " +
            " where stock_id  in (select temp_stock_id from (select  stock_id as  temp_stock_id from stock_info_temp  group  by  stock_id   having  count(stock_id) > 1) a)" +
            " and id not in (select temp_max_id from (select max(id) as temp_max_id from  stock_info_temp  group by stock_id  having count(stock_id )>1)b);")
    int removeDuplicates();
}
