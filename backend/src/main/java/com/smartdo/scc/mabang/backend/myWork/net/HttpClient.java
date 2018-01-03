package com.smartdo.scc.mabang.backend.myWork.net;

import com.sun.deploy.net.HttpResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class HttpClient {
    private final String charset="UTF-8";
    private CloseableHttpClient client = null;
    private HttpResponse response = null;
    //private Logger log = Logger.getLogger(HttpClient.class);

    /**
     * http get
     * @param url String
     * @return String
     * @throws IOException
     */
    public String sendGet(String url) throws IOException{
        try {
            //client = HttpClients.createDefault();
            client = new SSLClient();
            response = client.execute(new HttpGet(url));
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            log.error("HttpClient get error",e);
        }finally {
            if (client != null) {
                client.close();
            }
        }
        return null;
    }
}
