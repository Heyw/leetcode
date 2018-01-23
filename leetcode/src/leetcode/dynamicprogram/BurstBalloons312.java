package leetcode.dynamicprogram;

/**
 * 给定一组气球，每个气球都标有数字，代表硬币数，然后燃烧其中一个，将获得该气球与旁边气球上数字之积的硬币数，同时旁边的气球成了邻居
 * 求出给定气球能获得的最大的硬币数
 * 气球数不超过500，每个气球上的数字不超过100
 * 如果燃烧处于边界的气球那么超出边界之外的相当于用一个数字为1的气球替代
 * Example: 
	Given [3, 1, 5, 8] 
	Return 167 
	    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
	   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
	   
	   解法思路：(1)如果从n个气球中找到一个气球燃烧掉，然后继续从n-1个气球中找到一个合适的气球燃烧掉，这样虽然能解决问题
	   但是时间复杂度为o(n!)，不推荐使用；
	   (2)如果仅用dp，来保存计算过程中重复的变量，例如，先燃烧掉i，再燃烧掉j，求出剩余气球的硬币数，与先烧掉j再烧掉i求出剩余气球的硬币数已经重复了
	   这样做，对于k个balloons，需要k个布尔变量数组来保存结果值，总共会产生2^k个结果值，算法复杂度为o(2^n)，
	   虽然有所减少但是还是相当大的一笔计算量
	   (3)在以上我们都是考虑先燃烧掉一个balloon，然后计算其他的，但是在动态规划中，最常见想法的是什么？是反向思维！！
	   如果在[left,right]选择一个balloon最后燃烧掉， 那么该被选择的i，与left，right重新组成了[left,i],[i,right],
	   这样问题就转化成求出[left,i],[i,right]中的最大值与nums[i]*nums[left]*nums[right]之和的最大值；
	   这种解法就是分治和动态规划相结合的解法，算法复杂度为o(n^3)
 * @author Administrator
 *
 */
public class BurstBalloons312 {
		public int maxCoins(int[] nums) {
		        int[] newnums=new int[nums.length+2];
		        System.arraycopy(nums, 0, newnums, 1,nums.length);
		        newnums[0]=1;newnums[newnums.length-1]=1;
		        int[][] dp=new int[newnums.length][newnums.length];
		        //所有的循环都要先确定变量，left，right都是变量，i也是在left和right之间的变量，right-left>=2,maxright=newnums.length-1,minleft=0;
		        for(int k=2;k<newnums.length;k++){//k表示左右两边的宽度
		        	for(int left=0;left<newnums.length-k;left++){//对于每个宽度k，left最大值不能超过数组长度-k的值
		        		int right=left+k;
		        		for(int i=left+1;i<right;i++){
		        			dp[left][right]=Math.max(dp[left][right],dp[left][i]+dp[i][right]+newnums[i]*newnums[left]*newnums[right]);
		        		}
		        	}
		        	}
		        return dp[0][newnums.length-1];
		        }
		public static void main(String[] args) {
			int[] nums={3,1,5,8};
			System.out.println(new BurstBalloons312().maxCoins(nums));
		}
}
