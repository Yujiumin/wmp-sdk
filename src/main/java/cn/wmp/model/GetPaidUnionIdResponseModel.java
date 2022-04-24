package cn.wmp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author Yujiumin
 * @version 2022/04/24
 */
@Getter
@Setter
@ToString
public class GetPaidUnionIdResponseModel {

    @JsonProperty("errcode")
    private Integer errorCode;

    @JsonProperty("errmsg")
    private String errorMessage;

    @JsonProperty("unionid")
    private String unionId;
}
