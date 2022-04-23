package cn.wmp.http;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import cn.wmp.model.CodeToSessionModel;
import cn.wmp.request.Code2SessionRequest;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author Yujiumin
 * @version 2022/04/19
 */
@RunWith(JUnit4.class)
public class HttpClientTests {

    @Test
    public void test() throws Exception {
        HttpClient httpClient = HttpClient.getInstance();
        Executable executable = () -> (HttpUriRequest) new HttpGet("http://localhost:8080/my/test");
        Map<String, Object> execute = httpClient.execute(executable, new AbstractResponseHandler<Map<String, Object>>() {
            @Override
            public Map<String, Object> handleResponse(String responseBody) {
                return JSONUtil.toBean(responseBody, new TypeReference<Map<String, Object>>() {
                }.getType(), false);
            }
        });
        System.out.println(execute);
    }

}
