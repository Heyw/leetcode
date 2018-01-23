package leetcode.dynamicprogram;

/**
 * WiggleSubsequence��ζ��һ�������ǰ�����������ķ�ʽ���еģ�����[1,7,4,9,2,5]��wiggleSubsequence,��Ϊ��ֵΪ[6,-3,5,-7,3]
 * ��[1,7,4,5,5]�Ͳ���һ��wiggleSubsequence�����ֵ����Ϊ[6,-3,1,0]
 * ���ڸ���һ�����У���������wigglesubsquence���еĳ��ȣ�������,Ҫ��o(n)ʱ�临�Ӷ�
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
