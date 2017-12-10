package com.kute.demo.http.resttemplate;

import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kute on 2017/12/9.
 * http://blog.csdn.net/u010476464/article/details/50067079
 */
public class RestTemplateUtil implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Bean
    private PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(400);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(200);
        return poolingHttpClientConnectionManager;
    }

    @Bean
    private HttpRequestRetryHandler httpRequestRetryHandler() {
        return new DefaultHttpRequestRetryHandler(3, true);
    }

    @Bean
    private HttpClientBuilder httpClientBuilder() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setRetryHandler(httpRequestRetryHandler());
        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager());
        return httpClientBuilder;
    }

    @Bean
    private HttpClient httpClient() {
        return httpClientBuilder().build();
    }

    @Bean
    private HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient());
        httpComponentsClientHttpRequestFactory.setReadTimeout(200);
        httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(5000);
        httpComponentsClientHttpRequestFactory.setConnectTimeout(5000);
        return httpComponentsClientHttpRequestFactory;
    }

    @Bean
    private DefaultResponseErrorHandler defaultResponseErrorHandler() {
        return new DefaultResponseErrorHandler();
    }

    @Bean
    private RestTemplate restTemplate() {
        return new RestTemplate(httpComponentsClientHttpRequestFactory());
    }
}
