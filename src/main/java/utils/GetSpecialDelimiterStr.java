package utils;
/**
 * ���ַ����е������Ŀո��tab��tab��ո����ϻ���ָ�����ַ�
 * @author chehao
 * @version 2017��9��28�� ����10:10:33
 */
public class GetSpecialDelimiterStr {
	public String getSpecialStr(String targetStr,String delimiter){
		char[] ch_array=targetStr.toCharArray();
		String newStr="";
		String tmpStr="";
		//��ȡ���ǿո���tab���ַ�������ָ���ķָ�������µ��ַ���
		for(char ar:ch_array){
			if(ar==" ".toCharArray()[0] || ar=="	".toCharArray()[0]){
				if(!tmpStr.equals("")){
					newStr=newStr+tmpStr+delimiter;
					tmpStr="";
				}
			}else{
				tmpStr=tmpStr+ar;
			}
		}
		newStr=newStr+tmpStr;
		return newStr;
	}
}
