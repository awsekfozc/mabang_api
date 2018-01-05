package com.smartdo.scc.mabang.backend.pipe;

import com.smartdo.scc.mabang.backend.bean.Product;
import com.smartdo.scc.mabang.backend.dao.IProductDao;
import com.smartdo.scc.mabang.backend.exceptions.ResponseTypeError;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import com.smartdo.scc.mabang.backend.response.ProductResponse;
import com.smartdo.scc.mabang.backend.response.Response;
import org.apache.ibatis.session.SqlSession;

public class ProductPipeline implements Pipeline {

    public void save(Response response) throws ResponseTypeError {
        if (!(response instanceof ProductResponse))
            throw new ResponseTypeError("不是商品响应类型");
        ProductResponse productResponse = (ProductResponse) response;
        //持久化
        SqlSession session = DbFactory.getInstance().openSession();
        IProductDao dao = DbFactory.getBeanMapper(IProductDao.class, session);
        try {
            for (Product product : productResponse.getProductList()) {
                dao.add(product);
            }
        } finally {
            session.commit();
            session.close();
        }
    }
}
