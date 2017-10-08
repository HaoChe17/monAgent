package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ִ��Linux-shell���������ִ�н��
 * @author chehao
 * @version 2017��9��24�� ����11:28:36
 */
public class ExecShellCmd {
	
	public String exec(String shellCmd){
		String[] cmd=new String[]{"/bin/sh","-c",shellCmd};
		Process ps=null;
		try {
			ps=Runtime.getRuntime().exec(cmd);
			Log.writeLog("debug", "execute the shell command\""+shellCmd+"\"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.writeLog("error", e.getMessage());
			e.printStackTrace();
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream())); 
		StringBuffer sb = new StringBuffer();
		String line;
		try {
			while((line=br.readLine())!=null){
				sb.append(line).append("\n");
			}
			Log.writeLog("info", "execute command \""+shellCmd+"\" success!");
			Log.writeLog("debug", "the executing result of \""+shellCmd+"\":"+sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.writeLog("error", "execute command \""+shellCmd+"\" failed!");
			Log.writeLog("error", e.getMessage());
			e.printStackTrace();
		}
		
		return sb.toString();
	} 
}
