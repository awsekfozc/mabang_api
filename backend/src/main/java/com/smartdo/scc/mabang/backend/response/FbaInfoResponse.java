package com.smartdo.scc.mabang.backend.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.FbaInfo;
import com.smartdo.scc.mabang.backend.exceptions.HttpClientError;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FbaInfoResponse extends Response {
    @Getter
    private List<FbaInfo> fbaInfoList = new ArrayList<FbaInfo>();

    public FbaInfoResponse(HttpResult result) throws HttpClientError {
        super(result);
        setBeans();
    }


    @Override
    public void setBeans() throws HttpClientError {
        if (checkResultCode()) {
            JSONObject object = JSON.parseObject(this.result.getBody());
            this.setCode(object.getString("code"));
            this.setMessage(object.getString("message"));
            this.setDataCount(object.getInteger("dataCount"));
            this.setPageCount(object.getInteger("pageCount"));
            JSONArray dataArray = object.getJSONArray("data");
            for (int j = 0; j < dataArray.size(); j++) {
                JSONObject fbaInfoJson = dataArray.getJSONObject(j);
                FbaInfo entity = JSON.parseObject(JSON.toJSONString(fbaInfoJson), FbaInfo.class);
                entity.setUniqueId(fbaInfoJson.getString("id"));
                fbaInfoList.add(entity);
            }
        }else{
            log.warn("请求失败或者请求参数不对");
            throw new HttpClientError("请求失败或者请求参数不对");
        }
    }
}
