package com.smartdo.scc.mabang.backend.service;

import com.smartdo.scc.mabang.backend.dao.IStockProviderInfoDao;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import org.apache.ibatis.session.SqlSession;

public class StockProviderInfoService {
    /**
     * //删除表中多余的重复记录
     * @return
     */
    public void removeDuplicates(){
        SqlSession session = DbFactory.getInstance().openSession();
        IStockProviderInfoDao dao = DbFactory.getBeanMapper(IStockProviderInfoDao.class, session);
        dao.removeDuplicates();
        session.commit();
        session.close();
    }
}
