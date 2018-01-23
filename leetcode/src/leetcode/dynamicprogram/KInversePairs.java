package leetcode.dynamicprogram;

public class KInversePairs {
	/**
	 * 给定正整数n，求从1-n中的组成的所有数组中，找出有k个逆序对的数组数
	 * 如果i<j，a[i]>a[j]就是一个逆序对
	 * （3，0）的情况只有(1,2,3)这种情况
	 * (3,1)有(2,1,3),(1,3,2)这种情况
	 * (3,2)有(3,1,2),(2,3,1)这两种情况
	 * (3,3)有(3,2,1)这种情况 
	 * 如果用动态规划去考虑，
	 * (4,0)=(3,0): (1,2,3,4)
	 * (4,1)=(3,0)+(3,1):(1,2,4,3)+(2,1,4,3)+(1,3,4,2)，就相当于将a[3]插入到a[2]的位置，然后a[2]移动到a[3]
	 * (4,2)=(3,0)+(3,1)+(3,2):
	 *         [1,4,2,3]+[2,4,1,3]+[1,4,3,2]+[3,4,1,2]+[2,4,3,1]
	 * (4,3)=(3,3)+(4,2)=[3,2,1,4]+[4,1,2,3]+[2,1,4,3]+[1,3,4,2]+[3,1,4,2]+[2,3,4,1]
	 * 假设(i,j)，那么i+1只需插入到[1,i]的最后一位就会保持原逆序对数目不变，假设插入i+1的数组中m位为i+1,那么就会多出i+1-m个逆序对,1<=m<=i+1
	 *那么对于(i,j-k)，只需i+1插入到i+1-k位就会多出k个逆序对，从而实现(i+1,j)
	 *故(i+1,j)=(i,0)+...+(i,j)=(i+1,j-1)+(i,j) 
	 *如果j=i*(i-1)/2那么只有一种可能，超过则没有可能
	 *另外i+1最多只能带来i个逆序对，所以如果j>=i+1,那么(i,j-i-1)插入i+1是无法是原有数组变成(i+1,j)，故需要减去这部分数组
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
        long[][] dp=new long[n+1][k+1];//dp[i][j]代表1到i组成的数组中有j个逆序对的数组数
        for(int i=1;i<=n;i++){
        	for(int j=0;j<=k;j++){
        		if(j>i*(i-1)/2){
        			dp[i][j]=0;
        		}else if(j==i*(i-1)/2)
        			dp[i][j]=1;
        		else{
        		dp[i][j]=((j==0?0:dp[i][j-1])+dp[i-1][j]);
        		if(j>=i) dp[i][j]-=dp[i-1][j-i];
        		dp[i][j]=(dp[i][j]+mod)%mod;//mod避免整数溢出
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
