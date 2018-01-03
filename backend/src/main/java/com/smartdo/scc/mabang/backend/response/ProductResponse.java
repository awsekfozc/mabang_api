package com.smartdo.scc.mabang.backend.response;


import com.alibaba.fastjson.JSON;
import com.smartdo.scc.mabang.backend.bean.Product;
import lombok.Getter;

import java.util.List;

/**
 * 商品响应类
 */
public class ProductResponse extends Response {

    @Getter
    private List<Product> productList = null;

    public void setBeans() {
        productList = JSON.parseArray(super.data, Product.class);
    }
}
