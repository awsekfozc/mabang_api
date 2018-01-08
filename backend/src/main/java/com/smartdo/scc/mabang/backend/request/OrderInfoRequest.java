package com.smartdo.scc.mabang.backend.request;

import lombok.Data;

import java.util.Date;

@Data
public class OrderInfoRequest extends Request{

    private static String myAction = "get-order-info-data"; //Y
    private Integer tableBase; //Y 	1未发货订单 2已发货订单（发货+完成+作废）
    private Integer page; //Y 	当前页数：每页默认显示100条
    private String platformOrderIds; //N 	订单编号多个已逗号隔开，最大支持10个
    private Integer status;     //N 	订单状态：2.配货中 3.已发货 4 已完成 5.已作废
    private Date updateTimeStart;//N 	最后更新开始时间
    private Date updateTimeEnd;//N 	最后更新结束时间
    private String urlFormat = "&tableBase=%d&page=%d&platformOrderIds=%s&status=%d&updateTimeStart=%s&updateTimeEnd=%s";

    private OrderInfoRequest(String action) {
        super(action);
    }

    @Override
    public String stitchingRequest() {
        if((tableBase != null) && (tableBase != page)){
            System.out.println("StockWarehouseInfoRequest缺少tableBase参数或page参数");
            System.out.println("StockWarehouseInfoRequest缺少tableBase参数或page参数");
            System.out.println("StockWarehouseInfoRequest缺少tableBase参数或page参数");
            return null;
        }else{
            String url = super.getPublicUrl() + "&tableBase="+ tableBase+ "&page="+page;
            System.out.println(url);
            return url;
//            return super.getPublicUrl() + String.format(urlFormat, tableBase, page, platformOrderIds, status, updateTimeStart+"", updateTimeEnd+"");
        }
    }

    public OrderInfoRequest() {
        this(myAction);
    }
}
