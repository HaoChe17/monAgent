package utils;

/**
 * 配置的设置
 * @author chehao
 * @version 2017年10月8日 上午8:35:14
 */
public class ConfigData {
	
	//全局配置
	public static String delimiter="\t";//处理获取的监控数据时，需要替换成的分隔符
	public static String newLineSeparator=System.getProperty("line.separator");//系统换行符
	
	//获取JVM信息
	public static String jstatBin="/bin/jstat -gc ";//执行的jstat命令
	public static String jvmMonitorType="jvm";//判断需要获取的监控数据是否为jvm
	public static String gcValueregular="^\\d{3}";//判断是否为GC数据行的正则表达式。此行的前三个字符否均为数字
	public static String gcKeyKeyWords="S0C";//判断是否为GC列名行的关键字。此行的第2到4个字符串为“S0C”
	
	//获取os信息
	public static String osMonitorType="os";//判断需要获取的监控数据是否为os
	public static String devCommand="iostat -dx -k";//iostat命令，用于获取磁盘IO信息
	public static String devKeyPrefix="Device";//判断是否为磁盘列名行的关键字。此行以“Device”字符串开头
	public static String memCommand="free -k";//free命令，用于获取系统内存信息
	public static String memKeyKeyWords="total";//判断是否为内存数据（free命令）的列名行。此行以包含“total”关键字
	public static String memMemValueKeyWords="Mem";//判断是否为内存数据（free命令）的Mem数据行。此行以包含Mem关键字
	public static String memBcValueKeyWords="buffers/cache";//判断是否为内存数据（free命令）的“buffers/cache”数据行。此行以包含“buffers/cache”关键字
	public static String memSwapValueKeyWords="Swap";//判断是否为内存数据（free命令）的“Swap”数据行。此行以包含“Swap”关键字
	
	//获取application信息
	public static String appMonitorType="app";//判断需要获取的监控数据是否为app
	public static String pidstatCmd="pidstat -udr -h -p ";//pidstat命令
	public static String appKeyLinePrefix="#";//用于判断pidstat的输出中，是否为列名所在行，此行以“#”开头
	public static String appValueLinePrefix="150";//用于判断pidstat的输出中，是否为数据所在行，此行以Unix时间戳开头
	
	/**读取配置数据
	 * 
	 */
	public static void initConfigData(){
		
	}
	
}
