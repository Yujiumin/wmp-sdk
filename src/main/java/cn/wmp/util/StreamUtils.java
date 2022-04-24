package cn.wmp.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * 流处理
 *
 * @author Yujiumin
 * @version 2022/04/24
 */
public class StreamUtils {

    /**
     * 将流内容拷贝为字符串
     *
     * @param inputStream
     * @param charset
     * @return
     * @throws IOException
     */
    public static String copyToString(InputStream inputStream, Charset charset) throws IOException {
        if (inputStream == null) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder(4096);
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset)) {
            char[] buffer = new char[4096];
            int charsRead;
            while ((charsRead = inputStreamReader.read(buffer)) != -1) {
                stringBuilder.append(buffer, 0, charsRead);
            }
        }
        return stringBuilder.toString();
    }

}
