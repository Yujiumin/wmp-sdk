package cn.wmp.request;

import cn.wmp.http.Executable;
import cn.wmp.model.GetAccessTokenRequestModel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

/**
 * @author Yujiumin
 * @version 2022/04/24
 */
@Slf4j
public class GetAccessTokenRequest implements Executable {

    private static final String URI_PATH = "https://api.weixin.qq.com/cgi-bin/token";

    private HttpGet httpGet;

    @Getter
    private GetAccessTokenRequestModel getAccessTokenRequestModel;

    public GetAccessTokenRequest(GetAccessTokenRequestModel getAccessTokenRequestModel) {
        this.getAccessTokenRequestModel = getAccessTokenRequestModel;
        try {
            final URI uri = buildUri(getAccessTokenRequestModel);
            this.httpGet = new HttpGet(uri);
        } catch (URISyntaxException ex) {
            if (log.isErrorEnabled()) {
                log.error("URI构建失败", ex);
            }
        }
    }

    private URI buildUri(GetAccessTokenRequestModel getAccessTokenRequestModel) throws URISyntaxException {
        return new URIBuilder(URI_PATH, StandardCharsets.UTF_8)
                .addParameter("grant_type", getAccessTokenRequestModel.getGrantType())
                .addParameter("appid", getAccessTokenRequestModel.getAppId())
                .addParameter("secret", getAccessTokenRequestModel.getSecret())
                .build();
    }

    @Override
    public HttpUriRequest getRequest() {
        return httpGet;
    }
}
