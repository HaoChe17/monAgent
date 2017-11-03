package utils;

/**
 * get the pid of the application
 * @author chehao
 * @version 2017年9月27日 下午8:58:47
 */
public class GetAppPid {
	public static String getAppPid(String appKeyWord){
		return new ExecShellCmd().exec("ps -ef|grep -v '00:00:00 grep'|grep "+appKeyWord+"|awk '{print $2}'");
	}
}
