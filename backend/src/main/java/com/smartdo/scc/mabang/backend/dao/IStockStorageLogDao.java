package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.StockStorageLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

public interface IStockStorageLogDao {
    @Insert(" INSERT INTO stock_storage_log ( stock_id,quantity,remark,stock_name,stock_sku,ctype,time_created,oper_id,warehouse_id,grid_code)" +
                                  "VALUES ( #{stockId},#{quantity},#{remark},#{stockName},#{stockSku},#{ctype},#{timeCreated},#{operId},#{warehouseId},#{gridCode})")
    int add(StockStorageLog stockStorageLog );

    //删除表中多余的重复记录，重复记录是根据单个字段（stock_id）来判断，只留有id最大的记录,（因为数据库的id是自增长，大者为后更新）
    @Delete(" delete from stock_storage_log" +
            " where stock_id in( select temp_a from (select  stock_id as temp_a from stock_storage_log  group  by  stock_id   having  count(stock_id) > 1)a)" +
            " and id not in( select temp_b from(select max(id) as temp_b from  stock_storage_log  group by stock_id  having count(stock_id )>1)b)")
    int removeDuplicates();
}
