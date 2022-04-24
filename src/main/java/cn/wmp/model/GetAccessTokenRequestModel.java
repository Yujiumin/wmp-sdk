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
public class GetAccessTokenRequestModel {

    @JsonProperty("appid")
    private String appId;

    private String secret;

    @JsonProperty("grant_type")
    private String grantType;

}
