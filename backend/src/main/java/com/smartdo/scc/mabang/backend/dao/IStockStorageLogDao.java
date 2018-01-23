package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.StockStorageLog;
import org.apache.ibatis.annotations.Insert;

public interface IStockStorageLogDao {
    @Insert(" INSERT INTO stock_storage_log ( stock_id,quantity,remark,stock_name,stock_sku,ctype,time_created,oper_id,warehouse_id,grid_code)" +
            " VALUES ( #{stockId},#{quantity},#{remark},#{stockName},#{stockSku},#{ctype},#{timeCreated},#{operId},#{warehouseId},#{gridCode})")
    int add(StockStorageLog stockStorageLog );

    /*@Update(" update stock_storage_log set stock_id = #{stockId},quantity = #{quantity},remark = #{remark},stock_name = #{stockName},stock_sku = #{stockSku},ctype = #{ctype},time_created = #{timeCreated},oper_id = #{operId},warehouse_id = #{warehouseId},grid_code = #{gridCode}" +
            " where stock_id = #{stockId}")
    int update(StockStorageLog stockStorageLog);*/

    /*@Select("SELECT count(*) FROM stock_storage_log where stock_id = #{stockId}")
    int IsExist(StockStorageLog stockStorageLog);*/

}
