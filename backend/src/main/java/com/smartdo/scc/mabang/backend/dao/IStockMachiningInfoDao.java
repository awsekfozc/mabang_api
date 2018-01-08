package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.StockMachiningInfo;
import org.apache.ibatis.annotations.Insert;

public interface IStockMachiningInfoDao {
    @Insert("INSERT INTO stock_machining_info ( stock_ids, stock_id, stock_sku, quantity, default_cost)" +
            "VALUES ( #{stockIds},#{stockId},#{stockSku},#{quantity},#{defaultCost})")
    int add(StockMachiningInfo stockMachiningInfo);

}
