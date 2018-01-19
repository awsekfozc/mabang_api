package com.smartdo.scc.mabang.backend.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.OrderInfo;
import com.smartdo.scc.mabang.backend.bean.OrderItem;
import com.smartdo.scc.mabang.backend.exceptions.HttpClientError;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderInfoResponse extends Response {
    @Getter
    private List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
    @Getter
    private List<OrderItem> orderItemList = new ArrayList<OrderItem>();

    public OrderInfoResponse(HttpResult result) throws   HttpClientError{
        super(result);
        setBeans();
    }


    @Override
    public void setBeans()throws   HttpClientError {
        if (result.getCode() == 200) {
            JSONObject object = JSON.parseObject(this.result.getBody());
            this.setCode(object.getString("code"));
            this.setMessage(object.getString("message"));
            this.setDataCount(object.getInteger("dataCount"));
            this.setPageCount(object.getInteger("pageCount"));
            if (object.getString("code").equals("000")) {
                JSONArray dataArray = object.getJSONArray("data");
                for (int i = 0; i < dataArray.size(); i++) {
                    JSONObject orderInfoObj = dataArray.getJSONObject(i);
                    OrderInfo orderInfo = JSON.parseObject(JSON.toJSONString(orderInfoObj), OrderInfo.class);
                    orderInfoList.add(orderInfo);
                    String platformOrderIdStr = orderInfoObj.getString("platformOrderId");
                    JSONArray orderItemArray = orderInfoObj.getJSONArray("orderItem");
                    for (int j = 0; j < orderItemArray.size(); j++) {
                        Object orderItem = orderItemArray.get(j);
                        OrderItem orderItemEntity = JSON.parseObject(JSON.toJSONString(orderItem), OrderItem.class);
                        orderItemEntity.setPlatformOrderId(platformOrderIdStr);
                        orderItemList.add(orderItemEntity);
                    }
                }
            } else {
                log.warn("查询结果为：" + object);
                throw new HttpClientError("查询结果为：" + object);
            }
        } else {
            log.warn("请求出错" + result.getCode());
            throw new HttpClientError("请求出错" + result.getCode());
        }
    }
}
