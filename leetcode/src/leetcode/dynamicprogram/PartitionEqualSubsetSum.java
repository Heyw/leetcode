package leetcode.dynamicprogram;

import java.util.Arrays;
import java.util.HashMap;

public class PartitionEqualSubsetSum {
	/**
	 * �������ǽ������ǿ��������������Ϊ���������飬���������������֮����ȣ��򷵻�true
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
	 * ����һ���������⣬������������������֮����ȣ����൱���ҳ�һ�������е��������sum�Ķ���֮һ�����ڱ�������
	 * �൱�ڴ�������ȡ��k�����ݣ�kС�����鳤�ȣ��������k�������Ƿ���������������sum/2
	 * ��������dp[j]��ʾ�ܷ���������ҳ�Ԫ��ʹ����ЩԪ��֮�͵���j��j<=sum/2,
	 * ������������Ԫ��nums[i]�����ǿ���ѡ��nums[i]��Ҳ���Բ�ѡ��nums[i];
	 * ���dp[j]���ڣ���ô����������ѡ��nums[i]�����dp[j]�����ڣ�������Ҫ�ж�dp[j-nums[i]]�Ƿ���ڣ����������ôdp[j]�϶�Ҳ���ڣ�
	 * ��dp[j]=dp[j]||dp[j-nums[i]]
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
	  * ��������㷨��˼·������һ������memo[j]�������������Ƿ��ܼӺ͵���j�Ĳ���ֵ��
	  * ���ѡ��nums[index],���ж�memo[j-nums[index]]�Ƿ���ڣ�
	  * �����ѡ��nums[index],������ѭ������memo[j]�Ƿ����
	  * ������ڣ�memo[j]=true;
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
