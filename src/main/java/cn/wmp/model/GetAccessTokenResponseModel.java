package cn.wmp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Yujiumin
 * @version 2022/04/24
 */
@Getter
@Setter
@ToString
public class GetAccessTokenResponseModel {

    @JsonProperty("errcode")
    private String errorCode;

    @JsonProperty("errmsg")
    private String errorMessage;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Long expiresIn;

}
