package cn.wmp.http;

import org.apache.http.client.methods.HttpUriRequest;

/**
 * @author Yujiumin
 * @version 2022/04/19
 */
public interface Executable {

    /**
     * 获取请求对象
     *
     * @return
     */
    HttpUriRequest getRequest();
}
