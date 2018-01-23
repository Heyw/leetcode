package leetcode.dynamicprogram;

/**
 * 给定一个数组arr，两个选手轮流可以从头尾中取出一个数，求第一个选手取出的数之和能否大于第二个选手
 * [1,5,3]，对于该数组第一个选手是不可能成功的，返回false
 * [1,5,34,4]，第一个选手首先选择1，那么第二个选手只能选择5，4,那么第一个选手选择34，就能够成功 返回true
 * @author Administrator
 *
 */
public class PredictWinner {
	/**
	 * 两个选手不断取出数据，最后比较大小，相当于轮流给首尾标上正负号，求其和能否大于零
	 * max(i,j)表示选手在(i,j)选择能导致最大的结果值
	 * 则int result=max(0,n-1)-max(max(1,n-1),max(0,n-2))
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
