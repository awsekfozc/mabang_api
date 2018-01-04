package com.smartdo.scc.mabang.backend;


import com.smartdo.scc.mabang.backend.pipe.Pipeline;
import com.smartdo.scc.mabang.backend.pipe.ProductPipeline;
import com.smartdo.scc.mabang.backend.request.ProductRequst;
import com.smartdo.scc.mabang.backend.request.Request;
import com.smartdo.scc.mabang.backend.response.Response;
import com.smartdo.scc.mabang.common.helper.HttpAPIService;
import com.smartdo.scc.mabang.common.helper.HttpResult;

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
