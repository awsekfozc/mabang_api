package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.Scheduling;
import com.smartdo.scc.mabang.backend.bean.StockMachiningInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IStockMachiningInfoDao {
    @Insert(" INSERT INTO stock_machining_info ( update_time_end,stock_ids, stock_id, stock_sku, quantity, default_cost)" +
            " VALUES ( #{updateTimeEnd},#{stockIds},#{stockId},#{stockSku},#{quantity},#{defaultCost})")
    int add(StockMachiningInfo stockMachiningInfo);

    @Select(" select stock_id as stockId from stock_info where is_machining = '1' ")
    List<String> getStockId();

    @Update(" UPDATE stock_machining_info SET del_flag = '1' ,update_time_local = #{updateTimeEnd} WHERE  ((del_flag is  NULL) or (del_flag = '0'))")
    int deleteAll(Scheduling sheduling);
}
