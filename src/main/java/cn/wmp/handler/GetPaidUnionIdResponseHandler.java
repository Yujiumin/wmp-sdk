package cn.wmp.handler;

import cn.wmp.http.AbstractResponseHandler;
import cn.wmp.model.GetPaidUnionIdResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Yujiumin
 * @version 2022/04/24
 */
@Slf4j
public class GetPaidUnionIdResponseHandler extends AbstractResponseHandler<GetPaidUnionIdResponseModel> {

    private ObjectMapper objectMapper;

    public GetPaidUnionIdResponseHandler() {
        this(new ObjectMapper());
    }

    public GetPaidUnionIdResponseHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public GetPaidUnionIdResponseModel handleResponse(String responseBody) {
        try {
            log.info("responseBody -> {}", responseBody);
            final GetPaidUnionIdResponseModel getPaidUnionIdResponseModel = objectMapper.readValue(responseBody, GetPaidUnionIdResponseModel.class);
            log.info("responseModel -> {}", getPaidUnionIdResponseModel);
            return getPaidUnionIdResponseModel;
        } catch (JsonProcessingException ex) {
            if (log.isErrorEnabled()) {
                log.error("getPaidUnionId响应参数转换失败", ex);
            }
        }
        return null;
    }
}
