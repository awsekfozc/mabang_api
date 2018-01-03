package com.smartdo.scc.mabang.backend.pipe;

import com.smartdo.scc.mabang.backend.bean.Product;
import com.smartdo.scc.mabang.backend.exceptions.ResponseTypeError;
import com.smartdo.scc.mabang.backend.response.ProductResponse;
import com.smartdo.scc.mabang.backend.response.Response;

public class ProductPipeline implements Pipeline {

    public void save(Response response) throws ResponseTypeError {
        if (!(response instanceof ProductResponse))
            throw new ResponseTypeError("不是商品响应类型");
        ProductResponse productResponse = (ProductResponse) response;
        for (Product product : productResponse.getProductList()) {
            System.out.println(product);
        }
    }
}
