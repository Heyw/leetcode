package leetcode.dynamicprogram;

import java.util.HashMap;
import java.util.Map;

/**
 * 给一个数组和目标k，如果数组有有子数组之和等于k的倍数，返回true
 * Input: [23, 2, 4, 6, 7],  k=6
* Output: True
* Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
* Input: [23, 2, 6, 4, 7],  k=6
* Output: True
* Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42=6*7.
 * @author Administrator
 *
 */
public class ContinuousSubarraySum {
   /**
    * dp[i][j]表示i到j的sum,j 大于i
    * @param nums
    * @param k
    * @return
    */
	public boolean checkSubarraySum(int[] nums, int k) {
		if(nums.length<=1)
		return false;
	    int[][] dp=new int[nums.length][nums.length];
	    for(int i=0;i<nums.length;i++){
	    	dp[i][i]=nums[i];
	    }
	    for(int i=0;i<nums.length-1;i++){
	    	for(int j=i+1;j<nums.length;j++){
	    		dp[i][j]=dp[i][j-1]+nums[j];
	    	    if(dp[i][j]==0&&k==0||k!=0&&dp[i][j]%k==0)
	    	    	return true;
	    	}
	    }
    return false;}
	public boolean checkSubarraySumTwo(int[] nums, int k) {
		
		for(int i=0;i<nums.length-1;i++){
	       if( check(nums,k,i))
	    	   return true;
		}
		return false;
	}
	
	private boolean check(int[] nums, int k, int i) {
		int sum=nums[i];
		for(int j=i+1;j<nums.length;j++){
			sum+=nums[j];
			if(sum==0||k==0||sum%k==0)
				return true;
		}
		return false;
	}
	
	/**
	 * 如果a和b对k的余数相同，那么a-b一定能等于k*n
	 * @param nums
	 * @param k
	 * @return
	 */
	public boolean checkSubarraySumThree(int[] nums, int k) {
	    Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{put(0,-1);}};;
	    int runningSum = 0;
	    for (int i=0;i<nums.length;i++) {
	        runningSum += nums[i];
	        if (k != 0) runningSum %= k; 
	        Integer prev = map.get(runningSum);
	        if (prev != null) {
	            if (i - prev > 1) return true;
	        }
	        else map.put(runningSum, i);
	    }
	    return false;
	}
	public static void main(String[] args) {
		int[] nums=new int[]{23,2,6,4,7};
		System.out.println(new ContinuousSubarraySum().checkSubarraySumThree(nums,6));
	}
}
