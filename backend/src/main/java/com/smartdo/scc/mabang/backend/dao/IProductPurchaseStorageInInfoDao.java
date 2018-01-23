package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.ProductPurchaseStorageInInfo;
import com.smartdo.scc.mabang.backend.bean.Scheduling;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IProductPurchaseStorageInInfoDao {
    @Insert(" INSERT INTO product_purchase_storage_in_info ( purchase_group, sku, storage_num, damaged_num)" +
            " VALUES ( #{purchaseGroup},#{sku},#{storageNum},#{damagedNum})")
    int add(ProductPurchaseStorageInInfo productPurchaseStorageInInfo);

    @Update(" update product_purchase_storage_in_info set purchase_group =  #{purchaseGroup}, sku = #{sku}, storage_num =#{storageNum} , damaged_num =#{damagedNum} " +
            " where purchase_group = #{purchaseGroup} and sku = #{sku}")
    int update(ProductPurchaseStorageInInfo productPurchaseStorageInInfo);

    @Select("SELECT count(*) FROM product_purchase_storage_in_info where purchase_group = #{purchaseGroup} and sku = #{sku}")
    int IsExist(ProductPurchaseStorageInInfo productPurchaseStorageInInfo);

    @Select(" select group_id as groupId from product_purchase_info")
    List<String> getGroupId ();

    @Update(" UPDATE product_purchase_storage_in_info SET del_flag = '1' ,update_time_local = #{updateTimeEnd} WHERE  ((del_flag is  NULL) or (del_flag = '0'))")
    int deleteAll(Scheduling sheduling);
}
