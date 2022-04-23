package cn.wmp.http;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * @author Yujiumin
 * @version 2022/04/18
 */
public class HttpClient {

    private volatile static HttpClient httpClient;

    private CloseableHttpClient closeableHttpClient;

    private HttpClient() {
        closeableHttpClient = HttpClients.custom()
                .build();
    }

    public static HttpClient getInstance() {
        if (httpClient == null) {
            synchronized (HttpClient.class) {
                if (httpClient == null) {
                    httpClient = new HttpClient();
                }
            }
        }
        return httpClient;
    }

    public <T> T execute(Executable executable, AbstractResponseHandler<T> abstractResponseHandler) throws IOException {
        HttpUriRequest httpUriRequest = executable.getRequest();
        return closeableHttpClient.execute(httpUriRequest, abstractResponseHandler);
    }

    @Override
    protected void finalize() throws Throwable {
        closeableHttpClient.close();
    }
}
