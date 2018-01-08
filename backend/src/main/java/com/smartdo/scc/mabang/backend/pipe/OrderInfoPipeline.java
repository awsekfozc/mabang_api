package com.smartdo.scc.mabang.backend.pipe;

import com.smartdo.scc.mabang.backend.bean.OrderInfo;
import com.smartdo.scc.mabang.backend.bean.OrderItem;
import com.smartdo.scc.mabang.backend.dao.IOrderInfoDao;
import com.smartdo.scc.mabang.backend.dao.IOrderItemDao;
import com.smartdo.scc.mabang.backend.exceptions.ResponseTypeError;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import com.smartdo.scc.mabang.backend.response.OrderInfoResponse;
import com.smartdo.scc.mabang.backend.response.Response;
import org.apache.ibatis.session.SqlSession;

public class OrderInfoPipeline implements Pipeline {
    @Override
    public void save(Response response) throws ResponseTypeError {
        if (!(response instanceof OrderInfoResponse))
            throw new ResponseTypeError("不是商品响应类型");
        OrderInfoResponse fbaInfoResponse = (OrderInfoResponse) response;
        //持久化
        SqlSession session = DbFactory.getInstance().openSession();

        IOrderInfoDao iOrderInfodao = DbFactory.getBeanMapper(IOrderInfoDao.class, session);
        IOrderItemDao iOrderItemDao = DbFactory.getBeanMapper(IOrderItemDao.class, session);
        try {
            for (OrderInfo OrderInfo : fbaInfoResponse.getOrderInfoList()) {
                iOrderInfodao.add(OrderInfo);
            }
            for (OrderItem orderItem : fbaInfoResponse.getOrderItemList()) {
                iOrderItemDao.add(orderItem);
            }
        } finally {
            session.commit();
            session.close();
        }
    }
}
