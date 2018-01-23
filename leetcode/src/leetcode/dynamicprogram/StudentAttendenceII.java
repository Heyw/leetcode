package leetcode.dynamicprogram;

/**
 * ѧ����ϯ1���P,�ٵ���L��ȱϯ��A��
 * ���������n����ȱϯ������1�죬�����ٵ����������ξ��Ǳ���Ϊ���Խ��͵ģ�������Խ��͵����м�¼����
 * ��n=2ʱ��ֻ��pp,pa,pl,ap,al,lp,la,ll�ǿ��Խ��͵ģ���������¼����
 * @author Administrator
 *
 */
public class StudentAttendenceII {
	/**
	 *�������������ö�̬�滮�ȽϷ��㣬dp[n][i][j]��ʾn���ڳ���A�Ĵ������Ϊi��������L��β�������Ϊj�ļ�¼��Ŀ��
	 *   i<2��j<3���ǿ��Խ��͵�
	 *   ����n=1ʱ,s=P����ôn=2ʱ��PA,PP,PL���ǿ��Խ��͵�
	 *   n=2ʱ,s=PA����ôn=3ʱ��PAP,PAL���ǿ��Խ��͵�
	 *   n=2ʱ��s=PL����ôn=3ʱ��PLP,PLA,PLL���ǿ��Խ��͵�
	 *   dp[n][0][2]={X......P,X.....PL,X.......LL}����X������A����ô����Щ���к������PA�϶��ǿ��Եģ����L��Ҫ�ж����������L�Ƿ��Ѿ�����2
	 *dp[n][j][0]��ʾn���ڳ���A�Ĵ���Ϊj��������L��β����Ϊ0�ļ�¼��Ŀ��.......X(X��=L),��ô��ֵ����dp[n-1][0]
	 *dp[n][j][1]��.........(AP)L,��ô��ֵ����dp[n-1][j][0](�������P)+dp[n-1][j][1](�������L)
	 *dp[n][j][2]��.........(APL)L,��ô��ֵ����dp[n-1][j][0]+dp[n-1][j][1]+dp[n-1][j][2]
	 *
	 * @param n
	 * @return
	 */
	  int mod=1000000007;
	 public int checkRecord(int n) {
	        int[][][]dp=new int[n+1][2][3];
	        dp[0]=new int[][]{{1,1,1},{1,1,1}};//��n=0ʱ������״�����ǿ��Խ��͵ģ���Ϊֻ�п��Խ��ͣ����������������ܽ���
	        int mod=1000000000+7;
	        for(int i=1;i<=n;i++){
	        	for(int j=0;j<2;j++){
	        		for(int k=0;k<3;k++){
	        			int val=dp[i-1][j][2];//ԭn-1���м����к����P��
	        			if(j>0) val=(val+dp[i-1][j-1][2])%mod;//ԭn-1���м����в���A�ĺ������A��
	        			if(k>0) val=(val+dp[i-1][j][k-1])%mod;//ԭn-1���м����н�β���ֻ��1��L�ĺ������L
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
            {0, 0, 1, 0, 1, 1}},�൱��a[6][6]*dp[n][6][1]�����������
            ��ôdp[n][][]=a^n*dp[0][][]������dp[0][][]={1,1,1,1,1,1},��ôdp[n][][]=a^(n+1)[5][2]
            �����ݵ�ƽ������Խ�ʱ�临�ӶȽ��͵�o(6*6*6lgn)

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
	  * ��ƽ���㷨
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
	 private  int[][] mul(int[][] a,int[][]b){//a[0].length��ʾ�г���a.length��ʾ�п�
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
