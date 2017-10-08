package test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import monitor.GetAllInfo;
import monitor.GetAppInfo;
import monitor.GetJvmInfo;
import rmi.AchieveMonitorInfo;
import rmi.MonitorInterface;
import utils.ExecShellCmd;
import utils.GetSpecialDelimiterStr;
import utils.Log;

public class testMain {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		// TODO Auto-generated method stub
//		Log.writeLog("info", "start the agent……");
//		System.out.println(GetAllInfo.getAllInfo("/usr/java/jdk1.7.0_79", "pinpoint", "jvm"));
//		GetJvmInfo.getJvmInfo("/usr/java/jdk1.7.0_79", "tomcat_pinpoint_web");
		//System.out.println(ExecShellCmd.exec("ps -ef|grep java"));
//		GetAllInfo.getAllInfo("/usr/java/jdk1.8.0_65","tomcat_pinpoint_collector","jvm");
		
		//rmi测试
		MonitorInterface mi=new AchieveMonitorInfo();
		try {
			LocateRegistry.createRegistry(12121);
			Log.writeLog("info", "rmi binds the port 12121 success！");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Naming.rebind("rmi://172.16.31.77:12121/mi", mi);
//		System.out.println("23s".length());
		
		
//		String gcInfo="ASE DFSD DFSD   SDFSD	SDFSD   WE4SD 	SDFSD  SDFS SDF   SDF 	  	SDF\n123 343 13  2342	233	 233		 234 - 234 	RT -";
//		String[] gc_array=gcInfo.split("\n");
//		String[] gcKey_array=null;
//		String[] gcValue_array=null;
//		String gcKeyStr=null;
//		String gcValueStr=null;
//		//以每行的前三个字符来判断是否为我们需要的GC信息
//		Pattern pattern=Pattern.compile("^\\d{3}");
//		for(String str:gc_array){
//			String prefix=str.substring(0,3);
//			if(prefix.equals("ASE")){
//				gcKeyStr=GetSpecialDelimiterStr.getSpecialStr(str, "\t");
//				gcKey_array=gcKeyStr.split("\t");
//				continue;
//			}else if(pattern.matcher(prefix).matches()){
//				gcValueStr=GetSpecialDelimiterStr.getSpecialStr(str, "\t");
//				gcValue_array=gcValueStr.split("\t");
//				continue;
//			}
//		}
//		if(gcKey_array.length != gcValue_array.length){
//			Log.writeLog("error", "get gcInfo is wrong! gcKeyInfo=["+gcKeyStr+"],gcValueStr="+gcValueStr);
//		}
//		
//		Map<String,String> gcInfoMap=new HashMap<String,String>();
//		for(int i=0;i<gcKey_array.length;i++){
//			gcInfoMap.put(gcKey_array[i], gcValue_array[i]);
//		}
//		
//		for(int i=0;i<gcKey_array.length;i++){
//			System.out.println(gcKey_array[i]+":"+gcInfoMap.get(gcKey_array[i]));;
//		}
	}
}
