package leetcode;

/**
 * 给定一个至少有一个整数的数组，求其最大和的子数组的和；
 * 例如:[-1,1,2,-2,5]，最大和的子数组是[1,2,-2,5],返回6
 * @author yiwuhe
 *
 */
public class MaxSubSum53 {

	     //利用两个指针判断，currSum：表示当前子数组和；maxSum：表示最大子数组和；
	//如果currSum+nums[i]<nums[i],则说明currSum必然小于0，那么可以从nums[i]开始重新计算下一个子数组和
	//所以初始条件currSum=0,maxSum=nums[0],利用maxSum保存最大和子数组，currSum保存当前子数组和
	    public int maxSubArray(int[] nums) {

	       int currSum=0;
	       int maxSum=nums[0];
	       for(int i=0;i<nums.length;i++){
	    	   
	    	   currSum=Math.max(currSum+nums[i], nums[i]);
	           maxSum=Math.max(maxSum, currSum);
	       }
       return maxSum;
}
	    public static void main(String[] args) {
			
	    	int maxSubArray = new MaxSubSum53().maxSubArray(new int[]{-1,1,2,-4,5,-3,5});
	    	System.out.println(maxSubArray);
		}
}
