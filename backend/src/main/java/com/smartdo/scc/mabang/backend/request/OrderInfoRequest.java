package com.smartdo.scc.mabang.backend.request;

import com.smartdo.scc.mabang.backend.exceptions.IncorrectParametersError;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Data
@Slf4j
public class OrderInfoRequest extends Request{

    private static String orderInfoAction = "get-order-info-data"; //Y
    private Integer tableBase; //Y 	1未发货订单 2已发货订单（发货+完成+作废）
    private Integer page; //Y 	当前页数：每页默认显示100条
    private String platformOrderIds; //N 	订单编号多个已逗号隔开，最大支持10个
    private Integer status;     //N 	订单状态：2.配货中 3.已发货 4 已完成 5.已作废
    private Date updateTimeStart;//N 	最后更新开始时间
    private Date updateTimeEnd;//N 	最后更新结束时间

    private OrderInfoRequest(String action) {
        super(action);
    }

    @Override
    public String stitchingRequest()throws IncorrectParametersError {
       if((tableBase == null) || ((tableBase != 1) && (tableBase != 2))  || (page == null) ){
            throw new IncorrectParametersError("OrderInfoRequest必须正确设置[tableBase]参数或者[page]参数");
        }else {
           if(status != null && (status != 2) && (status != 3) && (status != 4) && (status != 5) ){
               throw new IncorrectParametersError("OrderInfoRequest必须正确设置[status]参数");
           }
           Map map = new HashMap();
           map.put("tableBase", tableBase);
           map.put("page", page);
           map.put("platformOrderIds", platformOrderIds);
           map.put("status", status);

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
           log.info(super.getPublicUrl() + Parameters);
           return super.getPublicUrl() + Parameters;
       }
    }

    public OrderInfoRequest() {
        this(orderInfoAction);
    }
}
