/**
 *
 */
package business;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By LaoYu
 * 021/2/3
 */
public class Relation {

	Map<String, Object> map = new HashMap<String,Object>();

	public Map<String, Object> sMap(String key,Object obj){
		map.put(key, obj);
		return map;
	}

	/**
	 * 获取变量值，引用值
	 * 关联：查找，从上一个请求中返回的结果中，查找，左右边界，查找之后引用
	 */
	public Object gMap(String key){
		return map.get(key);
	}

}
