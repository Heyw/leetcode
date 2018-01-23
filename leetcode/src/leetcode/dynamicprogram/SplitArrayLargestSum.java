package leetcode.dynamicprogram;
/**
 * 该问题是给定一个长度为n的数组，将其按顺序划分为m个子数组，求找到一种分法使得其子数组元素之和最小
 * 例如：nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
 * @author Administrator
 *
 */
public class SplitArrayLargestSum {
	/**
	 * 利用动态规划求解
	 * 定义dp[s][j]:将数组中前n[0]....n[j-1]共j个元素划分为s个子数组后得到的最小子数组之和
	 * 那么dp[s+1][i]=min(max(dp[s][j]),(n[j]+...+n[i-1]))，j+1<=i;
	 * 如果s=i;那么dp[s][i]=max{n[0],....[n-1]}
	 * @param nums
	 * @param m
	 * @return
	 */
		public int splitArrayByDP(int[] nums, int m) {
		        int[] sum=new int[nums.length+1];
		        for(int i=1;i<=nums.length;i++){
		        	sum[i]=sum[i-1]+nums[i-1];
		        }
		        int[][] dp=new int[m+1][nums.length+1];
		        for(int i=0;i<=m;i++){
		        	for(int j=0;j<=nums.length;j++)
		        		dp[i][j]=Integer.MAX_VALUE;
		        }
		        dp[0][0]=0;
		        for(int s=1;s<=m;s++)
		        	for(int i=s;i<=nums.length;i++){//数组的成员个数需要大于等于子数组的个数
		        		for(int j=s-1;j<i;j++){
		        			int val=Math.max(dp[s-1][j], sum[i]-sum[j]);
		        			if(val<dp[s][i])dp[s][i]=val;
		        		}
		        	}
		        return dp[m][nums.length];
		    }
		/**
		 * 利用二分法求解该问题，要求出最小的子数组之和，我们需要了解该值的边界，如果m=1，则max=sum(nums),如果m=nums.length，那么就可以得到min=max{nums[i]};
		 * 因此我们可以在min和max之间遍历循环，对于每个一个在[min,max]之间的target，我们可以循环原数组，不断累加数组元素和total，并设置一个计数变量count，
		 * 如果累加过程中total大于target，那么count加1，total=num，那么num等于循环过程中当前数组元素
		 * 如果数组累加到最后之前，count已经等于m，
		 * 那么说明该数组以target为返回值，划分的子数组个数会超过m，那么此时target应该增大；
		 * 如果数组累加到最后，此时count还小于m，
		 * 那么说明该数组以target为返回值，划分的子数组个数不可能等于m，那么此时target应该减小；
		 *为了提升遍历[min,max]之间target的效率，可以使用二分法来target
		 * @param nums
		 * @param m
		 * @return
		 */
		public int splitArrayByBinarySearch(int[] nums, int m) {
			int minTarget=nums[0];
			long maxTarget=nums[0];
			for(int i=1;i<nums.length;i++){
				maxTarget+=nums[i];
				minTarget=Math.max(minTarget, nums[i]);
			}
			long l=minTarget,r=maxTarget;
			while(l<=r){
		          long mid=(l+r)/2;
		          if(isbigger(mid, nums,m)){
		        	 l=mid+1; 
		          }else{
		        	 r=mid-1;
		          }
			}
			return (int)l;
		}
		
		/**
		 * 目标值是否大于target
		 * @param target
		 * @param nums
		 * @param m
		 * @return
		 */
		public boolean isbigger(long target,int[] nums,int m){
			 long total=0;
			 int count=1;//count肯定要大于等于1的
			 for(int num:nums){
				 total+=num;
				 if(total>target){//target是返回的最大值，total不能大于target，如果大于target，那么total才是返回的最大值
					 total=num;
					 count++;
					 if(count>m)//还没算上num，此时count已经大于等于m，说明目标值要大于target
						 return true;
				 }
			 }
			 return false;
		}
		
		public static void main(String[] args) {
			int[] nums=new int[]{7,2,5,10,8};
			System.out.println(new SplitArrayLargestSum().splitArrayByBinarySearch(nums, 2));
		}
}
