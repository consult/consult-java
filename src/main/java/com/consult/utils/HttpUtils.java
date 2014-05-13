package com.consult.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtils {

    public String get(String url) throws ClientProtocolException, IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpGet httpGet = new HttpGet(url);

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    }else{
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };

            return httpClient.execute(httpGet, responseHandler);
        }finally{
            httpClient.close();
        }
    }

    public String post(String url, String body) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPost httpPost = new HttpPost(url);
            HttpEntity entity = new ByteArrayEntity(body.getBytes("UTF-8"));
            httpPost.setEntity(entity);

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    }else{
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };

            return httpClient.execute(httpPost, responseHandler);
        }finally{
            httpClient.close();
        }
    }
}
