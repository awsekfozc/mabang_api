package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

@Data
public class StockWarehouseInfo {
    private String stockId;
    private String warehouseId;
    private String gridcode;
    private String stockQuantity;
    private String status;
    private String stockCost;
    private String isDefault;
    private String warehouseType;
}
