package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

/**
 * 1.2 获取商品仓库信息
 */
@Data
public class StockWarehouseInfo extends DataEntity {

    private String stockId;//	商品编号
    private String warehouseId;//	商品仓库
    private String gridcode;//仓位
    private String stockQuantity;//	商品仓库库存
    private String status;//状态	1:启用	2:停用
    private String stockCost;//	商品仓库成本价
    private String isDefault;//是否是默认仓库	1:是	2:否
    private String warehouseType;//	商品仓库类型	1:自建	2:第三方	3:fba

}
