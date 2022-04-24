package cn.wmp.http;

import cn.wmp.handler.Code2SessionResponseHandler;
import cn.wmp.model.Code2SessionRequestModel;
import cn.wmp.model.Code2SessionResponseModel;
import cn.wmp.request.Code2SessionRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author Yujiumin
 * @version 2022/04/19
 */
@RunWith(JUnit4.class)
public class HttpClientTests {

    @Test
    public void test() throws Exception {
        Code2SessionRequestModel code2SessionRequestModel = Code2SessionRequestModel.builder()
                .appId("")
                .secret("")
                .jsCode("")
                .grantType("authorization_code")
                .build();
        Code2SessionRequest code2SessionRequest = new Code2SessionRequest(code2SessionRequestModel);
        final HttpClient httpClient = HttpClient.getInstance();
        final Code2SessionResponseModel code2SessionResponseModel = httpClient.execute(code2SessionRequest, new Code2SessionResponseHandler());
        System.out.println(code2SessionResponseModel);
    }

}
