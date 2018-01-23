package leetcode.dynamicprogram;

import java.util.Arrays;
import java.util.HashMap;

public class PartitionEqualSubsetSum {
	/**
	 * 该问题是将两个非空正整数的数组分为两个子数组，如果这两个子数组之和相等，则返回true
	 * @param nums
	 * @return
	 */
	 public boolean canPartition(int[] nums) {
		  int sum=0;
	        for(int i=0;i<nums.length;i++) sum+=nums[i];
	        if(sum%2==1) return false;
	        int[] state=new int[nums.length];
	        return canPartion(sum,nums,0,state,new HashMap<String,Boolean>());
	    }

	private boolean canPartion(int sum, int[] nums, int total , int[] state, HashMap<String, Boolean> hashMap) {
		String st=Arrays.toString(state);
		if(hashMap.containsKey(st)) return hashMap.get(st);
		for(int i=0;i<nums.length;i++){
			if(state[i]==1) continue;
			state[i]=1;
			if((sum-nums[i]== total+nums[i]) || canPartion(sum-nums[i], nums, total+nums[i], Arrays.copyOf(state,state.length), hashMap) ){
				hashMap.put(Arrays.toString(state), true);
				return true;
			}
			hashMap.put(Arrays.toString(state), false);
			state[i]=0;
		}
		return false;
	}
	/**
	 * 这是一个背包问题，问题是求两个子序列之和相等，就相当于找出一个子序列等于数组和sum的二分之一，等于背包容量
	 * 相当于从数组中取出k个数据，k小于数组长度，看看这个k个数据是否能填满背包容量sum/2
	 * 定义数组dp[j]表示能否从数组中找出元素使得这些元素之和等于j，j<=sum/2,
	 * 这样对于任意元素nums[i]，我们可以选择nums[i]，也可以不选择nums[i];
	 * 如果dp[j]存在，那么我们无需在选择nums[i]，如果dp[j]不存在，我们需要判断dp[j-nums[i]]是否存在，如果存在那么dp[j]肯定也存在，
	 * 故dp[j]=dp[j]||dp[j-nums[i]]
	 * @param args
	 */
	 public boolean canPartition2(int[] nums) {
		 int sum=0;
		 for(int i=0;i<nums.length;i++) sum+=nums[i];
		 if(sum%2==1) return false;
		 int v=sum/2;
		 boolean[] dp=new boolean[v+1];
		 dp[0]=true;
		 for(int i=0;i<nums.length;i++){
			 for(int j=v;j>=nums[i];j--)
				 dp[j]=dp[j]||dp[j-nums[i]];
		 }
		 return dp[v];
	 }
	 
	 /**
	  * 深度搜索算法：思路和上面一样，用memo[j]来保存数组中是否能加和等于j的布尔值，
	  * 如果选择nums[index],就判断memo[j-nums[index]]是否存在，
	  * 如果不选择nums[index],就往下循环查找memo[j]是否存在
	  * 如果存在，memo[j]=true;
	  * @param nums
	  * @return
	  */
	 public boolean canPartition3(int[] nums) {
	        if (nums == null || nums.length == 0) {
	            return true;
	        }
	        int sum = 0;
	        for (int num : nums) {
	            sum += num;
	        }
	        if (sum % 2 != 0) {
	            return false;
	        }
	        sum = sum / 2;
	        return dfs(nums, 0, sum, new Boolean[sum + 1]);
	    }
	    private boolean dfs(int[] nums, int index, int remain, Boolean[] memo) {
	        if (index == nums.length) {
	            return remain == 0;
	        }
	        if (remain < 0) {
	            return false;
	        }
	        if (memo[remain] != null) {
	            return memo[remain];
	        }
	        /*for (int i = index; i < nums.length; i++) {
	            if (remain >= nums[index]) {
	                if(dfs(nums, index + 1, remain - nums[index], memo)) {
	                    memo[remain] = true;
	                    return true;
	                }
	            }
	        }
	        memo[remain] = false;
	        return false;*/
	        if (dfs(nums, index + 1, remain - nums[index], memo)) {
	            memo[remain] = true;
	            return true;
	        }
	        memo[remain] = dfs(nums, index + 1, remain, memo);
	        return memo[remain];
	    }

	public static void main(String[] args) {
		int[] arr=new int[]{1,5,5,11};
		System.out.println(new PartitionEqualSubsetSum().canPartition2(arr));
	}
}
