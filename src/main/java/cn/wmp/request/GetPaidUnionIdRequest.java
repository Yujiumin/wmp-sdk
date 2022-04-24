package cn.wmp.request;

import cn.wmp.http.Executable;
import cn.wmp.model.GetPaidUnionIdRequestModel;
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
public class GetPaidUnionIdRequest implements Executable {

    private static final String URI_PATH = "https://api.weixin.qq.com/wxa/getpaidunionid";

    @Getter
    private GetPaidUnionIdRequestModel getPaidUnionIdRequestModel;

    private HttpGet httpGet;

    public GetPaidUnionIdRequest(GetPaidUnionIdRequestModel getPaidUnionIdRequestModel) {
        this.getPaidUnionIdRequestModel = getPaidUnionIdRequestModel;
        try {
            URI uri = buildUri(getPaidUnionIdRequestModel);
            this.httpGet = new HttpGet(uri);
        } catch (URISyntaxException ex) {
            if (log.isErrorEnabled()) {
                log.error("URI构建失败", ex);
            }
        }
    }

    private URI buildUri(GetPaidUnionIdRequestModel getPaidUnionIdRequestModel) throws URISyntaxException {
        return new URIBuilder(URI_PATH, StandardCharsets.UTF_8)
                .addParameter("access_token", getPaidUnionIdRequestModel.getAccessToken())
                .addParameter("openid", getPaidUnionIdRequestModel.getOpenId())
                .addParameter("transaction_id", getPaidUnionIdRequestModel.getTransactionId())
                .addParameter("mch_id", getPaidUnionIdRequestModel.getMchId())
                .addParameter("out_trade_no", getPaidUnionIdRequestModel.getOutTradeNo())
                .build();
    }

    @Override
    public HttpUriRequest getRequest() {
        return httpGet;
    }
}
