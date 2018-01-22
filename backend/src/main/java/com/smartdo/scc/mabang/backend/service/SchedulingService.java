package com.smartdo.scc.mabang.backend.service;

import com.smartdo.scc.mabang.backend.bean.Scheduling;
import com.smartdo.scc.mabang.backend.dao.ISchedulingDao;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SchedulingService {

    /**
     * 查出stock_info表中的有加工信息的StockId
     * @return
     */
    public List<Scheduling> getUpdateTime(){
        SqlSession session = DbFactory.getInstance().openSession();
        ISchedulingDao dao = DbFactory.getBeanMapper(ISchedulingDao.class, session);
        List list = dao.getUpdateTime();
        return list;
    }

    /**
     *
     * @return
     */
    public void add(Scheduling scheduling){
        SqlSession session = DbFactory.getInstance().openSession();
        ISchedulingDao dao = DbFactory.getBeanMapper(ISchedulingDao.class, session);
        dao.add(scheduling);
        session.commit();
        session.close();

    }

}
