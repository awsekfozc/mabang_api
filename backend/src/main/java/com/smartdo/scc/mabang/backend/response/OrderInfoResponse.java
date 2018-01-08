package com.smartdo.scc.mabang.backend.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.OrderInfo;
import com.smartdo.scc.mabang.backend.bean.OrderItem;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class OrderInfoResponse extends Response {
    @Getter
    private List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
    @Getter
    private List<OrderItem> orderItemList = new ArrayList<OrderItem>();

    public OrderInfoResponse(HttpResult result) {
        super(result);
        setBeans();
    }


    @Override
    public void setBeans() {
        if (result.getCode() == 200) {
            JSONObject object = JSON.parseObject(this.result.getBody());
            this.setCode(object.getString("code"));
            this.setMessage(object.getString("message"));
            if (object.getString("code").equals("000")) {
                String bb = object.getString("data");
                if (!bb.equals("[]")) {
                    JSONArray dataArray = object.getJSONArray("data");
                    for (int i = 0; i < dataArray.size(); i++) {
                        JSONObject orderInfoObj = dataArray.getJSONObject(i);
                        //这里OrderInfo故意少写一个orderItem参数，后期验证！！！
                        orderInfoList.add(JSON.parseObject(JSON.toJSONString(orderInfoObj), OrderInfo.class));
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
                    System.out.println("查询结果为：" + object);
                }
            } else {
                System.out.println(object.getString("message"));
            }
        } else {
            System.out.println("请求出错" + result.getCode());
        }
    }
}
