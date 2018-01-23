package leetcode.dynamicprogram;

/**
 * 移动箱子获取点数的问题，一次移除k个连续相同颜色的箱子可以获得k^2点数，箱子颜色可以用整数表示
 * 例如Example 1: 
Input: 
[1, 3, 2, 2, 2, 3, 4, 3, 1] 
Output: 
23 
Explanation: 
[1, 3, 2, 2, 2, 3, 4, 3, 1] 
—-> [1, 3, 3, 4, 3, 1] (3*3=9 points) 
—-> [1, 3, 3, 3, 1] (1*1=1 points) 
—-> [1, 1] (3*3=9 points) 
—-> [] (2*2=4 points)
总的points为23，求出ponits最大的数
 * @author Administrator
 *
 */
public class RemoveBoxes {
    /**
     * 对于数组boxes[n]来说，从左往右，对于每一i所代表的数字，它只有两种选择：
     * 1 。将自身以及左边所有相同的颜色移除，获得点数与i+1到n-1 的之间最大点数之和；
     * 2.从i+1开始寻找，找到拥有颜色相同的m，然后从m开始进行1的操作
     * 定义dp[i][j][k]:其中i代表子数组左边界，j代表子数组右边界，k表示位置小于i的且和boxes[i]大小（颜色）相同的连续箱子个数
     * 那么对于dp[i][j][k]就是移除boxes中i+1到j所有箱子以及i左侧与boxes[i]相同颜色的箱子的最大点数
     * 我们按照上面的选择：
     * 1.如果移除boxes[i]本身以及左边相同的颜色的箱子,则dp[i][j][k]=(1+k)^2+dp[i+1][j][0];
     * 2.如果从i+1往右遍历寻找m使得boxes[m]=boxes[i]，然后求出移除i+1到m-1之间最大点数dp[i+1][m-1][0]，
     * 与m到j之间最大点数dp[m][j][k+1]之和,k+1是因为i在m左边，小于i的位置有k个相同颜色，那么m左边就有k+1个颜色相同的箱子
     * 比较这两者大小，去大者为大；
     * 
     * 注:所有子序列问题都可以归类为三个限制性的整数问题(i,j,k)其中0<=i,j,k<length，然后通过一个三位数组n*n*n是足够充当缓存中间数据的
     * @param boxes
     * @return
     */
	public int removeBoxes(int[] boxes){
		int n=boxes.length;
		int[][][] dp=new int[n][n][n];
    	int max= getMaxPointers(boxes,dp,0,boxes.length-1,0);
    	 return max;
      }

	private int getMaxPointers(int[] boxes, int[][][] dp, int i, int j, int k) {
		   if(i>j) return 0;//因为i大于j时，从左往右是没有箱子的
		   if(dp[i][j][k]>0) return dp[i][j][k];
		   int res=(k+1)*(k+1)+getMaxPointers(boxes,dp,i+1,j,0);//第一种方式
		   for(int m=i+1;m<=j;m++){
			   if(boxes[m]==boxes[i]){
				   int second=getMaxPointers(boxes,dp,i+1,m-1,0)+getMaxPointers(boxes,dp,m,j,k+1);
				   res=Math.max(res, second);
			   }
		   }
		   dp[i][j][k]=res;
		   return res;
	}
	public static void main(String[] args) {
		int[] boxes={1, 3, 2, 2, 2, 3, 4, 3, 1};
		System.out.println(new RemoveBoxes().removeBoxes(boxes));
	}
}
