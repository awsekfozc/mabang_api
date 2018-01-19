package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.ProductPurchaseInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

public interface IProductPurchaseInfoDao {
    @Insert(" INSERT INTO product_purchase_info ( group_id,order_bill_n_o,purchase_order_type,payment_status,flag,express_id,create_time,target_warehouse_id,check_time,estimated_time,create_operId,buyer_id,amount,express_money,discount_amount,tax_amount,content,provider,contact_address,ali1688_order_id)" +
            " VALUES (#{groupId},#{orderBillNO},#{purchaseOrderType},#{paymentStatus},#{flag},#{expressId},#{createTime},#{targetWarehouseId},#{checkTime},#{estimatedTime},#{createOperId},#{buyerId},#{amount},#{expressMoney},#{discountAmount},#{taxAmount},#{content},#{provider},#{contactAddress},#{ali1688OrderId})")
    int add(ProductPurchaseInfo productPurchaseInfo);

    //删除表中多余的重复记录，重复记录是根据单个字段（group_id）来判断，只留有id最大的记录,（因为数据库的id是自增长，大者为后更新）

    @Delete(" delete from product_purchase_info" +
            " where group_id in( select temp_a from (select  group_id as temp_a from product_purchase_info  group  by  group_id   having  count(group_id) > 1)a)"+
            " and id not in( select temp_b from(select max(id) as temp_b from  product_purchase_info  group by group_id  having count(group_id )>1)b)")
    int removeDuplicates();
}
