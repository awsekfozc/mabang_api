package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.PurchaseDetail;
import org.apache.ibatis.annotations.Insert;

public interface IPurchaseDetailDao {
    @Insert("INSERT INTO purchase_detail (group_id,purchase_num,sell_price,remark,stock_sku,name_cn,name_en,original_sku,stock_picture,weight)" +
            "VALUES (#{groupId},#{purchaseNum},#{sellPrice},#{remark},#{stockSku},#{nameCN},#{nameEN},#{originalSku},#{stockPicture},#{weight})")
    int add(PurchaseDetail purchaseDetail);
}
