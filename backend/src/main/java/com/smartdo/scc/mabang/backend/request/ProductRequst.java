package com.smartdo.scc.mabang.backend.request;

import lombok.Data;

import java.util.Date;

/**
 * 获取商品信息请求类
 */
@Data
public class ProductRequst extends Request {

    private static String productAction = "get-stock-info-data";
    private Integer page;
    private String stockSku;
    private Date updateTimeStart;
    private Date updateTimeEnd;
    private String urlFormat = "&page=%d&stockSku=%s&updateTimeStart=%s&updateTimeEnd=%s";

    private ProductRequst(String action) {
        super(action);
    }

    @Override
    public String stitchingRequest() {
        return super.getPublicUrl();
//        return super.getPublicUrl() + String.format(urlFormat, page, stockSku, updateTimeStart+"", updateTimeEnd+"");
    }

    public ProductRequst() {
        this(productAction);
    }

}
