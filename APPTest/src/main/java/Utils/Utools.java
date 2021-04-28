package Utils;

import java.net.URL;

/**
 * @Created by LaoYu
 */
public class Utools {

    public static String changeDomain(String domain,String url){
        String nURL = "";
        try {
            URL cUrl = new URL(url);
            String path = cUrl.getPath();
            nURL = domain + path;
        }catch (Exception e){
            e.printStackTrace();
        }
        return nURL;
    }

}
