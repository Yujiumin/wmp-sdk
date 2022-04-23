package cn.wmp.request;

import cn.wmp.http.Executable;
import cn.wmp.model.CodeToSessionModel;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

/**
 * <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html>登录凭证校验。</a>
 * 通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程。
 * 更多使用方法详见 <a href="https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/login.html">小程序登录。</a>
 *
 * @author Yujiumin
 * @version 2022/04/19
 */
public class Code2SessionRequest implements Executable {

    private CodeToSessionModel codeToSessionModel;

    private HttpGet httpGet;

    private static final String URI_PATH = "https://api.weixin.qq.com/sns/jscode2session";

    public Code2SessionRequest(CodeToSessionModel codeToSessionModel) throws URISyntaxException {
        this.codeToSessionModel = codeToSessionModel;
        init();
    }

    private void init() throws URISyntaxException {
        final URI uri = new URIBuilder(URI_PATH, StandardCharsets.UTF_8)
                .addParameter("appid", codeToSessionModel.getAppId())
                .addParameter("secret", codeToSessionModel.getSecret())
                .addParameter("js_code", codeToSessionModel.getJsCode())
                .addParameter("grant_type", codeToSessionModel.getGrantType())
                .build();
        this.httpGet = new HttpGet(uri);
    }

    @Override
    public HttpUriRequest getRequest() {
        return httpGet;
    }
}
