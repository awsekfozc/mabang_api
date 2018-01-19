package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.StockProviderInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

public interface IStockProviderInfoDao {
    @Insert(" INSERT INTO stock_provider_info ( stock_id, provider, contact_person, contact_tel, link_address, contact_province, contact_address,delete_flag, oper_time, payee, receive_term_id, receive_account, payment_type, buyer_id, temporary_buyer_id, content, arrival_cycle)" +
            " VALUES ( #{stockId},#{provider},#{contactPerson},#{contactTel},#{linkAddress},#{contactProvince},#{contactAddress},#{deleteFlag},#{operTime},#{payee},#{receiveTermId},#{receiveAccount},#{paymentType},#{buyerId},#{temporaryBuyerId},#{content},#{arrivalCycle})")
    int add(StockProviderInfo stockProviderInfo);

    //删除表中多余的重复记录，重复记录是根据单个字段（stock_id）来判断，只留有id最大的记录,（因为数据库的id是自增长，大者为后更新）
    @Delete(" delete from stock_provider_info " +
            " where stock_id in( select temp_a from (select  stock_id as temp_a from stock_provider_info  group  by  stock_id   having  count(stock_id) > 1)a)" +
            " and id not in( select temp_b from(select max(id) as temp_b from  stock_provider_info  group by stock_id  having count(stock_id )>1)b)")
    int removeDuplicates();
}
