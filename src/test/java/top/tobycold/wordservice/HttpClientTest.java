package top.tobycold.wordservice;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;


@SpringBootTest
public class HttpClientTest {
    @Test
    public void test() throws IOException {
        CloseableHttpClient aDefault = HttpClients.createDefault();
        String appid = "wxf81f1b4cfdf56e3d";
        String secret = "169ce16a035d00dc12a2162e8a45a31b";
        String grant_type = "anthorization_code";

        //变量
        String js_code = "0f1aD1Ga1a8vPG0FBjFa1aryxm2aD1GS";
        HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/sns/jscode2session?appid="
                + appid
                + "&secret=" + secret
                + "&js_code=" + js_code
                + "&grant_type=" + grant_type);

        CloseableHttpResponse response = aDefault.execute(httpGet);
        String string = EntityUtils.toString(response.getEntity());
        System.out.println(string);
    }
}
