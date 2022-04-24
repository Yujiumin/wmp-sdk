package cn.wmp.http;

import cn.wmp.util.StreamUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Yujiumin
 * @version 2022/04/18
 */
public abstract class AbstractResponseHandler<T> implements ResponseHandler<T> {

    private static final int STATUS_CODE_OK = 200;

    private static final int CONTENT_LENGTH_2KB = 2048;

    @Override
    public T handleResponse(HttpResponse httpResponse) throws IOException {
        StatusLine statusLine = httpResponse.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        HttpEntity httpEntity = httpResponse.getEntity();
        if (statusCode != STATUS_CODE_OK) {
            EntityUtils.consume(httpEntity);
            throw new HttpResponseException(statusCode, statusLine.getReasonPhrase());
        }
        long contentLength = httpEntity.getContentLength();
        if (contentLength != -1 && contentLength < CONTENT_LENGTH_2KB) {
            String responseBody = EntityUtils.toString(httpEntity);
            return handleResponse(responseBody);
        }

        final InputStream httpEntityContent = httpEntity.getContent();
        String responseBody = StreamUtils.copyToString(httpEntityContent, StandardCharsets.UTF_8);
        return handleResponse(responseBody);
    }

    /**
     * 处理响应内容
     *
     * @param responseBody 响应数据字符串
     * @return 实体泛型
     */
    public abstract T handleResponse(String responseBody);
}
