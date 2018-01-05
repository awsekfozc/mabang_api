package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.Product;
import org.apache.ibatis.annotations.Insert;

public interface IProductDao {

    @Insert("INSERT INTO product ( stock_id, stock_sku, original_sku, name_cn, name_en, stock_quantity, status, parent_category_id, category_id, stock_picture, remark, time_created, update_time, is_machining, retail_id, sale_price, default_cost, waiting_quantity, shipping_quantity, is_new_type, liveness_type, declare_name, declare_ename, declare_value, weight, declare_code, has_battery, is_tort, magnetic, no_tiquid_cosmetic, powder, length, width, height, volume, developer_id, sales_id, package_id, package_count, quantity_interval7, quantity_interval28, quantity_interval42 )" +
            "VALUES ( #{stockId},#{stockSku},#{originalSku},#{nameCN},#{nameEN},#{stockQuantity},#{status},#{parentCategoryId},#{categoryId},#{stockPicture},#{remark},#{timeCreated},#{updateTime},#{isMachining},#{retailId},#{salePrice},#{defaultCost},#{waitingQuantity},#{shippingQuantity},#{isNewType},#{ livenessType},#{declareName},#{declareEname},#{declareValue},#{weight},#{declareCode},#{hasBattery},#{isTort},#{magnetic},#{noLiquidCosmetic},#{powder},#{length},#{width},#{height},#{volume},#{developerId},#{salesId},#{packageId},#{packageCount},#{quantityInterval7},#{quantityInterval28},#{quantityInterval42})")
    int add(Product product);
}
