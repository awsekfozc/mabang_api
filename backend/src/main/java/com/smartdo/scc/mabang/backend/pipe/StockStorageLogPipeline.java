package com.smartdo.scc.mabang.backend.pipe;

import com.smartdo.scc.mabang.backend.bean.StockStorageLog;
import com.smartdo.scc.mabang.backend.dao.IStockStorageLogDao;
import com.smartdo.scc.mabang.backend.exceptions.ResponseTypeError;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import com.smartdo.scc.mabang.backend.response.Response;
import com.smartdo.scc.mabang.backend.response.StockStorageLogResponse;
import org.apache.ibatis.session.SqlSession;

public class StockStorageLogPipeline implements Pipeline {
    @Override
    public void save(Response response) throws ResponseTypeError {
        if (!(response instanceof StockStorageLogResponse))
            throw new ResponseTypeError("不是商品响应类型");
        StockStorageLogResponse stockStorageLogResponse = (StockStorageLogResponse) response;
        //持久化
        SqlSession session = DbFactory.getInstance().openSession();
        IStockStorageLogDao dao = DbFactory.getBeanMapper(IStockStorageLogDao.class, session);
        try {
            for (StockStorageLog stockStorageLog : stockStorageLogResponse.getStockStorageLogList()) {
                dao.add(stockStorageLog);
            }
        } finally {
            session.commit();
            session.close();
        }
    }
}
