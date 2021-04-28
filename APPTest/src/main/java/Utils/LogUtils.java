package Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {
	
	private final Class<?> clazz;
	private Logger log;
	
	public LogUtils(Class<?> clazz){
		this.clazz = clazz;
		this.log = LogManager.getLogger(this.clazz);
	}
	
	public void info(String message){
		log.info(message);
	}
	
	public void debug(String message){
		log.debug(message);
	}

	public void error(String message){
		log.error(message);
	}
	
	public void trace(String message){
		log.trace(message);
	}
	
	public void warn(String message){
		log.warn(message);
	}
	
	public void fatal(String message){
		log.fatal(message);
	}
	
	public static void sleep(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
