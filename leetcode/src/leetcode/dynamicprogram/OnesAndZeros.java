package leetcode.dynamicprogram;

/**
 * ����m��0��n��1���Լ�һ��ֻ��0��1��ɵ��ַ������飬������ڸ�����0��1�����£��������ɶ��ٸ��ַ��������е�Ԫ��
 * ÿ��0��1ֻ�ܱ���1��
 * Input: Array = {"10", "0", "1"}, m = 1, n = 1
    Output: 2
    Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".

 * @author Administrator
 *
 */
public class OnesAndZeros {
	/**
	 * ��������i��Ԫ�أ�ֻ������ѡ����ϻ��߲���ϣ�������������1����m��n��ֵ��Ӧ���٣����������������䣬m��n��ֵ���ı�
	 * ��dp[i][j][k]��ʾ������i��j��0��k��1����ϳɵ�����Ԫ�ص�������
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
