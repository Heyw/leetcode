package leetcode.dynamicprogram;

/**
 * ����һ������ÿ�����򶼱������֣�����Ӳ������Ȼ��ȼ������һ��������ø��������Ա�����������֮����Ӳ������ͬʱ�Աߵ���������ھ�
 * ������������ܻ�õ�����Ӳ����
 * ������������500��ÿ�������ϵ����ֲ�����100
 * ���ȼ�մ��ڱ߽��������ô�����߽�֮����൱����һ������Ϊ1���������
 * Example: 
	Given [3, 1, 5, 8] 
	Return 167 
	    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
	   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
	   
	   �ⷨ˼·��(1)�����n���������ҵ�һ������ȼ�յ���Ȼ�������n-1���������ҵ�һ�����ʵ�����ȼ�յ���������Ȼ�ܽ������
	   ����ʱ�临�Ӷ�Ϊo(n!)�����Ƽ�ʹ�ã�
	   (2)�������dp�����������������ظ��ı��������磬��ȼ�յ�i����ȼ�յ�j�����ʣ�������Ӳ�����������յ�j���յ�i���ʣ�������Ӳ�����Ѿ��ظ���
	   ������������k��balloons����Ҫk����������������������ֵ���ܹ������2^k�����ֵ���㷨���Ӷ�Ϊo(2^n)��
	   ��Ȼ�������ٵ��ǻ����൱���һ�ʼ�����
	   (3)���������Ƕ��ǿ�����ȼ�յ�һ��balloon��Ȼ����������ģ������ڶ�̬�滮�У�����뷨����ʲô���Ƿ���˼ά����
	   �����[left,right]ѡ��һ��balloon���ȼ�յ��� ��ô�ñ�ѡ���i����left��right���������[left,i],[i,right],
	   ���������ת�������[left,i],[i,right]�е����ֵ��nums[i]*nums[left]*nums[right]֮�͵����ֵ��
	   ���ֽⷨ���Ƿ��κͶ�̬�滮���ϵĽⷨ���㷨���Ӷ�Ϊo(n^3)
 * @author Administrator
 *
 */
public class BurstBalloons312 {
		public int maxCoins(int[] nums) {
		        int[] newnums=new int[nums.length+2];
		        System.arraycopy(nums, 0, newnums, 1,nums.length);
		        newnums[0]=1;newnums[newnums.length-1]=1;
		        int[][] dp=new int[newnums.length][newnums.length];
		        //���е�ѭ����Ҫ��ȷ��������left��right���Ǳ�����iҲ����left��right֮��ı�����right-left>=2,maxright=newnums.length-1,minleft=0;
		        for(int k=2;k<newnums.length;k++){//k��ʾ�������ߵĿ��
		        	for(int left=0;left<newnums.length-k;left++){//����ÿ�����k��left���ֵ���ܳ������鳤��-k��ֵ
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
