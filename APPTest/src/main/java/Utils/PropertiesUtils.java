package Utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

    public static String GetTheENVProperties(){
        String envFilePath="";
        String filePath="testNow.properties";
        String env=getTheProperty("testNow.properties","currentEnv");
        switch (env){
            case "inte": envFilePath="TestENV\\inteEnv.properties";break;
            case "test": envFilePath="TestENV\\testEnv.properties";break;
            default: envFilePath="";
        }
        return envFilePath;
    }

    public static String getTheProperty(String filePath, String property){

        String propertiesFilePath=System.getProperty("user.dir")+"\\src\\test\\resources\\"+filePath;
        Properties envPO=GetProPerties(propertiesFilePath);

        return envPO.getProperty(property);
    }

    /**
     * 提供文件路径，读取Properties内容
     * @param FilePath 文件路径
     * @return
     */
    private static Properties GetProPerties(String FilePath){
        Properties po = new Properties();
        try {

            FileInputStream fileInputStream = new FileInputStream(FilePath);
            BufferedInputStream in = new BufferedInputStream(fileInputStream);
            try{
                po.load(in);
            }catch(IOException io){
                io.printStackTrace();
            }
        }catch (FileNotFoundException fileNotFoundException){
            System.out.println("文件未发现");

        }
        return po;
    }
}
