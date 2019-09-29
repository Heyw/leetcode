package leetcode;

import java.util.HashMap;

/**
 * 给定一串字符串，求出该字符串最长不包含重复字符的子字符串
 * @author Administrator
 *
 */
public class LongestSubStr_3 {
	
	/**
	 * 滑动窗口解法，选择start和end，不断左移end,使得[start,end]之类的字符串保持不重复，
	 * 如果end指向的字符已经出现过，那么start为上一次出现该字符的位置和当前start位置的较大值；
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
      
      //该方法核心就是利用两个指针i和j，其中i指向当前字符所在位置，j指向上次重复字符的后一个位置，这样确保i和j之间没有任何重复字符
      //每次循环i向前移动一位，就判断下当前字符是否重复出现过，如果重复出现过，接着判断上次重复的位置是否大于该字符串重复的位置
      //如果大于，则取上次重复位置的后一位开始计算长度，否则就从该字符串上次出现的位置的下一位计算长度
      //接着判断该长度是否大于result，如果大于则替代
      //cache保存的是当前字符所在位置的后一个位置
    
      public int solution2(String s){
    	  int result = 0;
          int[] cache = new int[256];
          for (int i = 0, j = 0; i < s.length(); i++) {
        	  //如果该字符存在重复字符，那么比较该字符的之前出现的后一位置和上次出现重复字符的后一位置大小，取大值为当前j，确保i，j之间最大无重复字符长度
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
