package leetcode.dynamicprogram;

import java.util.Arrays;

/**
 * 该问题是给定一个数组，和一个目标值，从数组中找出元素使得元素之和等于目标值，元素可以重复，
 * 求出这样的元素排列有多少种，元素完全一样，位置不一样也可以
*       nums = [1, 2, 3]
		target = 4
		The possible combination ways are:
		(1, 1, 1, 1)
		(1, 1, 2)
		(1, 2, 1)
		(1, 3)
		(2, 1, 1)
		(2, 2)
		(3, 1)
		Note that different sequences are counted as different combinations.
		Therefore the output is 7.
 * @author Administrator
 *
 */
public class CombinationSumIV {
	    /**
	     * dp[i][j]:以nums[i]开头目标值为j的排列数
	     * dp[i][target]=sum(dp[k][target-nums[i]])
	     * dp[k][nums[k]]=1;
	     * @param nums
	     * @param target
	     * @return
	     */
		public int combinationSum4(int[] nums, int target) {
		        Integer[][] dp=new Integer[nums.length][target+1];
		        Arrays.sort(nums);
		        return getNums(nums, dp, target);
		 }
		private int getNums(int[] nums,Integer[][] dp,int target){
			int cur=0;
			for(int i=0;i<nums.length;i++){
				if(nums[i]>target) break;
			     if(nums[i]==target)
			    	 dp[i][target]=1;
			     else
			    	 dp[i][target]=dp[i][target]==null?getNums(nums, dp, target-nums[i]):dp[i][target];
			     cur+=dp[i][target];
			}
			return cur;
		}
		public static void main(String[] args) {
			int[] nums=new int[]{1,2,3,4};
			System.out.println(new CombinationSumIV().combinationSum4(nums, 4));
		}
}
