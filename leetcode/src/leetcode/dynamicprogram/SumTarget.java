package leetcode.dynamicprogram;

import java.util.Arrays;

public class SumTarget {
	 public int findTargetSumWays(int[] nums, int S) {
		 int sum=0;
		 Arrays.sort(nums);
		 for(int i=0;i<nums.length;i++){
			 sum+=nums[i];
		 }
		if(sum<S) return 0;	 
		 int count=0;
		 count=checkSubSumIsTarget(nums,S,sum,count,-1);
		 return count;
	}
	 private int checkSubSumIsTarget(int[] nums,int S,int presum,int count,int curr){
          int sum=(curr>=0?presum-2*nums[curr]:presum);
		  if(sum==S)  count++;
		 if(sum<S) return count;
		 for(int i=curr+1;i<nums.length;i++){
			 if(sum-2*nums[i]>=S)
			   count=checkSubSumIsTarget(nums,S,sum,count,i);
			 else
				 break;
		 }
		 return count;
	 }
	 
	
	 
	 
	 public static void main(String[] args) {
		int[] nums=new int[]{1,0};
		System.out.println(new SumTarget().findTargetSumWays(nums, 1));
	}
}
