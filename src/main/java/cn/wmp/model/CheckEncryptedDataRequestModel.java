package cn.wmp.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author Yujiumin
 * @version 2022/04/24
 */
@Data
@Builder
public class CheckEncryptedDataRequestModel {

    private String accessToken;

    private String encryptedMessageHash;

}
