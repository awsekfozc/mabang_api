package com.smartdo.scc.mabang.backend;


import com.smartdo.scc.mabang.backend.exceptions.HttpClientError;
import com.smartdo.scc.mabang.backend.exceptions.ResponseTypeError;
import com.smartdo.scc.mabang.backend.factory.ResponseFactory;
import com.smartdo.scc.mabang.backend.pipe.Pipeline;
import com.smartdo.scc.mabang.backend.request.Request;
import com.smartdo.scc.mabang.backend.response.Response;
import com.smartdo.scc.mabang.common.helper.HttpAPIService;
import lombok.Data;
import org.junit.runners.model.InitializationError;

@Data
public  class MabangAPI {

    private Request request;
    private Pipeline pipeline;
    private Response response;

    private HttpAPIService service = new HttpAPIService();

    public MabangAPI(Request request) {
        this.request = request;
    }

    public MabangAPI setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    public static MabangAPI create(Request request) {
        return new MabangAPI(request);
    }

    /**
     * 发送请求
     *
     * @throws Exception
     */
    public void sendRequest() throws Exception {
        request.setResult(service.doGet(request.stitchingRequest()));
    }

    /**
     * 初始化响应对象
     */
    public void initResponse() throws HttpClientError {
        response = ResponseFactory.getResponse(request);
    }

    /**
     * 持久化
     *
     * @throws ResponseTypeError
     */
    public void pipe() throws ResponseTypeError {
        pipeline.save(response);
    }

    protected void checkStaus() throws InitializationError {
        if (request == null) {
            throw new InitializationError("请求对象未初始化");
        }
    }

    public void start() {
        try {
            sendRequest();
            initResponse();
            System.out.println("start存入数据库");
            pipe();
            System.out.println("end存入数据库");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
