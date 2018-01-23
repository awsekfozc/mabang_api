package com.smartdo.scc.mabang.backend.service;

import com.smartdo.scc.mabang.backend.dao.IStockInfoDao;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import org.apache.ibatis.session.SqlSession;

public class StockInfoService {
    /**
     * //删除表中多余的重复记录
     * @return
     */
    public void removeDuplicates(){
        SqlSession session = DbFactory.getInstance().openSession();
        IStockInfoDao dao = DbFactory.getBeanMapper(IStockInfoDao.class, session);
        dao.removeDuplicates();
        session.commit();
        session.close();
    }


    public void deleteAll(){
        SqlSession session = DbFactory.getInstance().openSession();
        IStockInfoDao dao = DbFactory.getBeanMapper(IStockInfoDao.class, session);
        dao.deleteAll();
        session.commit();
        session.close();
    }
}
