package com.wang.inis.configuration;



import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        List<Header> headers = new ArrayList();
        headers.add(new BasicHeader("Referer","https://burghquayregistrationoffice.inis.gov.ie/Website/AMSREG/AMSRegWeb.nsf/AppSelect?OpenForm"));
        headers.add(new BasicHeader("Host","burghquayregistrationoffice.inis.gov.ie"));
        headers.add(new BasicHeader("X-Requested-With","MLHttpRequest"));
        headers.add(new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36"));
        headers.add(new BasicHeader("Content-Type","application/json"));

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .setDefaultHeaders(headers)
                .setRetryHandler(new DefaultHttpRequestRetryHandler(3,true))
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory =  new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        requestFactory.setReadTimeout(5000);
        requestFactory.setConnectTimeout(5000);


        RestTemplate restTemplate = new RestTemplate(requestFactory);


        return restTemplate;
    }


}

/**
    private HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory(){
        final PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();



            // 设置最大连接数
            connManager.setMaxTotal(1000);

            // 设置每个连接的路由数
            connManager.setDefaultMaxPerRoute(1000);

        //设置header
        List<Header> headers = new ArrayList();
        headers.add(new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36"));
        headers.add(new BasicHeader("Referer","https://burghquayregistrationoffice.inis.gov.ie/Website/AMSREG/AMSRegWeb.nsf/AppSelect?OpenForm"));
        headers.add(new BasicHeader("Host","burghquayregistrationoffice.inis.gov.ie"));
        headers.add(new BasicHeader("X-Requested-With","MLHttpRequest"));
        //创建HttpClient
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .setDefaultHeaders(headers)
                .setRetryHandler(new HttpRequestRetryHandler() {
                    @Override
                    public boolean retryRequest(IOException exception, int executionCount, HttpContext httpContext) {
                        if(executionCount >= 3){// 如果已经重试了3次，就放弃
                            return false;
                        }
                        if(exception instanceof NoHttpResponseException){
                            // 如果服务器丢掉了连接，那么就重试
                            System.out.println("服务器丢掉了连接");
                            return true;
                        }
                        if(exception instanceof SSLHandshakeException){
                            // 不要重试SSL握手异常
                            System.out.println("SSL握手异常");
                            return false;
                        }
                        if(exception instanceof InterruptedIOException){
                            // 超时
                            System.out.println("超时");
                            return true;
                        }
                        if(exception instanceof UnknownHostException){
                            // 目标服务器不可达
                            System.out.println("目标服务器不可达");
                            return false;
                        }
                        if(exception instanceof ConnectTimeoutException){
                            // 连接被拒绝
                            System.out.println("连接被拒绝");
                            return false;
                        }
                        if(exception instanceof SSLException){
                            // ssl握手异常
                            System.out.println("ssl握手异常");
                            return false;
                        }
                        return false;
                    }
                })
                .build();
        //创建HttpComponentsClientHttpRequestFactory实例
        HttpComponentsClientHttpRequestFactory requestFactory =  new HttpComponentsClientHttpRequestFactory(httpClient);
        //设置客户端和服务端建立连接的超时时间
        requestFactory.setConnectTimeout(5000);
        //设置客户端从服务端读取数据的超时时间
        requestFactory.setReadTimeout(5000);
        //设置从连接池获取连接的超时时间，不宜过长
        requestFactory.setConnectionRequestTimeout(200);
        //缓冲请求数据，默认为true。通过POST或者PUT大量发送数据时，建议将此更改为false，以免耗尽内存
        requestFactory.setBufferRequestBody(false);
        return requestFactory;
    }
}

**/