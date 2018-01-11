package com.smartdo.scc.mabang.backend.pipe;

import com.smartdo.scc.mabang.backend.bean.StockMachiningInfo;
import com.smartdo.scc.mabang.backend.dao.IStockMachiningInfoDao;
import com.smartdo.scc.mabang.backend.exceptions.ResponseTypeError;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import com.smartdo.scc.mabang.backend.response.Response;
import com.smartdo.scc.mabang.backend.response.StockMachiningInfoResponse;
import org.apache.ibatis.session.SqlSession;

public class StockMachiningInfoPipeline implements Pipeline {
    @Override
    public void save(Response response) throws ResponseTypeError {
        if (!(response instanceof StockMachiningInfoResponse))
            throw new ResponseTypeError("不是商品响应类型");
        StockMachiningInfoResponse stockMachiningInfoResponse = (StockMachiningInfoResponse) response;
        //持久化
        SqlSession session = DbFactory.getInstance().openSession();

        IStockMachiningInfoDao dao = DbFactory.getBeanMapper(IStockMachiningInfoDao.class, session);
        try {
            for (StockMachiningInfo stockMachiningInfo : stockMachiningInfoResponse.getStockMachiningInfoList()) {
                    dao.add(stockMachiningInfo);
            }
        } finally {
            session.commit();
            session.close();
        }
    }
}
