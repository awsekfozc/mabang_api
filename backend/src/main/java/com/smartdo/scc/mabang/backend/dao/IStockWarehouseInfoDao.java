package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.StockWarehouseInfo;
import org.apache.ibatis.annotations.Insert;

public interface IStockWarehouseInfoDao {
    @Insert("INSERT INTO stock_warehouse_info ( stock_id, warehouse_id, gridcode, stock_quantity, status, stock_cost, is_default, warehouse_type )" +
            "VALUES ( #{stockId},#{warehouseId},#{gridcode},#{stockQuantity},#{status},#{stockCost},#{isDefault},#{warehouseType})")
    int add(StockWarehouseInfo sockWarehouseInfo);
}
