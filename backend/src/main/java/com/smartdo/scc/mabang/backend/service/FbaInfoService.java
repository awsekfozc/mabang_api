package com.smartdo.scc.mabang.backend.service;

import com.smartdo.scc.mabang.backend.dao.IFbaInfoDao;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import org.apache.ibatis.session.SqlSession;

public class FbaInfoService {
    /**
     * //删除表中多余的重复记录
     * @return
     */
    public void removeDuplicates(){
        SqlSession session = DbFactory.getInstance().openSession();
        IFbaInfoDao dao = DbFactory.getBeanMapper(IFbaInfoDao.class, session);
        dao.removeDuplicates();
        session.commit();
        session.close();
    }
}
