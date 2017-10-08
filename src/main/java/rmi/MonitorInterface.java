package rmi;

import java.rmi.Remote;

public interface MonitorInterface extends Remote {
	public String getMonitorInfo(String jdkPath,String appKeyWord,String infoType) throws java.rmi.RemoteException;
}
