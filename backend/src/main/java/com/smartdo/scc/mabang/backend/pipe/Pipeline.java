package com.smartdo.scc.mabang.backend.pipe;

import com.smartdo.scc.mabang.backend.exceptions.ResponseTypeError;
import com.smartdo.scc.mabang.backend.response.Response;

public interface Pipeline {
    void save(Response response) throws ResponseTypeError;
}
