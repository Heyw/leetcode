package leetcode.dynamicprogram;

/**
 * 该问题就是给定一个m*n的方格子，指定位置(i,j)的小球，可以移动n步，求出该小球在n步内走出方格子的路径数
 * @author Administrator
 *
 */
public class OutOfBoundaryPath {
	//可以用递归来解决，每次递归都减少一步，知道最后一步返回
	//时间太长，没通过验证
	/**
	 * 
	 * @param m m行
	 * @param n  n列
	 * @param N  N步数
	 * @param i  i坐标
	 * @param j  j坐标
	 * @return
	 */
	 public int findPaths(int m, int n, int N, int i, int j) {
		 if(i>=m|| j>=n ||i<0|| j<0){
			 return 0;
		 }
		 int nums=1;
	     for(int k=0;k<9;k++){
			 nums*=10;
		 }
	      nums+=7;
	        long steps=0;
	       steps= findPath(m, n, N, i, j, steps);
	       
	        return (int)steps%(nums);
	    }
	 
	  public long findPath(int m, int n, int N, int i, int j,long steps){
		  int nums=1000000007;
		  if(N<=i&&N<=j&&N<m-i&&N<n-j){
			  return steps%nums;
		  }
		  if((i<0||j<0||i>=m||j>=n)&&N>=0)
			  return (steps+1)%nums;
		  steps=findPath(m,n,N-1,i,j-1,steps);
		  steps=findPath(m,n,N-1,i,j+1,steps);
		  steps=findPath(m,n,N-1,i-1,j,steps);
		  steps=findPath(m,n,N-1,i+1,j,steps);
		 return steps%nums;
	  }
	  
	  /**
	   * 使用动态规划来解决该问题，dp[N][i][j]表示，在N步内从i，j格走出去的方法数
	   * dp[N][i][j]=dp[N-1][i-1][j]+dp[N-1][i+1][j]+dp[N-1][i][j-1]+dp[N-1][i][j+1]
	   * @param m
	   * @param n
	   * @param N
	   * @param i
	   * @param j
	   * @return
	   */
	  public int findPaths2(int m, int n, int N, int i, int j) {
		  long[][][] dp=new long[51][50][50];
		  for(int ns=1;ns<=N;ns++){
			  for(int mi=0;mi<m;mi++)
				  for(int nj=0;nj<n;nj++){
					  dp[ns][mi][nj]=((mi==0?1:dp[ns-1][mi-1][nj])+//当mi=0时，往上移动一步即可出去，否则取dp[ns-1][mi-1][nj]
							                  (mi==m-1?1:dp[ns-1][mi+1][nj])+
							                  (nj==0?1:dp[ns-1][mi][nj-1])+
							                  (nj==n-1?1:dp[ns-1][mi][nj+1]))%1000000007;
							                  
				  }
		  }
		  return (int) (dp[N][i][j]);
	  }
	  public int findPaths3(int m, int n, int N, int i, int j) {
	        if (N <= 0) return 0;
	        
	        final int MOD = 1000000007;
	        int[][] count = new int[m][n];
	        count[i][j] = 1;
	        int result = 0;
	        
	        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	        
	        for (int step = 0; step < N; step++) {
	            int[][] temp = new int[m][n];
	            for (int r = 0; r < m; r++) {
	                for (int c = 0; c < n; c++) {
	                    for (int[] d : dirs) {
	                        int nr = r + d[0];
	                        int nc = c + d[1];
	                      //temp[r][c]代表小球在方格子中临时的位置和路径数，如果下一步移动出去的话，就计入result
	                        //count[r][c]代表上一步移动到r，c处的路径数
	                        if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
	                            result = (result + count[r][c]) % MOD;
	                        }
	                        else {
	                            temp[nr][nc] = (temp[nr][nc] + count[r][c]) % MOD;//
	                        }
	                    }
	                }
	            }
	            count = temp;
	        }
	        
	        return result;
	    }

	  public static void main(String[] args) {
//		System.out.println(new OutOfBoundaryPath().findPaths(36, 5, 50, 15, 3));
		System.out.println(new OutOfBoundaryPath().findPaths3(2, 2, 2, 0, 0));
	}
}
