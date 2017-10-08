package monitor;

import utils.ConfigData;
import utils.ExecShellCmd;
import utils.GetSpecialDelimiterStr;
import utils.Log;

public class GetAppInfo {
	public static String pidstatCmd=ConfigData.pidstatCmd;
	private static String delimiter=ConfigData.delimiter;
	/**
	 * ����pidstat�����ȡӦ�ñ������Ϣ
	 * @param pid
	 * @return
	 */
	public String getAppInfo(String pid){
		String appInfoJson="";
		String appInfo=new ExecShellCmd().exec(pidstatCmd+pid);
		Log.writeLog("debug", "the pidstatCmd of "+pid+" is:"+pidstatCmd+pid);
		Log.writeLog("debug", "the pidstatCmd's executing result is:  "+appInfo);
		
		//��pidstat����Ľ�������ݻ��з��ָ������
		String[] appInfo_array=appInfo.split(ConfigData.newLineSeparator);
		String appInfoKey="";
		String appInfoValue="";
		//�ӽ�������У�ɸѡ����Ҫ�Ľ��
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
		
		//�ѽ����װ��json��ʽ���ַ���
		for(int i=0;i<appInfoKey_array.length;i++){
			appInfoJson=appInfoJson+'"'+appInfoKey_array[i]+"\":"+'"'+appInfoValue_array[i]+'"';
			if(i!=appInfoKey_array.length-1)appInfoJson+=",";
		}
		return "{"+appInfoJson+"}";
	}
}
