package com.smartdo.scc.mabang.backend.pipe;

import com.smartdo.scc.mabang.backend.bean.Product;
import com.smartdo.scc.mabang.backend.exceptions.ResponseTypeError;
import com.smartdo.scc.mabang.backend.response.ProductResponse;
import com.smartdo.scc.mabang.backend.response.Response;
import com.smartdo.scc.mabang.backend.service.ProductServiceImp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class ProductPipeline implements Pipeline {

private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("Configure.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }

    public void save(Response response) throws ResponseTypeError {
        if (!(response instanceof ProductResponse))
            throw new ResponseTypeError("不是商品响应类型");
        ProductResponse productResponse = (ProductResponse) response;
        //持久化
        ProductServiceImp productServiceImp = new ProductServiceImp();
        for (Product product : productResponse.getProductList()) {
            SqlSession session = sqlSessionFactory.openSession();
            try {
                session.selectOne(
                        "com.smartdo.scc.mabang.backend.ProductMapper.add", product);
            } finally {
                session.close();
            }
        }
        System.out.println("888888888888888888");
    }
}
