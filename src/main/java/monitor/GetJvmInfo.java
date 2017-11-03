package monitor;

import java.util.regex.Pattern;

import utils.ConfigData;
import utils.ExecShellCmd;
import utils.GetSpecialDelimiterStr;
import utils.Log;
/**
 * 获取JVM的监控信息
 * @author chehao
 * @version 2017年9月27日 下午10:00:56
 */
public class GetJvmInfo {
	private  String jvmInfo="";
	private  String pid=null;
	private  String privateJstatCmd=null;
	private static String delimiter=ConfigData.delimiter;

	/**
	 * 获取JVM信息的唯一方法。获取所有的jvm信息，整理为json，并返回。
	 * @param jstatCmd
	 * @param appPid
	 * @return
	 */
	public String getJvmInfo(String jstatCmd,String appPid){
		privateJstatCmd=jstatCmd;
		pid=appPid;
		jvmInfo+=getGCInfo();
		return "{"+jvmInfo+"}";
	}

	/**
	 * 获取GC的监控数据
	 */
	private String getGCInfo(){
		String gcJsonInfo="";
		String gcCommand=privateJstatCmd+pid;
		Log.writeLog("debug", "the gcCommand of "+pid+" is:"+gcCommand);
		//获取jstat -gc命令的返回结果
		String gcInfo=new ExecShellCmd().exec(gcCommand);
		Log.writeLog("debug", "the gcInfo of "+pid+" is:"+gcInfo);

		//解析GC命令返回的结果，提取出满足要求的结果
		String[] gc_array=gcInfo.split(ConfigData.newLineSeparator);
		String[] gcKey_array=null;
		String[] gcValue_array=null;
		String gcKeyStr=null;
		String gcValueStr=null;
		Pattern pattern=Pattern.compile(ConfigData.gcValueregular);
		for(String str:gc_array){
			if(str.substring(1,4).equals(ConfigData.gcKeyKeyWords)){
				gcKeyStr=new GetSpecialDelimiterStr().getSpecialStr(str, delimiter);
				gcKey_array=gcKeyStr.split(delimiter);
				Log.writeLog("debug","gcKeyStr:"+gcKeyStr);
				continue;
			}else if(pattern.matcher(str.substring(0,3)).matches()){
				gcValueStr=new GetSpecialDelimiterStr().getSpecialStr(str, delimiter);
				gcValue_array=gcValueStr.split(delimiter);
				Log.writeLog("debug","gcValueStr:"+gcValueStr);
				continue;
			}
		}


		if(gcKey_array.length != gcValue_array.length){
			Log.writeLog("error", "get gcInfo is wrong! gcKeyInfo=["+gcKeyStr+"],gcValueStr="+gcValueStr);
		}
		//把结果组装成json格式的字符串
		for(int i=0;i<gcKey_array.length;i++){
			gcJsonInfo=gcJsonInfo+'"'+gcKey_array[i]+"\":"+'"'+gcValue_array[i]+'"';
			if(i!=gcKey_array.length-1)gcJsonInfo+=",";
		}
		Log.writeLog("debug","gcJsonInfo:{"+gcJsonInfo+"}");
		return gcJsonInfo;

	}
}
