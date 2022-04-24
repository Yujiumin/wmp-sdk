package cn.wmp.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * @author Yujiumin
 * @version 2022/04/18
 */
@Slf4j
public class HttpClient {

    private volatile static HttpClient httpClient;

    private final CloseableHttpClient closeableHttpClient;

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

    /**
     * 执行http请求
     *
     * @param executable              可执行对象
     * @param abstractResponseHandler 响应处理器
     * @param <T>                     响应实体泛型
     * @return 响应实体
     * @throws IOException 执行异常
     */
    public <T> T execute(Executable executable, AbstractResponseHandler<T> abstractResponseHandler) throws IOException {
        HttpUriRequest httpUriRequest = executable.getRequest();
        if (log.isInfoEnabled()) {
            log.info("httpRequest -> {}", httpUriRequest.toString());
        }
        return closeableHttpClient.execute(httpUriRequest, abstractResponseHandler);
    }

    @Override
    protected void finalize() throws Throwable {
        closeableHttpClient.close();
    }
}
