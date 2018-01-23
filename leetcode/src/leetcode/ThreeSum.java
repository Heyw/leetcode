package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
		public List<List<Integer>> threeSum(int[] nums) {
	        Arrays.sort(nums);
	        List<List<Integer>> list=new ArrayList<>();
	        for(int i=0;i<nums.length-2;i++){
	        	if(i>0&&nums[i]==nums[i-1])
	        		continue;
	        	int sum=-nums[i];
	        	int j=i+1,m=nums.length-1;
	        	while(j<m){
	        	    if(m<nums.length-1&&nums[m]==nums[m+1]){
	        	    	m--;
	        	        continue;
	        	    }
	        		if(nums[j]+nums[m]>sum)
	        			m--;
	        		else if(nums[j]+nums[m]<sum)
	        			j++;
	        		else{
	        			list.add(Arrays.asList(new Integer[]{nums[i],nums[j],nums[m]}));
	        			j++;m--;
	        		}
	        	}
	        }
	        return list;
	    
}
		public static void main(String[] args) {
			System.out.println(new ThreeSum().threeSum(new int[]{-1, 0, 1, 2,2, -1, -4}));
		}
}
