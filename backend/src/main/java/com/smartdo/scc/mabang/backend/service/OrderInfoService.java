package com.smartdo.scc.mabang.backend.service;

import com.smartdo.scc.mabang.backend.dao.IOrderInfoDao;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import org.apache.ibatis.session.SqlSession;

public class OrderInfoService {
    /**
     * //删除表中多余的重复记录
     * @return
     */
    public void removeDuplicates(){
        SqlSession session = DbFactory.getInstance().openSession();
        IOrderInfoDao dao = DbFactory.getBeanMapper(IOrderInfoDao.class, session);
        dao.removeDuplicates();
        session.commit();
        session.close();
    }
}
