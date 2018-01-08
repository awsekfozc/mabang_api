package com.smartdo.scc.mabang.backend.request;

import lombok.Data;

import java.util.Date;

/**
 * 1.6 获取企业FBA商品信息
 */
@Data
public class FbaInfoRequst extends Request {

    private static String myAction = "get-fba-info-data"; //必填参数
    private Integer page; //必填参数
    private Date updateTimeStart;
    private Date updateTimeEnd;
    private String urlFormat = "&page=%d";
//    private String urlFormat = "&page=%d&updateTimeStart=%s&updateTimeEnd=%s";

    private FbaInfoRequst(String action) {
        super(action);
    }

    @Override
    public String stitchingRequest() {
        if (page == null){
            System.out.println("缺少必填参数page");
            System.out.println("缺少必填参数page");
            System.out.println("缺少必填参数page");
            return null;
        }else{
            return super.getPublicUrl() + String.format(urlFormat, page);
        }
    }

    public FbaInfoRequst() {
        this(myAction);
    }

}
