package cn.wmp.handler;

import cn.wmp.http.AbstractResponseHandler;
import cn.wmp.model.CheckEncryptedDataResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Yujiumin
 * @version 2022/04/24
 */
@Slf4j
public class CheckEncryptedDataResponseHandler extends AbstractResponseHandler<CheckEncryptedDataResponseModel> {

    private ObjectMapper objectMapper;

    public CheckEncryptedDataResponseHandler() {
        this.objectMapper = new ObjectMapper();
    }

    public CheckEncryptedDataResponseHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public CheckEncryptedDataResponseModel handleResponse(String responseBody) {
        try {
            log.info("responseBody -> {}", responseBody);
            final CheckEncryptedDataResponseModel checkEncryptedDataResponseModel = objectMapper.readValue(responseBody, CheckEncryptedDataResponseModel.class);
            log.info("responseModel -> {}", checkEncryptedDataResponseModel);
            return checkEncryptedDataResponseModel;
        } catch (JsonProcessingException ex) {
            log.info("checkEncryptedData响应参数转换异常", ex);
        }
        return null;
    }
}
