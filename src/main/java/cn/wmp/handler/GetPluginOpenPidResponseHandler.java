package cn.wmp.handler;

import cn.wmp.http.AbstractResponseHandler;
import cn.wmp.model.GetPluginOpenPidResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Yujiumin
 * @version 2022/04/24
 */
@Slf4j
public class GetPluginOpenPidResponseHandler extends AbstractResponseHandler<GetPluginOpenPidResponseModel> {

    private final ObjectMapper objectMapper;

    public GetPluginOpenPidResponseHandler() {
        this(new ObjectMapper());
    }

    public GetPluginOpenPidResponseHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public GetPluginOpenPidResponseModel handleResponse(String responseBody) {
        try {
            log.info("responseBody -> {}", responseBody);
            final GetPluginOpenPidResponseModel getPluginOpenPidResponseModel = objectMapper.readValue(responseBody, GetPluginOpenPidResponseModel.class);
            log.info("responseModel -> {}", getPluginOpenPidResponseModel);
            return getPluginOpenPidResponseModel;
        } catch (JsonProcessingException ex) {
            if (log.isErrorEnabled()) {
                log.error("getPluginOpenPid参数转换异常", ex);
            }
        }
        return null;
    }
}
