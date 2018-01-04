package com.smartdo.scc.mabang.backend.response;


import com.alibaba.fastjson.JSON;
import com.smartdo.scc.mabang.backend.bean.Product;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;

import java.util.List;

/**
 * 商品响应类
 */
public class StockResponse extends Response {

    @Getter
    private List<Product> productList = null;

    public StockResponse(HttpResult result) {
        super(result);
    }


    @Override
    public void setBeans() {

    }
}
