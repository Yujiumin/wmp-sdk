package cn.wmp.http;

import cn.wmp.handler.*;
import cn.wmp.model.*;
import cn.wmp.request.*;
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

    @Test
    public void testGetPaidUnionIdRequest() throws IOException {
        final GetPaidUnionIdRequestModel getPaidUnionIdRequestModel = GetPaidUnionIdRequestModel.builder()
                .accessToken("")
                .openId("")
                .mchId("")
                .transactionId("")
                .outTradeNo("")
                .build();
        GetPaidUnionIdRequest getPaidUnionIdRequest = new GetPaidUnionIdRequest(getPaidUnionIdRequestModel);
        httpClient.execute(getPaidUnionIdRequest, new GetPaidUnionIdResponseHandler());
    }

    @Test
    public void testGetPluginOpenPidRequest() throws IOException {
        final GetPluginOpenPidRequestModel getPluginOpenPidRequestModel = GetPluginOpenPidRequestModel.builder()
                .accessToken("")
                .code("")
                .build();
        GetPluginOpenPidRequest getPluginOpenPidRequest = new GetPluginOpenPidRequest(getPluginOpenPidRequestModel);
        httpClient.execute(getPluginOpenPidRequest, new GetPluginOpenPidResponseHandler());
    }

    @Test
    public void testGetAccessTokenRequest() throws IOException {
        final GetAccessTokenRequestModel getAccessTokenRequestModel = GetAccessTokenRequestModel.builder()
                .appId("")
                .secret("")
                .grantType("client_credential")
                .build();
        GetAccessTokenRequest getAccessTokenRequest = new GetAccessTokenRequest(getAccessTokenRequestModel);
        httpClient.execute(getAccessTokenRequest, new GetAccessTokenResponseHandler());
    }
}
