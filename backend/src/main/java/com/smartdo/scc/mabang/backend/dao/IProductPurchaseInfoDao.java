package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.ProductPurchaseInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IProductPurchaseInfoDao {
    @Insert(" INSERT INTO product_purchase_info ( group_id,order_bill_n_o,purchase_order_type,payment_status,flag,express_id,create_time,target_warehouse_id,check_time,estimated_time,create_operId,buyer_id,amount,express_money,discount_amount,tax_amount,content,provider,contact_address,ali1688_order_id)" +
            " VALUES (#{groupId},#{orderBillNO},#{purchaseOrderType},#{paymentStatus},#{flag},#{expressId},#{createTime},#{targetWarehouseId},#{checkTime},#{estimatedTime},#{createOperId},#{buyerId},#{amount},#{expressMoney},#{discountAmount},#{taxAmount},#{content},#{provider},#{contactAddress},#{ali1688OrderId})")
    int add(ProductPurchaseInfo productPurchaseInfo);

    @Select("SELECT count(*) FROM product_purchase_info where group_id = #{groupId}")
    int IsExist(ProductPurchaseInfo productPurchaseInfo);

    @Update(" update product_purchase_info set group_id = #{groupId},order_bill_n_o = #{orderBillNO},purchase_order_type = #{purchaseOrderType},payment_status = #{paymentStatus},flag = #{flag},express_id = #{expressId},create_time = #{createTime},target_warehouse_id = #{targetWarehouseId},check_time = #{checkTime},estimated_time = #{estimatedTime},create_operId = #{createOperId},buyer_id = #{buyerId},amount = #{amount},express_money = #{expressMoney},discount_amount = #{discountAmount},tax_amount = #{taxAmount},content = #{content},provider = #{provider},contact_address = #{contactAddress},ali1688_order_id = #{ali1688OrderId}" +
            " where group_id = #{groupId}")
    int update(ProductPurchaseInfo productPurchaseInfo);

}
