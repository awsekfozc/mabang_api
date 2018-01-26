package com.smartdo.scc.mabang.backend.pipe;


import com.smartdo.scc.mabang.backend.bean.StockWarehouseInfo;
import com.smartdo.scc.mabang.backend.dao.IStockWarehouseInfoDao;
import com.smartdo.scc.mabang.backend.exceptions.ResponseTypeError;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import com.smartdo.scc.mabang.backend.response.Response;
import com.smartdo.scc.mabang.backend.response.StockWarehouseInfoResponse;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

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
            Map<String,List> map = stockWarehouseInfoResponse.getResultMap();
            StockWarehouseInfo stockWarehouseInfo = new StockWarehouseInfo();
            for (Map.Entry<String,List> entry : map.entrySet()) {
                stockWarehouseInfo.setStockId(entry.getKey());
                dao.delete(stockWarehouseInfo);
                for (int i = 0; i < entry.getValue().size(); i++) {
                    stockWarehouseInfo = (StockWarehouseInfo) entry.getValue().get(i);
                    dao.add(stockWarehouseInfo);
                }
            }
        } finally {
            session.commit();
            session.close();
        }
    }
}
