package com.smartdo.scc.mabang.backend.factory;

import com.smartdo.scc.mabang.backend.request.ProductRequst;
import com.smartdo.scc.mabang.backend.request.Request;
import com.smartdo.scc.mabang.backend.response.ProductResponse;
import com.smartdo.scc.mabang.backend.response.Response;

/**
 * 响应生成工厂
 */
public class ResponseFactory {

    public static Response getResponse(Request request) {
        if (request instanceof ProductRequst) {
            return new ProductResponse(request.getResult());
        }
        return null;
    }
}
