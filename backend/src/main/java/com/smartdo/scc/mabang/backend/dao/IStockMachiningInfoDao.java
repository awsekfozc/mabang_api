package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.StockMachiningInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IStockMachiningInfoDao {
    @Insert(" INSERT INTO stock_machining_info ( stock_ids, stock_id, stock_sku, quantity, default_cost)" +
            " VALUES ( #{stockIds},#{stockId},#{stockSku},#{quantity},#{defaultCost})")
    int add(StockMachiningInfo stockMachiningInfo);


    @Select("SELECT count(*) FROM stock_machining_info where stock_id = #{stockId} and stock_ids = #{stockIds}")
    int IsExist(StockMachiningInfo stockMachiningInfo);

    @Update(" UPDATE stock_machining_info SET  stock_ids = #{stockIds}, stock_id = #{stockId}, stock_sku = #{stockSku}, quantity = #{quantity}, default_cost = #{defaultCost}" +
            " where stock_id = #{stockId} and stock_ids = #{stockIds};")
    int update(StockMachiningInfo stockMachiningInfo);

    @Select(" select stock_id as stockId from stock_info where is_machining = '1' ")
    List<String> getStockId();

}
