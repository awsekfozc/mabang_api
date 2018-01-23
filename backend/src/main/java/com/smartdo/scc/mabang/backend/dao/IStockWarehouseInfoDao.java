package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.StockWarehouseInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IStockWarehouseInfoDao {
    @Insert(" INSERT INTO stock_warehouse_info ( stock_id, warehouse_id, gridcode, stock_quantity, status, stock_cost, is_default, warehouse_type )" +
            " VALUES ( #{stockId},#{warehouseId},#{gridcode},#{stockQuantity},#{status},#{stockCost},#{isDefault},#{warehouseType})")
    int add(StockWarehouseInfo sockWarehouseInfo);

    @Select("SELECT count(*) FROM stock_warehouse_info where stock_id = #{stockId}")
    int IsExist(StockWarehouseInfo sockWarehouseInfo);

    @Update(" UPDATE stock_warehouse_info SET stock_id = #{stockId}, warehouse_id= #{warehouseId}, gridcode= #{gridcode}, stock_quantity= #{stockQuantity}, status= #{status}, stock_cost= #{stockCost}, is_default= #{isDefault}, warehouse_type= #{warehouseType} " +
            " where stock_id = #{stockId}")
    int update(StockWarehouseInfo sockWarehouseInfo);




    @Select(" select stock_id as stockId from stock_info")
    List<String> getStockId();


}
