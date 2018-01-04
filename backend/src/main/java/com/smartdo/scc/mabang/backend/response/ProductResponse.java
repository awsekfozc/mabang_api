package com.smartdo.scc.mabang.backend.response;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.Product;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * 商品响应类
 */
@ToString
public class ProductResponse extends Response {

    @Getter
    private List<Product> productList = null;


    public ProductResponse(HttpResult result) {
        super(result);
        setBeans();
        productList.forEach(product -> {
            System.out.println(product);
        });
    }


    @Override
    public void setBeans() {
        if (result.getCode() == 200) {
            JSONObject object = JSON.parseObject(this.result.getBody());
            this.setCode(object.getString("code"));
            this.setMessage(object.getString("message"));
            this.setDataCount(object.getInteger("dataCount"));
            this.setPageCount(object.getInteger("pageCount"));
            this.setCode(object.getString("code"));
            JSONArray array = object.getJSONArray("data");
            array.forEach(product -> {
                //TODO
                productList.add(JSON.parseObject(JSON.toJSONString(product), Product.class));
            });
        } else {
            System.out.println("请求出错" + result.getCode());
        }
    }
}
