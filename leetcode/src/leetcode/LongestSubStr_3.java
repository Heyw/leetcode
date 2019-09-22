package leetcode;


/**
 * ����һ���ַ�����������ַ�����������ظ��ַ������ַ���
 * @author Administrator
 *
 */
public class LongestSubStr_3 {
	
	//�ýⷨ������indexOf()������������Ӷ�
      public int solution1(String str){
    	  String substr=new String();
    	  String maxSubStr=new String();
    	  for(int i=0;i<str.length();i++){
    		  Character c=str.charAt(i);
    		  int j=0;
    		  if((j=substr.indexOf(c.toString()))>=0){
    			  substr=str.substring(i+j+1-substr.length(),i+1);
    		  }
    		  else
    		     substr+=str.charAt(i);
    		  if(substr.length()>=maxSubStr.length())
                 maxSubStr=substr;
    	  }
    	  System.out.println(maxSubStr);
    	 return maxSubStr.length();
      }
      
      //�÷������ľ�����������ָ��i��j������iָ��ǰ�ַ�����λ�ã�jָ���ϴ��ظ��ַ��ĺ�һ��λ�ã�����ȷ��i��j֮��û���κ��ظ��ַ�
      //ÿ��ѭ��i��ǰ�ƶ�һλ�����ж��µ�ǰ�ַ��Ƿ��ظ����ֹ�������ظ����ֹ��������ж��ϴ��ظ���λ���Ƿ���ڸ��ַ����ظ���λ��
      //������ڣ���ȡ�ϴ��ظ�λ�õĺ�һλ��ʼ���㳤�ȣ�����ʹӸ��ַ����ϴγ��ֵ�λ�õ���һλ���㳤��
      //�����жϸó����Ƿ����result��������������
      //cache������ǵ�ǰ�ַ�����λ�õĺ�һ��λ��
    
      public int solution2(String s){
    	  int result = 0;
          int[] cache = new int[256];
          for (int i = 0, j = 0; i < s.length(); i++) {
        	  //������ַ������ظ��ַ�����ô�Ƚϸ��ַ���֮ǰ���ֵĺ�һλ�ú��ϴγ����ظ��ַ��ĺ�һλ�ô�С��ȡ��ֵΪ��ǰj��ȷ��i��j֮��������ظ��ַ�����
              j = (cache[s.charAt(i)] > 0) ? Math.max(j, cache[s.charAt(i)]) : j;
              cache[s.charAt(i)] = i + 1;
              result = Math.max(result, i - j + 1);
          }
          return result;

      }
      public static void main(String[] args) {
		String str="bcdefgahijklmnadef";
		new LongestSubStr().solution2(str);
		}
}
