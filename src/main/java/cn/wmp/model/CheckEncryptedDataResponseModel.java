package cn.wmp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
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
public class CheckEncryptedDataResponseModel {

    @JsonProperty("errcode")
    private String errorCode;

    @JsonProperty("errmsg")
    private String errorMessage;

    @JsonProperty("vaild")
    private Boolean valid;

    @JsonProperty("create_time")
    private Long createTime;


}
