package utils;
/**
 * ��ȡ�쳣����ϸ��Ϣ
 * @author chehao
 * @version 2017��10��7�� ����11:12:01
 */
public class GetExceptionDetalInfo {
	public String getExceptionDetail(Exception e) {  
        StringBuffer stringBuffer = new StringBuffer(e.toString() + "\n");  
        StackTraceElement[] messages = e.getStackTrace();  
        int length = messages.length;  
        for (int i = 0; i < length; i++) {  
            stringBuffer.append("\t"+messages[i].toString()+"\n");  
        }  
        return stringBuffer.toString();  
    }
}
