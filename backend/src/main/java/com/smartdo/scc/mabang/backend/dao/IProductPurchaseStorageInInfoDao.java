package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.ProductPurchaseStorageInInfo;
import com.smartdo.scc.mabang.backend.bean.Scheduling;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IProductPurchaseStorageInInfoDao {
    @Insert(" INSERT INTO product_purchase_storage_in_info ( update_time_end,purchase_group, sku, storage_num, damaged_num)" +
            " VALUES ( #{updateTimeEnd},#{purchaseGroup},#{sku},#{storageNum},#{damagedNum})")
    int add(ProductPurchaseStorageInInfo productPurchaseStorageInInfo);

    @Select(" select group_id as groupId from product_purchase_info")
    List<String> getGroupId ();

    @Update(" UPDATE product_purchase_storage_in_info SET del_flag = '1' ,update_time_local = #{updateTimeEnd} WHERE  ((del_flag is  NULL) or (del_flag = '0'))")
    int deleteAll(Scheduling sheduling);
}
