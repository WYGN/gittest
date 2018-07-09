package cn.sunline.clwj.icomm.integral.aplt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@SuppressWarnings("restriction")
public class JSONUtil {
		
	/**
	 * 读取json文件,转化为json对象
	 * @param jsonfile
	 * @return
	 */
	public static JSONObject toJSONObect(File jsonfile) {	
		InputStreamReader reader = null;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			reader = new InputStreamReader(new FileInputStream(jsonfile), "UTF-8");
			char[] buf = new char[1024];
			int n = 0;
			while ((n=reader.read(buf)) != -1) {
				stringBuffer.append(new String(buf,0,n));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (reader!=null) {
					reader.close();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return JSONObject.parseObject(stringBuffer.toString());
	}
	
	/**
	 * 根据key值,读取json文件中的jsonObject
	 * @param key
	 * @param jsonfile
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public static <T> T getJSONObject(String key,File jsonfile){
		JSONObject jsonObect = JSONUtil.toJSONObect(jsonfile);
		if (jsonObect.containsKey(key)) {
			Object object = jsonObect.get(key);
			if ( object instanceof JSONObject) {
				return (T)object;
			}
			if (object instanceof JSONArray) {
				return (T) object;
			}
		}
		return null;
	}	
}
