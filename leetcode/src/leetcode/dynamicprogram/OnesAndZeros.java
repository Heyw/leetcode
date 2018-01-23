package leetcode.dynamicprogram;

/**
 * 给定m个0和n个1，以及一个只有0和1组成的字符串数组，求出来在给定的0和1条件下，能最多组成多少个字符串数组中的元素
 * 每个0和1只能被用1次
 * Input: Array = {"10", "0", "1"}, m = 1, n = 1
    Output: 2
    Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".

 * @author Administrator
 *
 */
public class OnesAndZeros {
	/**
	 * 对于数组i处元素，只有两种选择：组合或者不组合，组合则组合数加1，且m和n的值相应减少，不组合则组合数不变，m和n的值不改变
	 * 用dp[i][j][k]表示在数组i用j个0和k个1能组合成的数组元素的最大个数
	 * dp[i][j][k]=Max(1+dp[i+1][j-str(i)][k-str(i)],dp[i+1][j][k])
	 * @param strs
	 * @param m
	 * @param n
	 * @return
	 */
	public int findMaxForm(String[] strs, int m, int n) {
		  int[][] dp=new int[m+1][n+1];
		for(String s:strs){
			int[] count=count(s);
			for(int i=m;i>=count[0];i--)
				for(int j=n;j>=count[1];j--)
					dp[i][j]=Math.max(dp[i][j], 1+dp[i-count[0]][j-count[1]]);
	       
		}
		return dp[m][n];
	    }
	private int[] count(String str){
	    int[] count=new int[2];
		char[] charArray = str.toCharArray();
		for(char c:charArray){
			count[c-'0']++;
		}
	   return count;
	}
	
//	private int getMax(String[] strs,int i,int j,int k int[] dp){
//		int[] ozs=new int[]{j,k};
//		if(i>=strs.length)
//			dp[i]= 0;
//		if(isOneAndZeroEnough(strs[i], ozs)){
//			dp[i]= Math.max(1+getMax(strs,i+1,ozs[0],ozs[1]),dp[i+1]);
//		}else
//			 dp[i]=dp[i+1];
//	}
	public static void main(String[] args) {
		String[] strs={"10", "0001", "111001", "1", "0"};
		System.out.println(new OnesAndZeros().findMaxForm(strs, 5, 3));
	}
}
