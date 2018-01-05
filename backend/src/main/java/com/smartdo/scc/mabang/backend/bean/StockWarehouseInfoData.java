package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

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

    public StockWarehouseInfoData() {
    }
    public StockWarehouseInfoData(String stockId, String warehouseId, String gridcode, String stockQuantity, String status, String stockCost, String isDefault, String warehouseType) {
        this.stockId = stockId;
        this.warehouseId = warehouseId;
        this.gridcode = gridcode;
        this.stockQuantity = stockQuantity;
        this.status = status;
        this.stockCost = stockCost;
        this.isDefault = isDefault;
        this.warehouseType = warehouseType;
    }


}
