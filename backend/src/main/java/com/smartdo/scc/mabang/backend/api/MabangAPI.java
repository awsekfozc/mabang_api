package com.smartdo.scc.mabang.backend.api;


import com.smartdo.scc.mabang.backend.factory.ResponseFactory;
import com.smartdo.scc.mabang.backend.pipe.Pipeline;
import com.smartdo.scc.mabang.backend.pipe.ProductPipeline;
import com.smartdo.scc.mabang.backend.request.ProductRequst;
import com.smartdo.scc.mabang.backend.request.Request;
import com.smartdo.scc.mabang.backend.response.Response;
import com.smartdo.scc.mabang.common.helper.HttpAPIService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sellerInfo/setting")
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
            request.setResult(service.doGet(requestUrl));
            response = ResponseFactory.getResponse(request);
            pipeline.save(response);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public  void startTest(){
        ProductRequst request = new ProductRequst();
        MabangAPI.create(request)
                .setPipeline(new ProductPipeline())
                .start();
    }

}
