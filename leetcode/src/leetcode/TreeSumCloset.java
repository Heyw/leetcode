package leetcode;

import java.util.Arrays;

/**
 * ����һ�������һ��Ŀ��ֵ������������������Ԫ��֮����ӽ�Ŀ��ֵ�ļ���
 * @author heyw
 *
 */
public class TreeSumCloset {
	/**
	 * �����ظ���Ԫ��ʱ����ֱ������
	 * @param nums
	 * @param target
	 * @return
	 */
	 public int threeSumClosest(int[] nums, int target) {
	       Arrays.sort(nums); 
	       int closetSum=nums[0]+nums[1]+nums[2];
	       for(int i=0;i<nums.length-2;i++){
	    	   if(i>0&&nums[i]==nums[i-1])//�����ظ�
	    		   continue;
	    	   int lo=i+1;int hi=nums.length-1;
	    	   while(lo<hi){
	    		   int sum=nums[i]+nums[lo]+nums[hi];
	    		   if(Math.abs(sum-target)<Math.abs(closetSum-target))
	    			   closetSum=sum;
	    		   if(sum<target){
	    			   while(lo<hi&&nums[lo]==nums[lo+1]) lo++;//�����ظ�
	    			   lo++;
	    		   }
	    		   else if(sum>target){
	    			   while(lo<hi&&nums[hi]==nums[hi-1]) hi--;
	    			  hi--;
	    		   }
	    		   else{
	    			   return target;
	    		   } 
	       }
	       }
	       return closetSum;
	    }
	 
	 public static void main(String[] args) {
		 int[] nums={-1,0,1,1,55};
		 int target=3;
		System.out.println(new TreeSumCloset().threeSumClosest(nums, target));
	}
}
