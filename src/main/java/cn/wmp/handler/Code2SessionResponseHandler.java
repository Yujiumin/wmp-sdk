package cn.wmp.handler;

import cn.wmp.http.AbstractResponseHandler;
import cn.wmp.model.Code2SessionResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Yujiumin
 * @version 2022/04/24
 */
@Slf4j
public class Code2SessionResponseHandler extends AbstractResponseHandler<Code2SessionResponseModel> {

    private final ObjectMapper objectMapper;

    public Code2SessionResponseHandler() {
       this(new ObjectMapper());
    }

    public Code2SessionResponseHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Code2SessionResponseModel handleResponse(String responseBody) {
        try {
            final Code2SessionResponseModel code2SessionResponseModel = objectMapper.readValue(responseBody, Code2SessionResponseModel.class);
            log.info("code2SessionResponseModel -> {}", code2SessionResponseModel);
            return code2SessionResponseModel;
        } catch (JsonProcessingException ex) {
            if (log.isErrorEnabled()) {
                log.error("code2Session响应参数转换异常", ex);
            }
            return null;
        }
    }
}
