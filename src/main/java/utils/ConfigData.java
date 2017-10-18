package utils;

/**
 * ���õ�����
 * @author chehao
 * @version 2017��10��8�� ����8:35:14
 */
public class ConfigData {
	
	//ȫ������
	public static String delimiter="\t";//�����ȡ�ļ������ʱ����Ҫ�滻�ɵķָ���
	public static String newLineSeparator=System.getProperty("line.separator");//ϵͳ���з�
	
	//��ȡJVM��Ϣ
	public static String jstatBin="/bin/jstat -gc ";//ִ�е�jstat����
	public static String jvmMonitorType="jvm";//�ж���Ҫ��ȡ�ļ�������Ƿ�Ϊjvm
	public static String gcValueregular="^\\d{3}";//�ж��Ƿ�ΪGC�����е�������ʽ�����е�ǰ�����ַ����Ϊ����
	public static String gcKeyKeyWords="S0C";//�ж��Ƿ�ΪGC�����еĹؼ��֡����еĵ�2��4���ַ���Ϊ��S0C��
	
	//��ȡos��Ϣ
	public static String osMonitorType="os";//�ж���Ҫ��ȡ�ļ�������Ƿ�Ϊos
	public static String devCommand="iostat -dx -k -c 1 2";//iostat������ڻ�ȡ����IO��Ϣ
	public static String devKeyPrefix="Device";//�ж��Ƿ�Ϊ���������еĹؼ��֡������ԡ�Device���ַ�����ͷ
	public static String memCommand="free -k";//free������ڻ�ȡϵͳ�ڴ���Ϣ
	public static String memKeyKeyWords="total";//�ж��Ƿ�Ϊ�ڴ����ݣ�free����������С������԰�����total���ؼ���
	public static String memMemValueKeyWords="Mem";//�ж��Ƿ�Ϊ�ڴ����ݣ�free�����Mem�����С������԰���Mem�ؼ���
	public static String memBcValueKeyWords="buffers/cache";//�ж��Ƿ�Ϊ�ڴ����ݣ�free����ġ�buffers/cache�������С������԰�����buffers/cache���ؼ���
	public static String memSwapValueKeyWords="Swap";//�ж��Ƿ�Ϊ�ڴ����ݣ�free����ġ�Swap�������С������԰�����Swap���ؼ���
	public static String cpuCommand="mpstat -P ALL 1 1";//mpstat����
	public static String cpuKeyPrefixKeyWords="Average:";//cpu������ݵ�����average��ͷ
	public static String cpuKeyKeyWords="%iowait";//CPU�������ڵ��а����ˡ�%iowait���ַ���
	public static String diskSpaceCommand="df -h";//��ȡ���̿ռ��С������
	public static String diskSpaceKeyPrefixKeyWords="Filesystem";//���̿ռ��С���ݵ������ԡ�Filesystem���ַ�����ͷ 
	
	//��ȡapplication��Ϣ
	public static String appMonitorType="app";//�ж���Ҫ��ȡ�ļ�������Ƿ�Ϊapp
	public static String appPid;//����pid
	public static String pidstatCmdPrefix="pidstat -udr -h -p ";//pidstatǰ׺
	public static String pidstatCmdPrePost=" 1 1";//pidstat��׺
	public static String appKeyLinePrefix="#";//�����ж�pidstat������У��Ƿ�Ϊ���������У������ԡ�#����ͷ
	public static String appValueLinePrefix="150";//�����ж�pidstat������У��Ƿ�Ϊ���������У�������Unixʱ�����ͷ
	
	/**��ȡ��������
	 * 
	 */
	public static void initConfigData(){
		
	}
	
}
