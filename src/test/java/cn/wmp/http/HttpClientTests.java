package cn.wmp.http;

import cn.wmp.handler.CheckEncryptedDataResponseHandler;
import cn.wmp.handler.Code2SessionResponseHandler;
import cn.wmp.model.CheckEncryptedDataRequestModel;
import cn.wmp.model.Code2SessionRequestModel;
import cn.wmp.model.Code2SessionResponseModel;
import cn.wmp.request.CheckEncryptedDataRequest;
import cn.wmp.request.Code2SessionRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

/**
 * @author Yujiumin
 * @version 2022/04/19
 */
@RunWith(JUnit4.class)
public class HttpClientTests {

    private HttpClient httpClient;

    @Before
    public void before() {
        httpClient = HttpClient.getInstance();
    }

    @Test
    public void testCode2Session() throws Exception {
        Code2SessionRequestModel code2SessionRequestModel = Code2SessionRequestModel.builder()
                .appId("")
                .secret("")
                .jsCode("")
                .grantType("authorization_code")
                .build();
        Code2SessionRequest code2SessionRequest = new Code2SessionRequest(code2SessionRequestModel);
        httpClient.execute(code2SessionRequest, new Code2SessionResponseHandler());
    }

    @Test
    public void testCheckEncryptedDataRequest() throws IOException {
        final CheckEncryptedDataRequestModel checkEncryptedDataRequestModel = CheckEncryptedDataRequestModel.builder()
                .accessToken("")
                .encryptedMessageHash("")
                .build();
        CheckEncryptedDataRequest checkEncryptedDataRequest = new CheckEncryptedDataRequest(checkEncryptedDataRequestModel);
        httpClient.execute(checkEncryptedDataRequest, new CheckEncryptedDataResponseHandler());
    }

}
