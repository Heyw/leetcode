package leetcode;
/**
 * �������������ַ���ת�������͵ģ���Ҫע�����¼��㣺
 * 1.�ַ���ǰ��Ŀո�Ҫȥ����
 * 2.����λҪ��ȷ��ȡ
 * 3.������������ַ�,�򷵻ظ��ַ�ǰ������ֵ
 * 4.�����������ֵ�������ֵ�������򷵻���С����ֵ
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
