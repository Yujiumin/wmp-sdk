package cn.wmp.request;

import cn.wmp.http.Executable;
import cn.wmp.model.GetPluginOpenPidRequestModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

/**
 * @author Yujiumin
 * @version 2022/04/24
 */
@Slf4j
public class GetPluginOpenPidRequest implements Executable {

    private static final String URI_PATH = "https://api.weixin.qq.com/wxa/getpluginopenpid";

    private HttpPost httpPost;

    @Getter
    private GetPluginOpenPidRequestModel getPluginOpenPidRequestModel;

    public GetPluginOpenPidRequest(GetPluginOpenPidRequestModel getPluginOpenPidRequestModel) {
        this.getPluginOpenPidRequestModel = getPluginOpenPidRequestModel;
        try {
            final URI uri = buildUri(getPluginOpenPidRequestModel);
            final HttpEntity httpEntity = buildHttpEntity(getPluginOpenPidRequestModel);
            this.httpPost = buildHttpRequest(uri, httpEntity);
        } catch (URISyntaxException ex) {
            if (log.isErrorEnabled()) {
                log.error("URI构建失败", ex);
            }
        } catch (JsonProcessingException ex) {
            if (log.isErrorEnabled()) {
                log.error("请求参数序列化失败", ex);
            }
        }
    }

    private URI buildUri(GetPluginOpenPidRequestModel getPluginOpenPidRequestModel) throws URISyntaxException {
        return new URIBuilder(URI_PATH, StandardCharsets.UTF_8)
                .addParameter("access_token", getPluginOpenPidRequestModel.getAccessToken())
                .build();
    }

    private HttpEntity buildHttpEntity(GetPluginOpenPidRequestModel getPluginOpenPidRequestModel) throws JsonProcessingException {
        RequestBody requestBody = new RequestBody();
        requestBody.setCode(getPluginOpenPidRequestModel.getCode());

        ObjectMapper objectMapper = new ObjectMapper();
        final String requestBodyString = objectMapper.writeValueAsString(requestBody);
        return new StringEntity(requestBodyString, StandardCharsets.UTF_8);
    }

    private HttpPost buildHttpRequest(URI uri, HttpEntity httpEntity) {
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setEntity(httpEntity);
        return httpPost;
    }

    @Override
    public HttpUriRequest getRequest() {
        return httpPost;
    }

    @Data
    private static class RequestBody {
        private String code;
    }
}
