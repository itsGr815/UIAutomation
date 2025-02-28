package com.dFarm.qa.dFarm.util.reporters;

import com.dFarm.qa.dFarm.util.execution.CommonUtil;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.parser.Entity;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Set;

public class WSCaller {
    private static final Logger Logger = LoggerFactory.getLogger(WSCaller.class);

    public static String postRequest(String endPoint, Map<String, String> headers, String postData) throws WSException, FlexFrameWorkRunTimeException {
        String response;
        try {
            RequestSpecification httpPostRequest = RestAssured.given();
            if (headers != null){
                Set<String> keys = headers.keySet();
                for (String key : keys){
                    httpPostRequest.header(key, headers.get(key));
                }
            }
            httpPostRequest.body(postData);
            CommonUtil util = new CommonUtil();
            response = util.getResponse(httpPostRequest.post(endPoint));
        }catch (Exception e){
            throw new WSException(e);
        }
        return response;
    }

    public HttpResponse getRequest(Map<String, String> serviceHeadersData, String endpointWithData) throws WSException, FlexFrameWorkRunTimeException {
        HttpResponse response = null;
        try {
            CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new AllowAllHostnameVerifier()).
                    setSslcontext(new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                        @Override
                        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            return true;
                        }
                    }).build()).build();
            HttpGet httpgetRequest = new HttpGet(endpointWithData);
            if (serviceHeadersData != null){
                for (Map.Entry<String, String> serviceHeader : serviceHeadersData.entrySet()){
                    httpgetRequest.addHeader(serviceHeader.getKey(), serviceHeader.getValue());
                }
            }
            response = httpClient.execute(httpgetRequest);
        }catch (Exception e){
            Logger.error("Unable to hit the Service with End point : "+ endpointWithData);
            throw new WSException(e);
        }
        return response;
    }

    public static HttpResponse postRequestWithHttp(String endPoint, Map<String, String> serviceHeaders, String postData) throws WSException, FlexFrameWorkRunTimeException {
        HttpResponse response = null;
        String postDataForLog = endPoint.contains("SecurityTokenService") ?  "" : postData;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(endPoint);
            for (Map.Entry<String, String> serviceHeader : serviceHeaders.entrySet()){
                post.addHeader(serviceHeader.getKey(), serviceHeader.getValue());
            }
            StringEntity entity = new StringEntity(postData, "UFT-8");
            post.setEntity(entity);
            response = client.execute(post);
        }catch (Exception e){
            Logger.error("Unable to hit the Service with End point :" + endPoint + "\n Data : " + postDataForLog + e);
            throw new WSException(e);
        }
        return response;
    }

}
