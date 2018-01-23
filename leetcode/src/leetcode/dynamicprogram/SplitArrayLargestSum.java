package leetcode.dynamicprogram;
/**
 * �������Ǹ���һ������Ϊn�����飬���䰴˳�򻮷�Ϊm�������飬���ҵ�һ�ַַ�ʹ����������Ԫ��֮����С
 * ���磺nums = [7,2,5,10,8]
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
	 * ���ö�̬�滮���
	 * ����dp[s][j]:��������ǰn[0]....n[j-1]��j��Ԫ�ػ���Ϊs���������õ�����С������֮��
	 * ��ôdp[s+1][i]=min(max(dp[s][j]),(n[j]+...+n[i-1]))��j+1<=i;
	 * ���s=i;��ôdp[s][i]=max{n[0],....[n-1]}
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
		        	for(int i=s;i<=nums.length;i++){//����ĳ�Ա������Ҫ���ڵ���������ĸ���
		        		for(int j=s-1;j<i;j++){
		        			int val=Math.max(dp[s-1][j], sum[i]-sum[j]);
		        			if(val<dp[s][i])dp[s][i]=val;
		        		}
		        	}
		        return dp[m][nums.length];
		    }
		/**
		 * ���ö��ַ��������⣬Ҫ�����С��������֮�ͣ�������Ҫ�˽��ֵ�ı߽磬���m=1����max=sum(nums),���m=nums.length����ô�Ϳ��Եõ�min=max{nums[i]};
		 * ������ǿ�����min��max֮�����ѭ��������ÿ��һ����[min,max]֮���target�����ǿ���ѭ��ԭ���飬�����ۼ�����Ԫ�غ�total��������һ����������count��
		 * ����ۼӹ�����total����target����ôcount��1��total=num����ônum����ѭ�������е�ǰ����Ԫ��
		 * ��������ۼӵ����֮ǰ��count�Ѿ�����m��
		 * ��ô˵����������targetΪ����ֵ�����ֵ�����������ᳬ��m����ô��ʱtargetӦ������
		 * ��������ۼӵ���󣬴�ʱcount��С��m��
		 * ��ô˵����������targetΪ����ֵ�����ֵ���������������ܵ���m����ô��ʱtargetӦ�ü�С��
		 *Ϊ����������[min,max]֮��target��Ч�ʣ�����ʹ�ö��ַ���target
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
		 * Ŀ��ֵ�Ƿ����target
		 * @param target
		 * @param nums
		 * @param m
		 * @return
		 */
		public boolean isbigger(long target,int[] nums,int m){
			 long total=0;
			 int count=1;//count�϶�Ҫ���ڵ���1��
			 for(int num:nums){
				 total+=num;
				 if(total>target){//target�Ƿ��ص����ֵ��total���ܴ���target���������target����ôtotal���Ƿ��ص����ֵ
					 total=num;
					 count++;
					 if(count>m)//��û����num����ʱcount�Ѿ����ڵ���m��˵��Ŀ��ֵҪ����target
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
