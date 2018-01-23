package leetcode.dynamicprogram;

/**
 * 最长回文字符串问题
 * 给定一个字符串,求出该字符串的最长可能回文子串
 * “cbbd”返回:bb
 * "bbbad" 返回：bbb
 * @author Administrator
 *
 */
public class LongestPLDSubsequence {
	/**
	 * 不断判断首尾是否一样，如果一样保存首尾，
	 * 时间复杂度有点大
	 * @param s
	 * @return
	 */
	 public int longestPalindromeSubseq(String s) {
	        if(s==null) return 0;
	        char[] carr = s.toCharArray();
	        return calLongestPLDLength(carr, 0, carr.length-1);
	        
	    }
	 
	 private int calLongestPLDLength(char[] carr,int l,int h){
		 if(h<l) return 0;
		 if(l==h)
			return 1;
		 else{
			 if(carr[l]==carr[h]){
				return calLongestPLDLength(carr, l+1, h-1)+2;
			 }
			 else
				 return Math.max(calLongestPLDLength(carr, l+1, h), calLongestPLDLength(carr, l, h-1));
		 }
	 }
	 
	 /**
	  * 上面递归时间太长了，利用动态规划，保存递归过的记录，节省时间
	  * @param s
	  * @return
	  */
	 public int longestPalindromeSubseq2(String s) {
	        if(s==null) return 0;
	        char[] carr = s.toCharArray();
	         int count=0;
	       int[][] dp=new int[carr.length][carr.length];//dp[i][j]表示i到j的子串的最长回文串长度
	       for(int i=carr.length-1;i>=0;i--){//从大到小遍历，因为dp[i][j]中的i依赖i+1的值
	    	   dp[i][i]=1;
	    	   for(int j=i+1;j<carr.length;j++){
	    		   if(carr[i]==carr[j])
	    			   dp[i][j]=dp[i+1][j-1]+2;
	    		   else
	    			   dp[i][j]=Math.max(dp[i+1][j], dp[i][j-1]);
	    	   }
	       }
	        return dp[0][carr.length-1];
	    }
	 
	 public static void main(String[] args) {
		String s="cbaaaacc";
		System.out.println(new LongestPLDSubsequence().longestPalindromeSubseq2(s));

	}
}
