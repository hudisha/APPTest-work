package Utils;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Map;

/**
 * Created By LaoYu
 * 2021/2/2
 *  httpclient request method
 */
public class RestClient {

    final static Utils.LogUtils Log = new Utils.LogUtils(RestClient.class);

    public static CloseableHttpResponse get(String url, Map<String, String> map) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        if(map!=null||map.size()!=0){
            for(Map.Entry<String, String> m: map.entrySet()){
                httpGet.addHeader(m.getKey(), m.getValue());
            }
        }

        Log.info("开始发送GET请求");
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
        Log.info("开始接收响应内容");
        return closeableHttpResponse;
    }

    /**
     * post request
     * payLoad 传参数 body体中传参数
     *
     */
    public static CloseableHttpResponse post(String url,String entity,Map<String, String> map) throws ClientProtocolException, IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(entity));
        for(Map.Entry<String, String> m: map.entrySet()){
            httpPost.addHeader(m.getKey(), m.getValue());
        }
        Log.info("开始发送POST请求");
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
        closeableHttpResponse.getAllHeaders();
        Log.info("开始接收响应内容");
        return closeableHttpResponse;
    }

}
