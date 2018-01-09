package com.smartdo.scc.mabang.backend.request;

import lombok.Data;

import java.util.Date;

/**
 * 1.7 获取企业采购商品信息Request
 */
@Data
public class ProductPurchaseInfoRequest extends Request {
    //Y:必填  N：非必填
    private static String myAction = "get-product-purchase-info-data";//Y 	get-product-purchase-info-data
    private Integer page; //Y 	当前页数：每页默认显示100条
    private Date updateTimeStart;  //N 	最后更新开始时间
    private Date updateTimeEnd;  ///N 	最后更新结束时间
    private String urlFormat = "&page=%d&updateTimeStart=%s&updateTimeEnd=%s";


    private ProductPurchaseInfoRequest(String action) {
        super(action);
    }

    @Override
    public String stitchingRequest() {
        if (page == null){
            System.out.println("ProductPurchaseInfoRequest需要page参数");
            return null;
        }else{
            String url = super.getPublicUrl()+"&page="+page;
            System.out.println(url);
            return url;
        }
        // /判断条件
        //return super.getPublicUrl() + String.format(urlFormat, page, updateTimeStart+"", updateTimeEnd+"");
    }

    public ProductPurchaseInfoRequest() {
        this(myAction);
    }
}
