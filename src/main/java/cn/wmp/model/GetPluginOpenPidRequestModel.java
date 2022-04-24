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
public class GetPluginOpenPidRequestModel {

    @JsonProperty("access_token")
    private String accessToken;

    private String code;

}
