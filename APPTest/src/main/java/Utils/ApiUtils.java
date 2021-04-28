package Utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import business.Relation;
import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created By LaoYu
 * API的依赖处理
 * 2021/2/4
 */
public class ApiUtils {
    private final static Utils.LogUtils Log = new Utils.LogUtils(ApiUtils.class);

    public static int getStatusCode(CloseableHttpResponse closeableHttpResponse) {
        Log.info("开始获取状态码");
        int status_code = closeableHttpResponse.getStatusLine().getStatusCode();
        return status_code;
    }

    /**
     * 获取json key的value值 通用方法抽取
     */
    public static Object getJsonByKey(List<Object> obj, String key) {
        Log.info("开始获取"+key+"的value值");
        List<Object> list = new ArrayList<Object>();
        Object v;
        JSONArray jsonArray;
        JSONObject jsonObject;
        for (Object object : obj) {
            if (object instanceof JSONArray) {
                jsonArray = (JSONArray) (object);
                for (int i = 0; i < jsonArray.size(); i++) {
                    if (jsonArray.get(i) instanceof JSONObject) {
                        list.add(jsonArray.get(i));
                    }
                }
            }

            if (object instanceof JSONObject) {
                jsonObject = (JSONObject) object;
                for (String eString : jsonObject.keySet()) {
                    v = jsonObject.get(eString);
                    if (Objects.equals(key, eString)) {
                        return v;
                    }
                    if (v instanceof JSONObject | v instanceof JSONArray) {
                        list.add(v);
                    }
                }
            }
            if (list.isEmpty()) {
                return null;
            }
        }
        return getJsonByKey(list, key);
    }

    public static List<Object> FormatList(CloseableHttpResponse closeableHttpResponse)
            throws ParseException, IOException {
        Log.info("开始处理JSON数据，添加到List");
        List<Object> list = new ArrayList<>();
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity());
        JSONObject jsonObject = JSONObject.parseObject(responseString);
        list.add(jsonObject);
        return list;
    }

    /**
     * 获取某个字段的value值，默认第一个
     * @param regex
     * @param parse
     * @return
     */

    public static String getKeyValue(String regex,String parse){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(parse);
        String resultString = "";
        while (matcher.find()) {
            resultString = matcher.group(1);
            break;
        }
        return resultString;
    }
    /**
     * 获取任意一个值，传入你要获取的第几个数字即可
     * @param regex
     * @param parse
     * @param count
     * @return
     * (.+?)
     */
    public static String getKeyValues(String regex,String parse,int count){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(parse);
        List<String> list = new ArrayList<>();
        while(matcher.find()){
            list.add(matcher.group(1));
        }

        if(list.size()<count){
            return "";
        }else {
            return list.get(count-1);
        }

    }



    /**
     * 从excel获取参数化数据
     * @throws IOException
     */
    public static Object[][] dataProvier(String path,int sheetId) throws IOException{
        File file = new File(path);
        Log.info("开始读取参数化文件");
        FileInputStream fls = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fls);
        XSSFSheet sheet = wb.getSheetAt(0);
        int numberrow = sheet.getPhysicalNumberOfRows();
        int numbercell = sheet.getRow(0).getPhysicalNumberOfCells();
        Object[][] objects = new Object[numberrow][numbercell];
        for(int i=0;i<numberrow;i++){
            if(null==sheet.getRow(i)||"".equals(sheet.getRow(i))){
                continue;
            }
            for(int j=0;j<numbercell;j++){
                if (null==sheet.getRow(i).getCell(j)||"".equals(sheet.getRow(i).getCell(j))) {
                    continue;
                }
                XSSFCell cell = sheet.getRow(i).getCell(j);
                cell.setCellType(CellType.STRING);
                objects[i][j] = cell.getStringCellValue();
            }
        }

        return objects;
    }

    public Object getHeaders(CloseableHttpResponse closeableHttpResponse,String key){
        Header[] headers = closeableHttpResponse.getAllHeaders();
        Relation relation = new Relation();
        if(headers.length==0||headers.equals("")){
            return null;
        }
        for(Header header : headers){
            if(header.getName().equals(key)){
                relation.sMap(key, header.getValue());
            }
        }
        return relation;
    }

    public static String replaceValue(JSONObject obj,String relationKey, String relationValue) {
        Set<Entry<String, Object>> keys = obj.entrySet();
        keys.forEach(key -> {
            Object value = obj.get(key.getKey());
            if (value instanceof JSONObject) { //如果还是JSONObject，继续递归遍历
                replaceValue((JSONObject) value,relationKey,relationValue);
            } else if (value instanceof String && key.getKey() == relationKey) {//如果是String（这里没有处理其他类型，如int，double等），表示为具体的value值
                obj.put(key.getKey(), relationValue);
            }
        });
        return obj.toJSONString();
    }
}
