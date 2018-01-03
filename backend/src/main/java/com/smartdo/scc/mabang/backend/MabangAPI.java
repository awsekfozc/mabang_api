package com.smartdo.scc.mabang.backend;

import com.google.gson.stream.JsonReader;
import org.jdeferred.impl.DeferredObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;

public final class MabangAPI {

    /**
     * Thread pool size determines degree of asynchronization.
     */
    final private ExecutorService executorDeferred;
    final private ExecutorService executorRetry;

    final private String accessKey;
    final private String userAgent;
    final private int maxDelay = 60000;

    public enum ResponseStatus {
        PENDING, OK, FAIL, NOT_ENOUGH_TOKEN, REQUEST_REJECTED, PAYMENT_REQUIRED, METHOD_NOT_ALLOWED
    }

    /**
     * @param key     Your private API Access Token
     * @param threads Thread pool size determines degree of asynchronization. Higher thread count allows more requests in parallel to be made. Default 4
     */
    public MabangAPI(String key, int threads) {
        this.accessKey = key;
        String apiVersion = getClass().getPackage().getImplementationVersion();
        if (apiVersion != null) {
            userAgent = "KEEPA-JAVA Framework-" + apiVersion;
        } else {
            userAgent = "KEEPA-JAVA Framework-";
        }

        executorDeferred = Executors.newFixedThreadPool(threads, new BasicNameFactory("KeepaAPI-%d"));
        executorRetry = Executors.newFixedThreadPool(threads, new BasicNameFactory("KeepaAPI-RetryScheduler"));
    }

    /**
     * @param key Your private API Access Token
     */
    public MabangAPI(String key) {
        this(key, 4);
    }

    /**
     * Issue a request to the Keepa Price Data API.
     * If your tokens are depleted, this method will fail.
     *
     * @param r the API Request {@link Request}
     * @return Promise for {@link Response}
     */
    final public Promise<Response, Response, Void> sendRequest(Request r) {
        Deferred<Response, Response, Void> d = new DeferredObject<>();

        if (r == null) {
            d.reject(null);
            return d.promise();
        }

        long responseTime = System.nanoTime();
        Response response;

        String query = r.parameter.entrySet().stream()
                .map(p -> urlEncodeUTF8(p.getKey()) + "=" + urlEncodeUTF8(p.getValue()))
                .reduce((p1, p2) -> p1 + "&" + p2)
                .orElse("");

        String url = "https://api.keepa.com/" + r.path + "/?key=" + accessKey + "&" + query;

        try {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setUseCaches(false);
            con.setRequestProperty("User-Agent", this.userAgent);
            con.setRequestProperty("Connection", "keep-alive");
            con.setRequestProperty("Accept-Encoding", "gzip");
            con.setConnectTimeout(40000);
            con.setReadTimeout(120000);
            con.setRequestProperty("Accept-Encoding", "gzip");
            if (r.postData != null) {
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                con.setDoOutput(true);
                OutputStream os = con.getOutputStream();
                os.write(r.postData.getBytes("UTF-8"));
                os.close();
            } else
                con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            if (responseCode == 200) {
                try (InputStream is = con.getInputStream();
                     GZIPInputStream gis = new GZIPInputStream(is)) {
                    JsonReader reader = new JsonReader(new InputStreamReader(gis, "UTF-8"));
                    response = gson.fromJson(reader, Response.class);
                    response.status = ResponseStatus.OK;
                } catch (Exception e) {
                    response = Response.REQUEST_FAILED;
                    e.printStackTrace();
                }
            } else {
                try (InputStream is = con.getErrorStream();
                     GZIPInputStream gis = new GZIPInputStream(is)) {
                    JsonReader reader = new JsonReader(new InputStreamReader(gis, "UTF-8"));
                    response = gson.fromJson(reader, Response.class);
                    switch (responseCode) {
                        case 400:
                            response.status = ResponseStatus.REQUEST_REJECTED;
                            break;
                        case 402:
                            response.status = ResponseStatus.PAYMENT_REQUIRED;
                            break;
                        case 405:
                            response.status = ResponseStatus.METHOD_NOT_ALLOWED;
                            break;
                        case 429:
                            response.status = ResponseStatus.NOT_ENOUGH_TOKEN;
                            break;
                        default:
                            response = Response.REQUEST_FAILED;
                            break;
                    }
                } catch (Exception e) {
                    response = Response.REQUEST_FAILED;
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            response = Response.REQUEST_FAILED;
        }

        response.requestTime = (System.nanoTime() - responseTime) / 1000000;
        if (response.status == ResponseStatus.OK)
            d.resolve(response);
        else
            d.reject(response);

        return d.promise();
    }
}
