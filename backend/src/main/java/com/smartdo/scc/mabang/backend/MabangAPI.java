package com.smartdo.scc.mabang.backend;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.Product;
import com.smartdo.scc.mabang.backend.pipe.Pipeline;
import com.smartdo.scc.mabang.backend.pipe.ProductPipeline;
import com.smartdo.scc.mabang.backend.request.ProductRequst;
import com.smartdo.scc.mabang.backend.request.Request;
import com.smartdo.scc.mabang.backend.response.Response;
import com.smartdo.scc.mabang.backend.response.StockResponse;
import com.smartdo.scc.mabang.common.helper.BeanUtil;
import com.smartdo.scc.mabang.common.helper.HttpAPIService;
import com.smartdo.scc.mabang.common.helper.HttpResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class MabangAPI {

    private Request request;
    private Pipeline pipeline;
    private Response response;
    private HttpAPIService service = new HttpAPIService();

    private MabangAPI(Request request) {
        this.request = request;
    }

    public MabangAPI setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;

        return this;
    }

    public static MabangAPI create(Request request) {
        return new MabangAPI(request);
    }

    public void start() {
        try {
            String requestUrl = request.stitchingRequest();
            System.out.println("请求url:" + requestUrl);
            HttpResult result = service.doGet(requestUrl);

//            if(result.getCode()==200){
//               String resultStr =  result.getBody();
//               JSONObject json  = JSONObject.parseObject(resultStr);
//               String code = (String) json.get("code");
//               if(code.equals("000")){
//                   String data = (String) json.get("data");
//                   JSONArray dataArray  = JSONArray.parseArray(data);
//                   List list  = new ArrayList();
//                   Product product = new Product();
//                   for (int i = 0; i < dataArray.size(); i++) {
//                       Map maps = (Map) JSON.parse(dataArray.get(i).toString());
////                     BeanUtil.setProperty(Map<String, Object> map, T bean, Class<T> clz);
//                       Product productBean = BeanUtil.setProperty(maps,product,Product.class);
//                       list.add(productBean);
//                       //存储
//                   }
//               }
//            }else{
//                System.out.println(result.getCode());
//            }

            if(result.getCode()==200){
                JSONObject jsonObj  = JSONObject.parseObject(result.getBody());
                String code = (String) jsonObj.get("code");
                Product product = new Product();
                if(code.equals("000")){
                    Map maps = (Map)JSON.parse(result.getBody());


                    response = BeanUtil.setProperty(maps,product,?);

                }
            }else{
                System.out.println("请求出错"+result.getCode());
            }

            System.out.println(result);
        } catch (Exception ex) {

        }
    }

    public static void main(String[] args) {
        ProductRequst request = new ProductRequst();
        MabangAPI.create(request)
                .setPipeline(new ProductPipeline())
                .start();
    }
}
