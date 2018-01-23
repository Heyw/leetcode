package leetcode.dynamicprogram;

/**
 * WiggleSubsequence意味着一个序列是按照增减交替的方式排列的，例如[1,7,4,9,2,5]是wiggleSubsequence,因为差值为[6,-3,5,-7,3]
 * 而[1,7,4,5,5]就不是一个wiggleSubsequence，其差值序列为[6,-3,1,0]
 * 现在给定一个序列，求出其最长的wigglesubsquence序列的长度，并返回,要求o(n)时间复杂度
 * @author Administrator
 *
 */
public class WiggleSubsequence376 {
	   /**
	    * 
	    * @param nums
	    * @return
	    */
		public int wiggleMaxLength(int[] nums) {
			    if(nums==null||nums.length<2) return nums==null?0:nums.length;
		        Boolean isPreAdd=null;
		        int len=1;
		        for(int i=1;i<nums.length;i++){
		        	boolean isCurAdd=nums[i]>nums[i-1];
		        	if(nums[i]==nums[i-1]||(isPreAdd!=null&&isCurAdd==isPreAdd)){
		        		continue;
		        	}else{
		        		len++;
		        		isPreAdd=isCurAdd;
		        	}
		        }
		        return len;
		 }
		public static void main(String[] args) {
			int[] nums=new int[]{3,3,3,2,3};
			System.out.println(new WiggleSubsequence376().wiggleMaxLength(nums));
		}
}
