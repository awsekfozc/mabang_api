package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.Scheduling;
import com.smartdo.scc.mabang.backend.bean.StockWarehouseInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IStockWarehouseInfoDao {
    @Insert(" INSERT INTO stock_warehouse_info ( update_time_end,stock_id, warehouse_id, gridcode, stock_quantity, status, stock_cost, is_default, warehouse_type )" +
            " VALUES ( #{updateTimeEnd},#{stockId},#{warehouseId},#{gridcode},#{stockQuantity},#{status},#{stockCost},#{isDefault},#{warehouseType})")
    int add(StockWarehouseInfo sockWarehouseInfo);

    @Select(" select stock_id as stockId from stock_info")
    List<String> getStockId();

    @Update(" UPDATE stock_warehouse_info SET del_flag = '1' ,update_time_local = #{updateTimeEnd} WHERE  ((del_flag is  NULL) or (del_flag = '0'))")
    int deleteAll(Scheduling sheduling);
}
