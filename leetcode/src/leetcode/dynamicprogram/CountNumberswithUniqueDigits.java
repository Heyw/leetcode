package leetcode.dynamicprogram;

/**
 * 给定一个非负整数n，返回在[0,10^)中所有由唯一数字组成的数字个数
 * 例如：n=2，那么[0,99)中除了(11,22,33,44,55,66,77,88,99)外都是由唯一数字组成，因此共有91个
 * @author Administrator
 *
 */
public class CountNumberswithUniqueDigits {
	/**
	 * dp[i]表示i位数时由不重复数字字组成的个数,可以看成i个桶排成一排
	 * dp[1]=10;
	 * dp[2]=9*9+10;第一个桶可以放入[1,9]中的任意一个，共有9种选择，第二个同可以放入[0,9]中除了第一个以外的任意一个，也有9中选择
	 * dp[3]=9*9*8+dp[2]；
	 *
	 * dp[i]=9*C+dp[i-1],其中C表示从10个数中选出不重复的排列=9*8*...*(10-i+1)

	 * @param 
	 * @return
	 */
	public int countNumbersWithUniqueDigits(int n) {
		if(n<1) return 0;     
		int total=10;
		int c=9;
        for(int i=2;i<=n;i++){
        	if(11-i<=0) break;
        	c*=(11-i);
            total+=c;
        }
        return total;
	    }
	 public static void main(String[] args) {
		System.out.println(new CountNumberswithUniqueDigits().countNumbersWithUniqueDigits(3));
	}
}
