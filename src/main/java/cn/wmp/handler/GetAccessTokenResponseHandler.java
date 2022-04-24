package cn.wmp.handler;

import cn.wmp.http.AbstractResponseHandler;
import cn.wmp.model.GetAccessTokenResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Yujiumin
 * @version 2022/04/24
 */
@Slf4j
public class GetAccessTokenResponseHandler extends AbstractResponseHandler<GetAccessTokenResponseModel> {

    private ObjectMapper objectMapper;

    public GetAccessTokenResponseHandler() {
        this(new ObjectMapper());
    }

    public GetAccessTokenResponseHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public GetAccessTokenResponseModel handleResponse(String responseBody) {
        try {
            log.info("responseBody -> {}", responseBody);
            final GetAccessTokenResponseModel getAccessTokenResponseModel = objectMapper.readValue(responseBody, GetAccessTokenResponseModel.class);
            log.info("responseModel -> {}", getAccessTokenResponseModel);
            return getAccessTokenResponseModel;
        } catch (JsonProcessingException ex) {
            if (log.isErrorEnabled()) {
                log.error("getAccessToken响应参数转换异常", ex);
            }
        }
        return null;
    }
}
