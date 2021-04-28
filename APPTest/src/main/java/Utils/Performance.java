package Utils;

import com.chanjet.BaseActions.AppAction;
import com.chanjet.BaseActions.WebAction;
import com.chanjet.TPOSPages.TPOSBasePage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Performance {

	//adb shell dumpsys meminfo com.chanjet.goodbusiness | grep "TOTAL" | awk "{print $2}"

	public static String getMem(String message){
		System.out.println(message);
		String line = "";
		String result = "";
		try {
			Process process=Runtime.getRuntime().exec("/Users/yuxin/Downloads/tools/sdk/platform-tools/adb shell dumpsys meminfo com.chanjet.goodbusiness | grep \"TOTAL\"");
			InputStreamReader isr=new InputStreamReader(process.getInputStream());
			LineNumberReader input=new LineNumberReader(isr);
			List<String> list = new ArrayList<String>();
			while((line=input.readLine())!=null){
			    list.add(line);
			}
			 String total = (String) list.get(0).trim();

             String[] memall = total.split("TOTAL");
	         String memlist = memall[1].trim();
	         String num = memlist.replaceAll(" ", ",");

	          result = num.split(",")[0];
	          System.out.println("当前内存" + result+"KB");

	 } catch (IOException e) {

	        // TODO Auto-generated catch block

	        e.printStackTrace();

	 }
		System.out.println("结束获取内存");
		return result.trim();
	}

	public static void getErrorLog(){
		System.out.println("开始保存日志");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String date = df.format(new Date());
		RandomAccessFile random = null;
		try {
			random = new RandomAccessFile("/Users/yuxin/Downloads/work/app_data/log"+date+".txt","rw");
        	Process process=Runtime.getRuntime().exec("/Users/yuxin/Downloads/tools/sdk/platform-tools/adb logcat *:E");
        	InputStreamReader isr=new InputStreamReader(process.getInputStream());
        	LineNumberReader input=new LineNumberReader(isr);
        	String line;
        	while((line=input.readLine())!=null){
        		random.writeBytes(line+"/n");
        	}
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("结束保存日志");
	}

	public static void getCrashLogs(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String date = df.format(new Date());
		RandomAccessFile logRanm = null;
		try {
			logRanm = new RandomAccessFile("/Users/yuxin/Downloads/work/app_data/savelog"+date+".txt","rw");
			Process process = Runtime.getRuntime().exec("/Users/yuxin/Downloads/tools/sdk/platform-tools/adb shell ps | grep com.chanjet.goodbusiness");
			InputStreamReader isr = new InputStreamReader(process.getInputStream());
			LineNumberReader input = new LineNumberReader(isr);
			List<String> list = new ArrayList<String>();
			String lines = "";
			while((lines=input.readLine())!=null){
			    list.add(lines);
			}
			String pids = list.toString().replaceAll(" ", ",");
			String[] pidArr = pids.split(",");
			System.out.println(pidArr[4]);
			Process logprocess=Runtime.getRuntime().exec("/Users/yuxin/Downloads/tools/sdk/platform-tools/adb  logcat -v process | grep "+pidArr[4].trim());
			InputStreamReader logisr=new InputStreamReader(logprocess.getInputStream());
			LineNumberReader loginput=new LineNumberReader(logisr);

			String loglines = "";
			while((loglines=loginput.readLine())!=null){

				logRanm.writeBytes(loglines+"\n");

			}
			System.out.println(list.toString());

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static String getCpuinfo(String packagename){
		String result = "";
		try {
			System.out.println("获取cpu");
			Process process=Runtime.getRuntime().exec("/Users/yuxin/Downloads/tools/sdk/platform-tools/adb shell top -n 1 | grep "+packagename);
			InputStreamReader cpuisr = new InputStreamReader(process.getInputStream());
			LineNumberReader cpuinput = new LineNumberReader(cpuisr);
			List<String> list = new ArrayList<String>();
			String lines = "";
			while((lines=cpuinput.readLine())!=null){
			    list.add(lines);
			}
			String cpuInfo = list.toString().replaceAll(" ", ",");
			String[] cpuAll = cpuInfo.split(",");
			result = cpuAll[4].trim();
			System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public static long getTime(){
		return System.currentTimeMillis();
	}

	public static long getRT(AppAction action,String pageName,String elementName, long BeforeTime){
		boolean flag = true;
		while(flag){
			if(action.isElementDisplayed(pageName,elementName, TPOSBasePage.xmlPath)){
				flag = false;
			}
		}
		long afterTime = Performance.getTime();
		long  time = afterTime - BeforeTime - 1500;
		return time;
	}

	public static long getWRT(WebAction action, String locator, String pagename,String filename, long BeforeTime){
		boolean flag = true;
		while(flag){
			if(action.isElementDisplayed(locator,pagename,filename)){
				flag = false;
			}
		}
		long afterTime = Performance.getTime();
		long  time = afterTime - BeforeTime - 1500;
		return time;
	}

}
