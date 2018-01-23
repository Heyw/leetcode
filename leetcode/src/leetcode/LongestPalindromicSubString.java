package leetcode;
/**
 * 给一个字符串，求出其最长回文子串
 * abdcdb,最长回文就是bdcdb
 * aacbaef,最长回文就是aa
 * 回文就是从中心划分可以完美对照
 * @author Administrator
 *
 */
public class LongestPalindromicSubString {
       public String    solution1(String s){
    	   int max=1;
    	   String sub=s.substring(0,1);
    	   for(int i=0;i<s.length();i++){
    		   int size=1;
    		   int j=i-1,k=i+1;
    		   for(;k<s.length();){
    			  if(j>=0&&s.charAt(j)==s.charAt(k)){
    				   size+=2;
    				   j--;
    				   k++;
    			   } else if(s.charAt(k-1)==s.charAt(k)&&s.charAt(i)==s.charAt(k)){
    				   size+=1;
    				   k++;
    			   }else{
    		           break;
    			   }
    		   }
    		   if(max<size||size==s.length()){
				   max=size;
				   //经过循环后，此时j所在位置的字符与k所在位置的字符肯定不是相同的，j+1所在位置是回文开始的位置
				   sub=s.substring(j+1,j+size+1);
			   }
    	   }
    	   System.out.println(sub);
    	   return sub;
       }
       
       public static void main(String[] args) {
		String s="aaaa";
		new LongestPalindromicSubString().solution1(s);
	}
}
