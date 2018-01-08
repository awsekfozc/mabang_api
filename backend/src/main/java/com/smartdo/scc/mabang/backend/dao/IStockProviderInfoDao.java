package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.StockProviderInfo;
import org.apache.ibatis.annotations.Insert;

public interface IStockProviderInfoDao {
    @Insert("INSERT INTO stock_provider_info ( stock_ids, stock_id, provider, contact_person, contact_tel, link_address, contact_province, contact_address)" +
            "VALUES ( #{stockIds},#{stockId},#{provider},#{contactPerson},#{contactTel},#{linkAddress},#{contactProvince},#{contactAddress})")
    int add(StockProviderInfo stockProviderInfo);

}
