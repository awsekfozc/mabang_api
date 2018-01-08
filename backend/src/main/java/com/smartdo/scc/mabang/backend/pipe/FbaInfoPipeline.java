package com.smartdo.scc.mabang.backend.pipe;

import com.smartdo.scc.mabang.backend.bean.FbaInfo;
import com.smartdo.scc.mabang.backend.dao.IFbaInfoDao;
import com.smartdo.scc.mabang.backend.exceptions.ResponseTypeError;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import com.smartdo.scc.mabang.backend.response.FbaInfoResponse;
import com.smartdo.scc.mabang.backend.response.Response;
import org.apache.ibatis.session.SqlSession;

public class FbaInfoPipeline implements Pipeline {
    @Override
    public void save(Response response) throws ResponseTypeError {
        if (!(response instanceof FbaInfoResponse))
            throw new ResponseTypeError("不是商品响应类型");
        FbaInfoResponse fbaInfoResponse = (FbaInfoResponse) response;
        //持久化
        SqlSession session = DbFactory.getInstance().openSession();
        IFbaInfoDao dao = DbFactory.getBeanMapper(IFbaInfoDao.class, session);
        try {
            for (FbaInfo fbaInfo : fbaInfoResponse.getProductList()) {
                    dao.add(fbaInfo);
            }
        } finally {
            session.commit();
            session.close();
        }
    }
}
