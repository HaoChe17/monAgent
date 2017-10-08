package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import monitor.GetAllInfo;

public class AchieveMonitorInfo extends UnicastRemoteObject implements MonitorInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AchieveMonitorInfo() throws RemoteException {  
        super();  
    }
	
	@Override
	public String getMonitorInfo(String jdkPath,String appKeyWord,String infoType) throws RemoteException {
		// TODO Auto-generated method stub
		return new GetAllInfo().getAllInfo(jdkPath, appKeyWord, infoType);
	}

}
