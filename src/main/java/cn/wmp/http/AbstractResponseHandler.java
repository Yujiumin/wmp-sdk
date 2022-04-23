package cn.wmp.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Yujiumin
 * @version 2022/04/18
 */
public abstract class AbstractResponseHandler<T> implements ResponseHandler<T> {

    private static final int STATUS_CODE_OK = 200;

    private static final int CONTENT_LENGTH_2KB = 2048;

    @Override
    public T handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
        StatusLine statusLine = httpResponse.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        if (statusCode != STATUS_CODE_OK) {
            throw new RuntimeException("");
        }
        HttpEntity httpEntity = httpResponse.getEntity();
        long contentLength = httpEntity.getContentLength();
        if (contentLength != -1 && contentLength < CONTENT_LENGTH_2KB) {
            String responseBody = EntityUtils.toString(httpEntity);
            return handleResponse(responseBody);
        }

        StringBuilder responseBodyBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent()))) {
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    responseBodyBuilder.append(readLine);
                    continue;
                }
                break;
            }
        }

        return handleResponse(responseBodyBuilder.toString());
    }

    /**
     * 处理响应内容
     *
     * @param responseBody
     * @return
     */
    public abstract T handleResponse(String responseBody);
}
