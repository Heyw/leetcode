package leetcode.dynamicprogram;

import java.util.TreeSet;

/**
 * 该问题就是给定一个二维矩阵，然后求出该矩阵的子矩阵和在不大于k的情况下的最大值；
 * Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2

The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).
 * @author Administrator
 *
 */
public class MaxSumofRectangleNoLargerThanK {
	/**
	 * 暴力解法就是遍历所有子矩阵，然后找出其中不大于k的最大值，但是这种解法时间复杂度太高了，为了进行优化可以使用二分查找法；
	 * 
	 * 定义left，和right，其中col>right>=left>=0，就是left就是矩阵的左边界，right就是矩阵的右边界，col指的是矩阵的列宽
	 * 然后求出在left和right之间矩阵和，为了减少计算量，可以将二维数组看成一个个一维数组，然后计算第i行到第零行在left和right之间的和，
	 * 就是sum[i]，那么第i行和第j行在left和right之间的和就等于sum[i]-sum[j]，我们要求出sum[i]-sum[j]<=k的最大元素，那么我们需要找出
	 * sum[j]>=sum[i]-k的最小元素,为了快速查找这样的元素，我们可以将在left和right所有sum[i]都保存在一个二叉树上，
	 * 这样就可以利用二叉树的二分查找特性，当然为了保证查找的稳定型可以用红黑树代替，TreeSet
	 * @param matrix
	 * @param k
	 * @return
	 */
		public int maxSumSubmatrix(int[][] matrix, int k) {
		        if(matrix==null|| matrix[0]==null)
		        	return 0;
		        int col=matrix[0].length;
		        int row=matrix.length;
		        int max=Integer.MIN_VALUE;
		        for(int left=0;left<col;left++){
		        	int[]sum=new int[row];
		        	for(int right=left;right<col;right++){//右边界
		        		  TreeSet<Integer> tre=new TreeSet<>();
			        	  tre.add(0);//是为了防止第一行符合要求，而没能将第一行与max做比较
			        	  int curSum=0;//curSum代表行之和，两个cursum之间的差值就是矩阵的之和
		        		for(int up=0;up<row;up++){//up代表上边界
		        			sum[up]+=matrix[up][right];//sum[i]代表在从col在[left,right之间的]的i行之和
		        		  curSum+=sum[up];
		        		  Integer p=tre.ceiling(curSum-k);
		        		  if(p!=null){
		        			  max=Math.max(max, curSum-p);
		        		  }
		        		  tre.add(curSum);
		        	  }
		        	}
		        }
		        return max;
		    }
		
		public static void main(String[] args) {
			int[][] num=new int[][]{{1,0,1},{0,-2,3}};
			System.out.println(new MaxSumofRectangleNoLargerThanK().maxSumSubmatrix(num, 2));
		}
}
