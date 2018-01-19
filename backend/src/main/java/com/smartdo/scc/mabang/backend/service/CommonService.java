package com.smartdo.scc.mabang.backend.service;

import com.smartdo.scc.mabang.backend.dao.ICommonDao;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import org.apache.ibatis.session.SqlSession;

public class CommonService {
    /**
     * //删除表(多张表)
     * @return
     */
    public void deleteTable(){
        SqlSession session = DbFactory.getInstance().openSession();
        ICommonDao dao = DbFactory.getBeanMapper(ICommonDao.class, session);
        dao.deleteTableFbaInfo();
        dao.deleteTableOrderInfo();
        dao.deleteTableOrderItem();
        dao.deleteTableProductPurchaseInfo();
        dao.deleteTableProductPurchaseStorageInInfo();
        dao.deleteTablePurchaseDetail();
        dao.deleteTableStockInfo();
        dao.deleteTableStockMachiningInfo();
        dao.deleteTableStockProviderInfo();
        dao.deleteTableStockStorageLog();
        dao.deleteTableStockWarehouseInfo();
        session.commit();
        session.close();
    }
}
