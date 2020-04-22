package com.notebook.dubbo_test.service.Impl;

import com.notebook.dubbo_test.service.ProviderService;

/**
 * @author luorigong
 */
public class ProviderServiceImpl implements ProviderService {
    @Override
    public String sayHello(String word) {
        return word;
    }
}
