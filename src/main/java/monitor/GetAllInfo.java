package monitor;

import utils.ConfigData;
import utils.GetAppPid;
import utils.Log;

/**
 * ͨ�����࣬���Ի�ȡ���еļ����Ϣ
 * @author chehao
 * @version 2017��10��3�� ����10:28:03
 */
public class GetAllInfo {
	
	/**
	 * ��ȡ���м����Ϣ������Ϊjson��������
	 * @param jdkPath
	 * @param appKeyWord
	 * @param infoType
	 * @return
	 */
	public String getAllInfo(String jdkPath,String appKeyWord,String infoType){
		String allJsonInfo="{";
		String appName=appKeyWord;
		if(jdkPath==null)Log.writeLog("error", "the parameter 'jdkPath' of '"+appName+"' is null!");
		if(appName==null)Log.writeLog("error", "the parameter 'appKeyWord' "+" is null!");
		if(infoType==null)Log.writeLog("info", "the parameter 'infoType' "+" is null! The default value is 'all'");
		
//		String pid="";
		String pid=GetAppPid.getAppPid(appKeyWord).replace("\n", "").replace("\n\r", "");
		Log.writeLog("debug", "the pid of "+appKeyWord+" is:"+pid);
		
		
		//��ȡ�����Ϣ��ʱ�䣺
		String currentTime=System.currentTimeMillis()+"";
		
		//��ȡJVM�����Ϣ��
		String jstatCmd=jdkPath+ConfigData.jstatBin;
		String jvmInfo=null;
		if(infoType.contains(ConfigData.jvmMonitorType)){
			jvmInfo=new GetJvmInfo().getJvmInfo(jstatCmd, pid);
		}
		
		//��ȡϵͳ��Ϣ��
		String osInfo=null;
		if(infoType.contains(ConfigData.osMonitorType)){
			osInfo=new GetOsInfo().getOsInfo();
		}
		
		
		//��ȡӦ��pidstat��Ϣ��
		String appStatInfo=null;
		if(infoType.contains(ConfigData.appMonitorType)){
			appStatInfo=new GetAppInfo().getAppInfo(pid);
		}
		
		
		//��װ��json���ݣ�
		allJsonInfo+="\"time\":"+currentTime+",\"monitorInfo\":{\"jvmInfo\":"+jvmInfo+",\"osInfo\":"+osInfo+",\"appStatInfo\":"+appStatInfo+"}}";
		Log.writeLog("debug", "allJsonInfo is : "+allJsonInfo);
		return allJsonInfo;
	}
}
