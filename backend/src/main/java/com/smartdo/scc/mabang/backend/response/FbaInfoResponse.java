package com.smartdo.scc.mabang.backend.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.FbaInfo;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class FbaInfoResponse extends Response{
    @Getter
    private List<FbaInfo> productList = new ArrayList<FbaInfo>();

    public FbaInfoResponse(HttpResult result) {
        super(result);
        setBeans();
    }


    @Override
    public void setBeans() {
        if (result.getCode() == 200) {
            JSONObject object = JSON.parseObject(this.result.getBody());
            System.out.println(object);
            System.out.println("==================================");
            this.setCode(object.getString("code"));
            this.setMessage(object.getString("message"));
            if (object.getString("code").equals("000")){
                String bb = object.getString("data");
                if(!bb.equals("[]")){
                    JSONArray dataArray = object.getJSONArray("data");
                    for(Object fbaInfo:dataArray){
                        System.out.println(JSON.parseObject(JSON.toJSONString(fbaInfo), FbaInfo.class));
                        productList.add(JSON.parseObject(JSON.toJSONString(fbaInfo), FbaInfo.class));
                    }
                }else {
                   System.out.println("查询结果为为：" +object);
                }
            }else{
                System.out.println(object.getString("message"));
            }
        } else {
            System.out.println("请求出错" + result.getCode());
        }
    }
}
