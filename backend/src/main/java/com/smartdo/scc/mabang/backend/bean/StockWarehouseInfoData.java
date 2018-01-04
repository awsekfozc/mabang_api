package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;
/**
 * 商品实体
 * 接口方法 get-stock-warehouse-info-data
 *1.2 获取商品仓库信息
 */
@Data
public class StockWarehouseInfoData {

    private String stockId;
    private String warehouseId;
    private String gridcode;
    private String stockQuantity;
    private String status;
    private String stockCost;
    private String isDefault;
    private String warehouseType;

}
