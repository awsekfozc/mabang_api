package com.smartdo.scc.mabang.backend.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.FbaInfo;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FbaInfoResponse extends Response {
    @Getter
    private List<FbaInfo> productList = new ArrayList<FbaInfo>();

    public FbaInfoResponse(HttpResult result) {
        super(result);
        setBeans();
    }


    @Override
    public void setBeans() {
        if (checkResultCode()) {
            JSONObject object = JSON.parseObject(this.result.getBody());
            this.setCode(object.getString("code"));
            this.setMessage(object.getString("message"));
            JSONArray dataArray = object.getJSONArray("data");
            for (Object fbaInfo : dataArray) {
                productList.add(JSON.parseObject(JSON.toJSONString(fbaInfo), FbaInfo.class));
            }
        }else{
            log.warn("请求失败或者");
        }
    }
}
