package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.ProductPurchaseStorageInInfo;
import org.apache.ibatis.annotations.Insert;

public interface IProductPurchaseStorageInInfoDao {
    @Insert("INSERT INTO product_purchase_storage_in_info ( purchase_group, sku, storage_num, damaged_num)" +
            "VALUES ( #{purchaseGroup},#{sku},#{storageNum},#{damagedNum})")
    int add(ProductPurchaseStorageInInfo productPurchaseStorageInInfo);
}
