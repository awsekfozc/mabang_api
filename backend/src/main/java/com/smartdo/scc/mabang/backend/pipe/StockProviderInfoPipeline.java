package com.smartdo.scc.mabang.backend.pipe;

import com.smartdo.scc.mabang.backend.bean.StockProviderInfo;
import com.smartdo.scc.mabang.backend.dao.IStockProviderInfoDao;
import com.smartdo.scc.mabang.backend.exceptions.ResponseTypeError;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import com.smartdo.scc.mabang.backend.response.Response;
import com.smartdo.scc.mabang.backend.response.StockProviderInfoResponse;
import org.apache.ibatis.session.SqlSession;

public class StockProviderInfoPipeline implements Pipeline {
    @Override
    public void save(Response response) throws ResponseTypeError {
        if (!(response instanceof StockProviderInfoResponse))
            throw new ResponseTypeError("不是商品响应类型");
        StockProviderInfoResponse stockProviderInfoResponse = (StockProviderInfoResponse) response;
        //持久化
        SqlSession session = DbFactory.getInstance().openSession();

        IStockProviderInfoDao dao = DbFactory.getBeanMapper(IStockProviderInfoDao.class, session);
        try {
            for (StockProviderInfo stockProviderInfo : stockProviderInfoResponse.getProductList()) {
                    dao.add(stockProviderInfo);
            }
        } finally {
            session.commit();
            session.close();
        }
    }
}
