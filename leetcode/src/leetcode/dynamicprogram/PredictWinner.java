package leetcode.dynamicprogram;

/**
 * ����һ������arr������ѡ���������Դ�ͷβ��ȡ��һ���������һ��ѡ��ȡ������֮���ܷ���ڵڶ���ѡ��
 * [1,5,3]�����ڸ������һ��ѡ���ǲ����ܳɹ��ģ�����false
 * [1,5,34,4]����һ��ѡ������ѡ��1����ô�ڶ���ѡ��ֻ��ѡ��5��4,��ô��һ��ѡ��ѡ��34�����ܹ��ɹ� ����true
 * @author Administrator
 *
 */
public class PredictWinner {
	/**
	 * ����ѡ�ֲ���ȡ�����ݣ����Ƚϴ�С���൱����������β���������ţ�������ܷ������
	 * max(i,j)��ʾѡ����(i,j)ѡ���ܵ������Ľ��ֵ
	 * ��int result=max(0,n-1)-max(max(1,n-1),max(0,n-2))
	 *result(i,j)= max(i,j)-max(max(i+1,j),max(i,j-1))
	 * 
	 * @param nums
	 * @return
	 */
	 public boolean PredictTheWinner(int[] nums) {
	        int[][] mem=new int[nums.length][nums.length];
	        return getResult(nums,0,nums.length-1,mem)>=0;
	    }
	     private int getResult(int[] nums,int i,int j, int[][] mem){
	    	 if(mem[i][j]==0){
	    		 mem[i][j]=i==j?nums[i]:Math.max(nums[i]-getResult(nums,i+1,j,mem),nums[j]-getResult(nums,i,j-1,mem));
	    	 }
	    	 return mem[i][j];
	     }
	 
}
