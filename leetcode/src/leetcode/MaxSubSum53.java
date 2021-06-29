package leetcode;

/**
 * ����һ��������һ�����������飬�������͵�������ĺͣ�
 * ����:[-1,1,2,-2,5]�����͵���������[1,2,-2,5],����6
 * @author yiwuhe
 *
 */
public class MaxSubSum53 {

	     //��������ָ���жϣ�currSum����ʾ��ǰ������ͣ�maxSum����ʾ���������ͣ�
	//���currSum+nums[i]<nums[i],��˵��currSum��ȻС��0����ô���Դ�nums[i]��ʼ���¼�����һ���������
	//���Գ�ʼ����currSum=0,maxSum=nums[0],����maxSum�������������飬currSum���浱ǰ�������
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
