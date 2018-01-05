package com.smartdo.scc.mabang.backend.service;

import com.smartdo.scc.mabang.backend.bean.Product;


public interface ProductService {
    /**
     *
     */
    public String add(final Product product, final boolean alert);
}
