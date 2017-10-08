package monitor;

import utils.ConfigData;
import utils.ExecShellCmd;
import utils.GetSpecialDelimiterStr;
import utils.Log;

public class GetAppInfo {
	public static String pidstatCmd=ConfigData.pidstatCmd;
	private static String delimiter=ConfigData.delimiter;
	/**
	 * 根据pidstat命令，获取应用本身的信息
	 * @param pid
	 * @return
	 */
	public String getAppInfo(String pid){
		String appInfoJson="";
		String appInfo=new ExecShellCmd().exec(pidstatCmd+pid);
		Log.writeLog("debug", "the pidstatCmd of "+pid+" is:"+pidstatCmd+pid);
		Log.writeLog("debug", "the pidstatCmd's executing result is:  "+appInfo);
		
		//把pidstat命令的结果，根据换行符分割成数组
		String[] appInfo_array=appInfo.split(ConfigData.newLineSeparator);
		String appInfoKey="";
		String appInfoValue="";
		//从结果数组中，筛选出需要的结果
		try{
			for(String str:appInfo_array){
				if(str.length()<5)continue;
				if(str.startsWith(ConfigData.appKeyLinePrefix)){
					appInfoKey=new GetSpecialDelimiterStr().getSpecialStr(str.replace("#", ""), delimiter);
					Log.writeLog("debug", "The appInfoKey of "+pid+" is: "+appInfoKey);
					continue;
				}else if(str.substring(0, 10).contains(ConfigData.appValueLinePrefix)){
					appInfoValue=new GetSpecialDelimiterStr().getSpecialStr(str, delimiter);
					Log.writeLog("debug", "The appInfoKey of "+pid+" is: "+appInfoValue);
				}
			}
		}catch(Exception e){
			Log.writeLog("error", e.getMessage());
		}
		
		
		String[] appInfoKey_array=appInfoKey.split(delimiter);
		String[] appInfoValue_array=appInfoValue.split(delimiter);
		if(appInfoKey_array.length != appInfoValue_array.length){
			Log.writeLog("error", "get gcInfo is wrong! gcKeyInfo=["+appInfoKey+"],gcValueStr="+appInfoValue);
		}
		
		//把结果组装成json格式的字符串
		for(int i=0;i<appInfoKey_array.length;i++){
			appInfoJson=appInfoJson+'"'+appInfoKey_array[i]+"\":"+'"'+appInfoValue_array[i]+'"';
			if(i!=appInfoKey_array.length-1)appInfoJson+=",";
		}
		return "{"+appInfoJson+"}";
	}
}
