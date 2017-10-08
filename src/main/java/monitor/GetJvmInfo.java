package monitor;

import java.util.regex.Pattern;

import utils.ConfigData;
import utils.ExecShellCmd;
import utils.GetSpecialDelimiterStr;
import utils.Log;
/**
 * ��ȡJVM�ļ����Ϣ
 * @author chehao
 * @version 2017��9��27�� ����10:00:56
 */
public class GetJvmInfo {
	private  String jvmInfo="";
	private  String pid=null;
	private  String privateJstatCmd=null;
	private static String delimiter=ConfigData.delimiter;
	
	/**
	 * ��ȡJVM��Ϣ��Ψһ��������ȡ���е�jvm��Ϣ������Ϊjson�������ء�
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
	 * ��ȡGC�ļ������
	 */
	private String getGCInfo(){
		String gcJsonInfo="";
		String gcCommand=privateJstatCmd+pid;
		Log.writeLog("debug", "the gcCommand of "+pid+" is:"+gcCommand);
		//��ȡjstat -gc����ķ��ؽ��
		String gcInfo=new ExecShellCmd().exec(gcCommand);
		Log.writeLog("debug", "the gcInfo of "+pid+" is:"+gcInfo);

		//����GC����صĽ������ȡ������Ҫ��Ľ��
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
		//�ѽ����װ��json��ʽ���ַ���
		for(int i=0;i<gcKey_array.length;i++){
			gcJsonInfo=gcJsonInfo+'"'+gcKey_array[i]+"\":"+'"'+gcValue_array[i]+'"';
			if(i!=gcKey_array.length-1)gcJsonInfo+=",";
		}
		Log.writeLog("debug","gcJsonInfo:{"+gcJsonInfo+"}");
		return gcJsonInfo;
		
	}
}
