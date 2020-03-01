package puresport.mvc.t7crcl;

import org.apache.commons.lang3.StringUtils;

public class StringTools {
	public static void main(String[] args) {

		// 方法2
		String str = "liu jianguo";
		
		System.out.println(strConver(str));
	}
// 将字符串中单词首字母转换成大写	
	public static String strConver(String str) {
		if(StringUtils.isBlank(str))
			return "";
		String[] s = str.split(" ");
		// 以空格为分隔符进行分割
		// 用于接收转成大写的单词
		String str2 = new String();
		for (int i = 0; i < s.length; i++) {
			// toCharArray:将String类型字符串转换成字符数组
			char[] a = s[i].toCharArray();
			// 取出每个单词的首字母，-32，编程对应字母的大写值的ASCII码
			a[0] = (char) (a[0] - 32);
			// 使用String包装类valueOf(char[] data),将字符数组转换成字符串
			s[i] = String.valueOf(a);
			if (i == s.length - 1) {
				str2 = str2 + s[i];
			} else {
				str2 = str2 + s[i] + " ";
			}
		}
		System.out.println(str2);
		return str2;
	}
	//比较字符串是否相等，忽略空格，忽略大小写
	public static boolean isIgnoreEqueal(String str1, String str2) {
		if(StringUtils.isBlank(str1) || StringUtils.isBlank(str2)) {
			return false;
		}
		str1 = str1.replace(" ", "");
		str1 = str1.toUpperCase();
		str2 = str2.replace(" ", "");
		str2 = str2.toUpperCase();
		if(str1.equals(str2))
			return true;
		else
			return false;
	}
	

//	**
//    * 每个单词第一个字母大写
//    * @param str
//    * @return
//    */
   public static String toUpperFirstCode(String str) {
	   try {
	       String[] strs = str.split(" ");
	       StringBuilder sb = new StringBuilder();
	       for (String strTmp : strs) {
	           char[] ch = strTmp.toCharArray();
	           if (ch[0] >= 'a' && ch[0] <= 'z') {
	               ch[0] = (char) (ch[0] - 32);
	           }
	           String strT = new String(ch);
	           sb.append(strT).append(" ");
	       }
	       return sb.toString().trim();
	   } catch (Exception e) {
		   return str;
	   }
   }
}
