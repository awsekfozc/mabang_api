package com.smartdo.scc.mabang.backend.myWork;

import sun.net.www.http.HttpClient;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        String timestamp = (new Date().getTime() / 1000L) + "";
        String url = "http://api-private.wms.mabangerp.com?developerId=100197&authToken=eeb10fba7b268006b2f5febe2bd839e4&version=v2017&timestamp="+timestamp+"&action=get-stock-info-data";
        String reply = "";
        // 受理业务
        HttpClient client = new HttpClient();
        reply = client.sendGet(url);
        System.out.println(reply);
    }
}
