package com.smartdo.scc.mabang.backend.pipe;

import com.smartdo.scc.mabang.backend.bean.StockInfo;
import com.smartdo.scc.mabang.backend.dao.IStockInfoDao;
import com.smartdo.scc.mabang.backend.exceptions.ResponseTypeError;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import com.smartdo.scc.mabang.backend.response.Response;
import com.smartdo.scc.mabang.backend.response.StockInfoActionResponse;
import org.apache.ibatis.session.SqlSession;

public class StockInfoPipeline implements Pipeline {

    public void save(Response response) throws ResponseTypeError {
        if (!(response instanceof StockInfoActionResponse))
            throw new ResponseTypeError("不是商品响应类型");
        StockInfoActionResponse productResponse = (StockInfoActionResponse) response;
        //持久化
        SqlSession session = DbFactory.getInstance().openSession();
        IStockInfoDao dao = DbFactory.getBeanMapper(IStockInfoDao.class, session);
        try {
            for (StockInfo product : productResponse.getStockInfoList()) {
                dao.add(product);
            }
        } finally {
            session.commit();
            session.close();
        }
    }
}
