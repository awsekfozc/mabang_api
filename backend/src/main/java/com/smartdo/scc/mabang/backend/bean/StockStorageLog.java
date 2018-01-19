package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

import java.util.Date;

/**
 * 商品出入库流水信息
 */
@Data
public class StockStorageLog extends DataEntity{
    private String stockId;// 库存sku 编号
    private String quantity;// 数量
    private String remark;// 描述
    private String stockName;// 库存sku 名称
    private String stockSku;// 库存sku
    private String ctype;// 类型 1.入库 2 出库
    private Date timeCreated;// 创建时间
    private String operId;// 操纵员工
    private String warehouseId;// 仓库
    private String gridCode;// 仓位
}
