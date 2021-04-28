package com.chanjet.APITests;

import com.alibaba.fastjson.JSONObject;
import com.chanjet.BaseActions.APIAction;
import com.chanjet.BaseActions.APIAction;
import Utils.ApiUtils;
import Utils.DateUtils;
import Utils.LogUtils;
import Utils.RestClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By LaoYu
 * 2021/2/4
 * 销货单保存
 */
public class SalesInvoice extends APIAction {

    LogUtils Log = new LogUtils(SalesInvoice.class);
    RestClient restClient;
    String host;
    String URL; //getTxnToken
    String saleURL;//getTxnToken
    String path;
    String saleData;
    APIAction apiAction;
    CloseableHttpResponse closeableHttpResponse;
    Map<String, String> header = null;
    Map<String,String> relation = null;

    @BeforeClass
    public void setup(){
        apiAction = new APIAction();
        host = properties.getProperty("testHost");
        path = properties.getProperty("saleTokenPath");
        saleURL = properties.getProperty("testHost") + properties.getProperty("salePath");
        saleData = properties.getProperty("data");
        URL = host + path;
        header= getheader();
        relation = new HashMap<>();
    }

    /**
     * 销货单初始化获取txnToken 和 code
     */
    @Test(priority = 0)
    public void HSY_TestEnv_Sales_SalsInvoice_GetTxnToken(){
        try {
            closeableHttpResponse = RestClient.get(URL,header);
            int code = closeableHttpResponse.getStatusLine().getStatusCode();
            Assert.assertEquals(code, RESPONSE_STATUS_CODE_200, "response status code is not 200");
            String TxnTokenSalseResult = EntityUtils.toString(closeableHttpResponse.getEntity());
            Assert.assertEquals(ApiUtils.getKeyValue("\"isPriceTracking\":(.+?),",TxnTokenSalseResult),"true");

            //获取token and code
            String saleTokenValue = ApiUtils.getKeyValue("\"txnToken\":\"(.+?)\",",TxnTokenSalseResult);
            String saleCodeValue = ApiUtils.getKeyValue("\"code\":\"(.+?)\",",TxnTokenSalseResult);

            //放入关联map
            relation.put("saleToken",saleTokenValue);
            relation.put("saleCode",saleCodeValue);

        }catch (Exception e){
            Log.error("获取销货单TxnToken失败");
            e.printStackTrace();
        }

    }

    @Test(priority = 1)
    public void HSY_TestEnv_Sales_SalsInvoice_Submit(){
        try {
            //动态获取参数传输
            saleData = ApiUtils.replaceValue(JSONObject.parseObject(saleData),"txnToken",relation.get("saleToken"));
            saleData = ApiUtils.replaceValue(JSONObject.parseObject(saleData),"code",relation.get("saleCode"));
            saleData = ApiUtils.replaceValue(JSONObject.parseObject(saleData),"bizDate", DateUtils.getCurrentDate()+"");
            closeableHttpResponse = RestClient.post(saleURL,saleData,header);
            Assert.assertEquals(closeableHttpResponse.getStatusLine().getStatusCode(),"200");
        }catch (Exception e){
            Log.error("销货单保存失败!!!");
            e.printStackTrace();
        }
    }

    public Map<String,String> getheader(){
        Map<String,String> map=new HashMap<String,String>();
        String ciahost = properties.getProperty("ciahost");
        try{
            String authorization = EntityUtils.toString(RestClient.get(ciahost,map).getEntity());
            map.put("Authorization", authorization);
            map.put("content-type","application/json; charset=utf-8");
            map.put("Connection","keep-alive");
            map.put("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

}
