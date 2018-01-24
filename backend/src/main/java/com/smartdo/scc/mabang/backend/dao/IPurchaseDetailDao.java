package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.PurchaseDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IPurchaseDetailDao {
    @Insert(" INSERT INTO purchase_detail (group_id,purchase_num,sell_price,remark,stock_sku,name_cn,name_en,original_sku,stock_picture,weight)" +
            " VALUES (#{groupId},#{purchaseNum},#{sellPrice},#{remark},#{stockSku},#{nameCN},#{nameEN},#{originalSku},#{stockPicture},#{weight})")
    int add(PurchaseDetail purchaseDetail);

    @Select("SELECT count(*) FROM purchase_detail where group_id = #{groupId} and stock_sku =  #{stockSku}")
    int IsExist(PurchaseDetail purchaseDetail);

    @Update(" update purchase_detail set group_id = #{groupId},purchase_num = #{purchaseNum},sell_price = #{sellPrice},remark = #{remark},stock_sku = #{stockSku},name_cn = #{nameCN},name_en = #{nameEN},original_sku = #{originalSku},stock_picture = #{stockPicture},weight = #{weight}" +
            " where group_id = #{groupId} and stock_sku =  #{stockSku}")
    int update(PurchaseDetail purchaseDetail);

}
