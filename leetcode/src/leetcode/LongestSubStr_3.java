package leetcode;


/**
 * 给定一串字符串，求出该字符串最长不包含重复字符的子字符串
 * @author Administrator
 *
 */
public class LongestSubStr_3 {
	
	//该解法忽视了indexOf()本身的搜索复杂度
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
		String str="bcdefgahijklmnadef";
		new LongestSubStr().solution2(str);
		}
}
