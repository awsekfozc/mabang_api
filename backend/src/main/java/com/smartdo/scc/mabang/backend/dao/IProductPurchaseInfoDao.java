package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.ProductPurchaseInfo;
import org.apache.ibatis.annotations.Insert;

public interface IProductPurchaseInfoDao {
    @Insert("INSERT INTO product_purchase_info ( group_id,order_bill_n_o,purchase_order_type,payment_status,flag,express_id,create_time,target_warehouse_id,check_time,estimated_time,create_operId,buyer_id,amount,express_money,discount_amount,tax_amount,content,provider,contact_address,ali1688_order_id)" +
            "VALUES (#{groupId},#{orderBillNO},#{purchaseOrderType},#{paymentStatus},#{flag},#{expressId},#{createTime},#{targetWarehouseId},#{checkTime},#{estimatedTime},#{createOperId},#{buyerId},#{amount},#{expressMoney},#{discountAmount},#{taxAmount},#{content},#{provider},#{contactAddress},#{ali1688OrderId})")
    int add(ProductPurchaseInfo productPurchaseInfo);

}
