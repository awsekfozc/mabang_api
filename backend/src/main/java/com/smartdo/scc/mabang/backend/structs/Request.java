package com.smartdo.scc.mabang.backend.structs;

import java.util.HashMap;
import static com.smartdo.scc.mabang.backend.helper.Utility.arrayToCsv;

public class Request {
    public HashMap<String, String> parameter;
    public String postData;
    public String path;

    public Request() {
        parameter = new HashMap(20);
    }

    public static Request getProductRequest(final AmazonLocale domainId, Integer stats, Integer offers, final String... asins) {
        Request r = new Request();
        r.path = "product";
        r.parameter.put("asin", arrayToCsv(asins));
        r.parameter.put("domain", "" + domainId.ordinal());
        if (stats != null && stats > 0)
            r.parameter.put("stats", "" + stats);

        if (offers != null && offers > 0)
            r.parameter.put("offers", "" + offers);

        return r;
    }
}
