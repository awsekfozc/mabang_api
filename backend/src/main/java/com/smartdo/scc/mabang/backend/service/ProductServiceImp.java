package com.smartdo.scc.mabang.backend.service;

import com.smartdo.scc.mabang.backend.bean.Product;
import com.smartdo.scc.mabang.backend.inner.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public String add(Product product, boolean alert) {
        int rnt = productMapper.add(product);
        if(1 != rnt){
            return "fail";
        }
        return "success";
    }
}
