package com.kute.demo.service.impl;

import com.kute.demo.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * created on 2018-03-28 15:35
 */
@Service
public class ProductService implements IProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Override
    public void createProduct() {
        LOGGER.info("create product:{}", "ok");
    }

}
