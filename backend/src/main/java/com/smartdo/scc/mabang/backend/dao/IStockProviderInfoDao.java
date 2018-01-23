package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.StockProviderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IStockProviderInfoDao {
    @Insert(" INSERT INTO stock_provider_info ( stock_id, provider, contact_person, contact_tel, link_address, contact_province, contact_address,delete_flag, oper_time, payee, receive_term_id, receive_account, payment_type, buyer_id, temporary_buyer_id, content, arrival_cycle)" +
            " VALUES ( #{stockId},#{provider},#{contactPerson},#{contactTel},#{linkAddress},#{contactProvince},#{contactAddress},#{deleteFlag},#{operTime},#{payee},#{receiveTermId},#{receiveAccount},#{paymentType},#{buyerId},#{temporaryBuyerId},#{content},#{arrivalCycle})")
    int add(StockProviderInfo stockProviderInfo);

    @Select(" SELECT count(*) FROM stock_provider_info where stock_id = #{stockId}")
    int IsExist(StockProviderInfo stockProviderInfo);

    @Update(" update stock_provider_info set stock_id = #{stockId}, provider = #{provider}, contact_person = #{contactPerson}, contact_tel = #{contactTel}, link_address = #{linkAddress}, contact_province = #{contactProvince}, contact_address = #{contactAddress},delete_flag = #{deleteFlag}, oper_time = #{operTime}, payee = #{payee}, receive_term_id = #{receiveTermId}, receive_account = #{receiveAccount}, payment_type = #{paymentType}, buyer_id = #{buyerId}, temporary_buyer_id = #{temporaryBuyerId}, content = #{content}, arrival_cycle = #{arrivalCycle}" +
            " where stock_id = #{stockId}")
    int update(StockProviderInfo stockProviderInfo);

}
