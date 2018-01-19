package com.smartdo.scc.mabang.backend.service;

import com.smartdo.scc.mabang.backend.dao.IProductPurchaseInfoDao;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import org.apache.ibatis.session.SqlSession;

public class ProductPurchaseInfoService {
    /**
     * //删除表中多余的重复记录
     * @return
     */
    public void removeDuplicates(){
        SqlSession session = DbFactory.getInstance().openSession();
        IProductPurchaseInfoDao dao = DbFactory.getBeanMapper(IProductPurchaseInfoDao.class, session);
        dao.removeDuplicates();
        session.commit();
        session.close();
    }
}
