package leetcode;

import java.util.HashMap;

/**
 * ����һ���ַ�����������ַ�����������ظ��ַ������ַ���
 * @author Administrator
 *
 */
public class LongestSubStr_3 {
	
	/**
	 * �������ڽⷨ��ѡ��start��end����������end,ʹ��[start,end]֮����ַ������ֲ��ظ���
	 * ���endָ����ַ��Ѿ����ֹ�����ôstartΪ��һ�γ��ָ��ַ���λ�ú͵�ǰstartλ�õĽϴ�ֵ��
	 * @param s
	 * @return
	 */
	 public int lengthOfLongestSubstring(String s) {
	        HashMap<Character,Integer> charMap=new HashMap<>();
	        char[] chars=s.toCharArray();
	        int start=0,end=0,length=chars.length;
	        int max=0;
	        while(start<length&&end<length){
	            
	            
	            if(charMap.containsKey(chars[end])){
	            	
	                start=Math.max(charMap.get(chars[end])+1, start);
	            }
	            max=Math.max(max,end-start+1);
	            charMap.put(chars[end],end++);
	            
	        }
	    	  
	    	 return max;
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
		String str="bcdefgahijklmnadef";//"pwwkew";//"bcdefgahijklmnadef";
		System.out.println(new LongestSubStr_3().lengthOfLongestSubstring(str));
		}
}
