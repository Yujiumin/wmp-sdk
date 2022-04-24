package cn.wmp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author Yujiumin
 * @version 2022/04/24
 */
@Data
@Builder
public class GetPaidUnionIdRequestModel {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("openid")
    private String openId;

    @JsonProperty("transaction_id")
    private String transactionId;

    @JsonProperty("mch_id")
    private String mchId;

    @JsonProperty("out_trade_no")
    private String outTradeNo;
}
