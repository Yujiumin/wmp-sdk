package cn.wmp.request;

import cn.wmp.http.Executable;
import cn.wmp.model.CheckEncryptedDataRequestModel;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CheckEncryptedDataRequest implements Executable {

    private HttpPost httpPost;

    @Getter
    private CheckEncryptedDataRequestModel checkEncryptedDataRequestModel;

    @Getter
    private RequestBody requestBody;

    private static final String URI_PATH = "https://api.weixin.qq.com/wxa/business/checkencryptedmsg";

    public CheckEncryptedDataRequest(CheckEncryptedDataRequestModel checkEncryptedDataRequestModel) {
        this.checkEncryptedDataRequestModel = checkEncryptedDataRequestModel;
        this.requestBody = new RequestBody(checkEncryptedDataRequestModel.getEncryptedMessageHash());
        try {
            final URI uri = buildUri(checkEncryptedDataRequestModel.getAccessToken());
            final HttpEntity httpEntity = buildEntity(requestBody);
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setEntity(httpEntity);
            this.httpPost = httpPost;
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

    private URI buildUri(String accessToken) throws URISyntaxException {
        return new URIBuilder(URI_PATH, StandardCharsets.UTF_8)
                .addParameter("access_token", accessToken)
                .build();
    }

    private HttpEntity buildEntity(RequestBody requestBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        final String requestBodyString = objectMapper.writeValueAsString(requestBody);
        return new StringEntity(requestBodyString, StandardCharsets.UTF_8);
    }

    @Override
    public HttpUriRequest getRequest() {
        return httpPost;
    }

    @Data
    private static class RequestBody {

        @JsonProperty("encrypted_msg_hash")
        private String encryptedMsgHash;

        public RequestBody(String encryptedMsgHash) {
            this.encryptedMsgHash = encryptedMsgHash;
        }
    }
}
