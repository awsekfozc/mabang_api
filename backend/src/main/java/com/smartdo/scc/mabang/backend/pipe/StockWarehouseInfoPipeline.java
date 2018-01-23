package com.smartdo.scc.mabang.backend.pipe;


import com.smartdo.scc.mabang.backend.bean.StockWarehouseInfo;
import com.smartdo.scc.mabang.backend.dao.IStockWarehouseInfoDao;
import com.smartdo.scc.mabang.backend.exceptions.ResponseTypeError;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import com.smartdo.scc.mabang.backend.response.Response;
import com.smartdo.scc.mabang.backend.response.StockWarehouseInfoResponse;
import org.apache.ibatis.session.SqlSession;

public class StockWarehouseInfoPipeline implements Pipeline {

    @Override
    public void save(Response response) throws ResponseTypeError {
        if (!(response instanceof StockWarehouseInfoResponse))
            throw new ResponseTypeError("不是商品响应类型");
        StockWarehouseInfoResponse stockWarehouseInfoResponse = (StockWarehouseInfoResponse) response;
        //持久化
        SqlSession session = DbFactory.getInstance().openSession();
        IStockWarehouseInfoDao dao = DbFactory.getBeanMapper(IStockWarehouseInfoDao.class, session);
        try {
                for (StockWarehouseInfo stockWarehouseInfo : stockWarehouseInfoResponse.getStockWarehouseInfoList()) {
                    if (dao.IsExist(stockWarehouseInfo) > 0) {
                        dao.update(stockWarehouseInfo);
                    } else {
                        dao.add(stockWarehouseInfo);
                    }
                }
        } finally {
            session.commit();
            session.close();
        }
    }
}
