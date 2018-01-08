package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

/**
 *1.3 获取商品加工信息	
 */
@Data
public class StockMachiningInfo {
    private String stockIds; //3.0 库存 sku 编号
    private String stockId; //商品编号
    private String stockSku; //商品sku编号
    private String quantity; //数量
    private String defaultCost; //统一成本价

}
