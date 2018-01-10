package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.StockProviderInfo;
import org.apache.ibatis.annotations.Insert;

public interface IStockProviderInfoDao {
    @Insert("INSERT INTO stock_provider_info ( stock_id, provider, contact_person, contact_tel, link_address, contact_province, contact_address,delete_flag, oper_time, payee, receive_term_id, receive_account, payment_type, buyer_id, temporary_buyer_id, content, arrival_cycle)" +
            "VALUES ( #{stockId},#{provider},#{contactPerson},#{contactTel},#{linkAddress},#{contactProvince},#{contactAddress},#{deleteFlag},#{operTime},#{payee},#{receiveTermId},#{receiveAccount},#{paymentType},#{buyerId},#{temporaryBuyerId},#{content},#{arrivalCycle})")
    int add(StockProviderInfo stockProviderInfo);
}
