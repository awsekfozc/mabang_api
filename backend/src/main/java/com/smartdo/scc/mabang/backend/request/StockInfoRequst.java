package com.smartdo.scc.mabang.backend.request;

import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
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

    private StockInfoRequst(String action) {
        super(action);
    }

    @Override
    public String stitchingRequest(){
        Map map = new HashMap();
        map.put("page",page);
        map.put("stockSku",stockSku);


        if(updateTimeStart !=null && updateTimeEnd !=null) {
            SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
            String UpdateTimeStartStr = "";
            String UpdateTimeEndStr = "";
            try {
                UpdateTimeStartStr = java.net.URLEncoder.encode(sdf.format(updateTimeStart), "utf-8");
                UpdateTimeEndStr = java.net.URLEncoder.encode(sdf.format(updateTimeEnd), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            map.put("updateTimeStart", UpdateTimeStartStr);
            map.put("updateTimeEnd", UpdateTimeEndStr);
        }

        String Parameters = SplicingParameters(map);
        return super.getPublicUrl() + Parameters;
    }

    public StockInfoRequst() {
        this(stockInfoAction);
    }

}
