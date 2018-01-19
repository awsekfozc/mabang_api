package com.smartdo.scc.mabang.backend.service;

import com.smartdo.scc.mabang.backend.bean.Scheduling;
import com.smartdo.scc.mabang.backend.dao.IStockMachiningInfoDao;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class StockMachiningInfoService {
    /**
     * 查出stock_info表中的有加工信息的StockId
     * @return
     */
    public List<String> getStockId(){
        SqlSession session = DbFactory.getInstance().openSession();
        IStockMachiningInfoDao dao = DbFactory.getBeanMapper(IStockMachiningInfoDao.class, session);
        List resultList= dao.getStockId();
        List<String> stockIdsList = new ArrayList();
        int j = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resultList.size(); i++) {
            if(j < 9){
                sb.append(resultList.get(i) + ",");
            }else if(j == 9){
                sb.append(resultList.get(i));
                stockIdsList.add(sb.toString());
            }
            if(j == 10){
                j = 0;
                sb = new StringBuilder();
                sb.append(resultList.get(i) + ",");
            }
            j = j + 1;
        }
        return stockIdsList;
    }

    public void deleteAll(Scheduling sheduling){
        SqlSession session = DbFactory.getInstance().openSession();
        IStockMachiningInfoDao dao = DbFactory.getBeanMapper(IStockMachiningInfoDao.class, session);
        dao.deleteAll(sheduling);
        session.commit();
        session.close();
    }
}
