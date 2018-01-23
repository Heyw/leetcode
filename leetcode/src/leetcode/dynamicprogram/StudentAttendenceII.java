package leetcode.dynamicprogram;

/**
 * 学生出席1天记P,迟到记L，缺席记A，
 * 如果在连续n天内缺席不超过1天，连续迟到不超过两次就是被认为可以奖赏的，求出可以奖赏的所有记录序列
 * 当n=2时，只有pp,pa,pl,ap,al,lp,la,ll是可以奖赏的，共八条记录序列
 * @author Administrator
 *
 */
public class StudentAttendenceII {
	/**
	 *解决该问题可以用动态规划比较方便，dp[n][i][j]表示n天内出现A的次数最多为i且以连续L结尾次数最多为j的记录数目，
	 *   i<2，j<3就是可以奖赏的
	 *   对于n=1时,s=P，那么n=2时，PA,PP,PL都是可以奖赏的
	 *   n=2时,s=PA，那么n=3时，PAP,PAL，是可以奖赏的
	 *   n=2时，s=PL，那么n=3时，PLP,PLA,PLL都是可以奖赏的
	 *   dp[n][0][2]={X......P,X.....PL,X.......LL}其中X不等于A，那么在这些序列后面添加PA肯定是可以的，添加L需要判断最后连续的L是否已经超过2
	 *dp[n][j][0]表示n天内出现A的次数为j且以连续L结尾次数为0的记录数目即.......X(X！=L),那么该值等于dp[n-1][0]
	 *dp[n][j][1]即.........(AP)L,那么该值等于dp[n-1][j][0](后面添加P)+dp[n-1][j][1](后面添加L)
	 *dp[n][j][2]即.........(APL)L,那么该值等于dp[n-1][j][0]+dp[n-1][j][1]+dp[n-1][j][2]
	 *
	 * @param n
	 * @return
	 */
	  int mod=1000000007;
	 public int checkRecord(int n) {
	        int[][][]dp=new int[n+1][2][3];
	        dp[0]=new int[][]{{1,1,1},{1,1,1}};//当n=0时，各种状况都是可以奖赏的，因为只有可以奖赏，接下来的天数才能奖赏
	        int mod=1000000000+7;
	        for(int i=1;i<=n;i++){
	        	for(int j=0;j<2;j++){
	        		for(int k=0;k<3;k++){
	        			int val=dp[i-1][j][2];//原n-1序列集合中后面加P；
	        			if(j>0) val=(val+dp[i-1][j-1][2])%mod;//原n-1序列集合中不含A的后面添加A；
	        			if(k>0) val=(val+dp[i-1][j][k-1])%mod;//原n-1序列集合中结尾最多只有1个L的后面添加L
	        			dp[i][j][k]=val;
	        		}
	        	}
	        }
	        return dp[n][1][2];
	    }
	 
	 /**
	  * dp[n][][]=a*dp[n-1][][];dp[n][][]={dp[n][0][0],dp[n][0][1],dp[n][0][2],dp[n][1][0],dp[n][1][1],dp[n][1][2]}
	  * a=new int[][]={{0, 0, 1, 0, 0, 0},
            {1, 0, 1, 0, 0, 0},
            {0, 1, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 1},
            {0, 0, 1, 1, 0, 1},
            {0, 0, 1, 0, 1, 1}},相当于a[6][6]*dp[n][6][1]两个矩阵相乘
            那么dp[n][][]=a^n*dp[0][][]，另外dp[0][][]={1,1,1,1,1,1},那么dp[n][][]=a^(n+1)[5][2]
            利用幂的平法算可以将时间复杂度降低到o(6*6*6lgn)

	  * @param n
	  */
	 public int checkRecord2(int n){
		 int[][] a={
		            {0, 0, 1, 0, 0, 0},
		            {1, 0, 1, 0, 0, 0},
		            {0, 1, 1, 0, 0, 0},
		            {0, 0, 1, 0, 0, 1},
		            {0, 0, 1, 1, 0, 1},
		            {0, 0, 1, 0, 1, 1},
		    };
		 System.out.println(mul(a,a)[5][2]);
       return  expBySquare(a, n+1)[5][2];
	 }
	 /**
	  * 幂平方算法
	  * @param a
	  * @return
	  */
	 private int[][] expBySquare(int[][] a,int n){
		 int[][] res=new int[a.length][a.length];
		 for(int i=0;i<res[0].length;i++)
				 res[i][i]=1;
		 while(n>0){
			 if(n%2==1)
				 res=mul(res,a);
			 a=mul(a,a);
			 n/=2;
		 }
		 return res;
	 }
	 private  int[][] mul(int[][] a,int[][]b){//a[0].length表示列长，a.length表示行宽
		 int[][] c=new int[a.length][b.length];
		 for(int i=0;i<a.length;i++)
			 for(int j=0;j<a.length;j++)
				 for(int k=0;k<b.length;k++){
					 c[i][j]=(int) ((c[i][j]+(long)a[i][k]*b[k][j])%mod);
				 }
		 return c;
	 }
	 public static void main(String[] args) {
		System.out.println(new StudentAttendenceII().checkRecord2(2));
		System.out.println(9/2);
	}
}
