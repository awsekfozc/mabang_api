package com.smartdo.scc.mabang.backend.request;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取商品信息请求类
 */
@Data
public class StockInfoRequst extends Request {

    private static String stockInfoAction = "get-stock-info-data";//Y
    private Integer page; //N 	当前页数默认值：1;每页输出 1000 条
    private String stockSku;//N 	库存 sku 编号
    private Date updateTimeStart;//N 	最后更新开始时间
    private Date updateTimeEnd;//N 	最后更新结束时间
//    private String urlFormat = "&page=%d&stockSku=%s&updateTimeStart=%s&updateTimeEnd=%s";

    private StockInfoRequst(String action) {
        super(action);
    }

    @Override
    public String stitchingRequest(){
        Map map = new HashMap();
        map.put("page",page);
        map.put("stockSku",stockSku);
        map.put("updateTimeStart",updateTimeStart);
        map.put("updateTimeEnd",updateTimeEnd);
        String Parameters = SplicingParameters(map);
        System.out.println(Parameters);
        System.out.println(super.getPublicUrl() + Parameters);
        return super.getPublicUrl() + Parameters;
        // return super.getPublicUrl() + String.format(urlFormat, page, stockSku, updateTimeStart+"", updateTimeEnd+"");
    }

    public StockInfoRequst() {
        this(stockInfoAction);
    }

}
