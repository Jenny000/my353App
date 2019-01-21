package com.wang.inis.utils;

import io.netty.channel.ConnectTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
@Component
@Slf4j
public class HttpClient {




    private static final PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
    /**
     * 静态代码块配置连接池信息
     */
    static{

        // 设置最大连接数
        connManager.setMaxTotal(100);

        // 设置每个连接的路由数
        connManager.setDefaultMaxPerRoute(100);

    }

    /**
     * 获取Http客户端连接对象
     *
     * @param timeOut 超时时间
     * @return Http客户端连接对象
     */

    public static CloseableHttpClient getHttpClient(Integer timeOut) {
        // 创建Http请求配置参数
        RequestConfig requestConfig = RequestConfig.custom()
                // 获取连接超时时间
                .setConnectionRequestTimeout(timeOut)
                // 请求超时时间
                .setConnectTimeout(timeOut)
                // 响应超时时间
                .setSocketTimeout(timeOut)
                .build();


        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = null;
        try {
            sslContext = org.apache.http.ssl.SSLContexts.custom()
                    .loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        /**
         * 测出超时重试机制为了防止超时不生效而设置
         *  如果直接放回false,不重试
         *  这里会根据情况进行判断是否重试
         */

        HttpRequestRetryHandler retry = new HttpRequestRetryHandler() {

            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext httpContext) {
                if(executionCount >= 3){// 如果已经重试了3次，就放弃
                    return false;
                }
                if(exception instanceof NoHttpResponseException){
                    // 如果服务器丢掉了连接，那么就重试
                    log.error("服务器丢掉了连接");
                    return true;
                }
                if(exception instanceof SSLHandshakeException){
                    // 不要重试SSL握手异常
                    log.error("SSL握手异常");
                    return false;
                }
                if(exception instanceof InterruptedIOException){
                    // 超时
                    log.error("超时");
                    return true;
                }
                if(exception instanceof UnknownHostException){
                    // 目标服务器不可达
                    log.error("目标服务器不可达");
                    return false;
                }
                if(exception instanceof ConnectTimeoutException){
                    // 连接被拒绝
                    log.error("连接被拒绝");
                    return true;
                }
                if(exception instanceof SSLException){
                    // ssl握手异常
                    log.error("ssl握手异常");
                    return false;
                }
                return false;
            }
        };
        // 创建httpClient
        return HttpClients.custom()
                // 把请求相关的超时信息设置到连接客户端
                .setDefaultRequestConfig(requestConfig)
                // 把请求重试设置到连接客户端
                .setRetryHandler(retry)
                .setSSLSocketFactory(csf)
                .build();



    }

}
