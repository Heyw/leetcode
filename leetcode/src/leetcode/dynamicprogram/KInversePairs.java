package leetcode.dynamicprogram;

public class KInversePairs {
	/**
	 * ����������n�����1-n�е���ɵ����������У��ҳ���k������Ե�������
	 * ���i<j��a[i]>a[j]����һ�������
	 * ��3��0�������ֻ��(1,2,3)�������
	 * (3,1)��(2,1,3),(1,3,2)�������
	 * (3,2)��(3,1,2),(2,3,1)���������
	 * (3,3)��(3,2,1)������� 
	 * ����ö�̬�滮ȥ���ǣ�
	 * (4,0)=(3,0): (1,2,3,4)
	 * (4,1)=(3,0)+(3,1):(1,2,4,3)+(2,1,4,3)+(1,3,4,2)�����൱�ڽ�a[3]���뵽a[2]��λ�ã�Ȼ��a[2]�ƶ���a[3]
	 * (4,2)=(3,0)+(3,1)+(3,2):
	 *         [1,4,2,3]+[2,4,1,3]+[1,4,3,2]+[3,4,1,2]+[2,4,3,1]
	 * (4,3)=(3,3)+(4,2)=[3,2,1,4]+[4,1,2,3]+[2,1,4,3]+[1,3,4,2]+[3,1,4,2]+[2,3,4,1]
	 * ����(i,j)����ôi+1ֻ����뵽[1,i]�����һλ�ͻᱣ��ԭ�������Ŀ���䣬�������i+1��������mλΪi+1,��ô�ͻ���i+1-m�������,1<=m<=i+1
	 *��ô����(i,j-k)��ֻ��i+1���뵽i+1-kλ�ͻ���k������ԣ��Ӷ�ʵ��(i+1,j)
	 *��(i+1,j)=(i,0)+...+(i,j)=(i+1,j-1)+(i,j) 
	 *���j=i*(i-1)/2��ôֻ��һ�ֿ��ܣ�������û�п���
	 *����i+1���ֻ�ܴ���i������ԣ��������j>=i+1,��ô(i,j-i-1)����i+1���޷���ԭ��������(i+1,j)������Ҫ��ȥ�ⲿ������
	 * @param n
	 * @param k
	 * @return
	 */
     public int kInversePairs(int n, int k) {
    	 if(k>n*(n-1)/2)
    		 return 0;
    	 if(k==n*(n-1)/2||k==0)
    		 return 1;
    	 int mod=1000000007;
        long[][] dp=new long[n+1][k+1];//dp[i][j]����1��i��ɵ���������j������Ե�������
        for(int i=1;i<=n;i++){
        	for(int j=0;j<=k;j++){
        		if(j>i*(i-1)/2){
        			dp[i][j]=0;
        		}else if(j==i*(i-1)/2)
        			dp[i][j]=1;
        		else{
        		dp[i][j]=((j==0?0:dp[i][j-1])+dp[i-1][j]);
        		if(j>=i) dp[i][j]-=dp[i-1][j-i];
        		dp[i][j]=(dp[i][j]+mod)%mod;//mod�����������
        		}
        	}
        }
        
        return (int) dp[n][k];
    }
     public static int kInversePairs2(int n, int k) {
         int mod = 1000000007;
         if (k > n*(n-1)/2 || k < 0) return 0;
         if (k == 0 || k == n*(n-1)/2) return 1;
         long[][] dp = new long[n+1][k+1];
         dp[2][0] = 1;
         dp[2][1] = 1;
         for (int i = 3; i <= n; i++) {
             dp[i][0] = 1;
             for (int j = 1; j <= Math.min(k, i*(i-1)/2); j++) {
                 dp[i][j] = dp[i][j-1] + dp[i-1][j];
                 if (j >= i) 
                	 dp[i][j] -= dp[i-1][j-i];
                 dp[i][j] = (dp[i][j]+mod) % mod;
             }
         }
         return  (int)dp[n][k];
     }
     public static void main(String[] args) {
		System.out.println(new KInversePairs().kInversePairs(1000,1000));
	}
}
