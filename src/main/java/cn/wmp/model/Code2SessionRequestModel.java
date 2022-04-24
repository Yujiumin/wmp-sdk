package cn.wmp.model;

import lombok.Builder;
import lombok.Data;

/**
 * code2Session接口请求参数
 *
 * @author Yujiumin
 * @version 2022/04/19
 */
@Data
@Builder
public class Code2SessionRequestModel {

    /**
     * 小程序 appId
     */
    private String appId;

    /**
     * 小程序 appSecret
     */
    private String secret;

    /**
     * 登录时获取的 code
     */
    private String jsCode;

    /**
     * 授权类型，此处只需填写 authorization_code
     */
    private String grantType;

}
