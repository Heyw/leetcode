package leetcode;
/**
 * 该类是用来将字符串转换成整型的，需要注意以下几点：
 * 1.字符串前面的空格要去掉，
 * 2.符号位要正确获取
 * 3.除了数字外的字符,则返回该字符前的整型值
 * 4.超过整数最大值返回最大值，负数则返回最小整型值
 * @author Administrator
 *
 */
public class StringToInteger {
     public int myAtoi(String s){
    	 int result=0,i=0,sign=0;
    	 if(s.length()==0) return result;
    	 while(s.charAt(i)==' '&&i<s.length()){
    		 i++;
    	 }
    	 if(s.charAt(i)=='+'||s.charAt(i)=='-'){
    		 sign=(s.charAt(i)=='+')?1:-1;
    		 i++;
    	 }
    	 while(i<s.length()){
    		 int value=s.charAt(i++)-'0';
    		 if(value<0||value>9)
    			 break;
    		 if(Integer.MAX_VALUE/10<result||Integer.MAX_VALUE-10*result<value)
    			 return sign==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
    	       result=result*10+value; 
    	      
    	 }
    	 return result;
     }
     public static void main(String[] args) {
		System.out.println(new StringToInteger().myAtoi("-1"));
	}
}
