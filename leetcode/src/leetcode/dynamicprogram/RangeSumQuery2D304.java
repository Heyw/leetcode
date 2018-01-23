package leetcode.dynamicprogram;

/**
 * leetcode304�⣬ָ���Ǹ���һ����ά���飬Ȼ��ͨ��ָ����������Ϻ�����λ�ã�����������Χ�ڵ�Ԫ��֮��
 * ���磺
		 * Given matrix = [
		  [3, 0, 1, 4, 2],
		  [5, 6, 3, 2, 1],
		  [1, 2, 0, 1, 5],
		  [4, 1, 0, 1, 7],
		  [1, 0, 3, 0, 5]
		]
		
		sumRegion(2, 1, 4, 3) -> 8������(2,1)�Ǿ������ϵ�λ�ã���ʾ2��1�У�(4,3)�Ǿ������µ�λ�� 8=2+0+1+1+0+1+0+3+0
		sumRegion(1, 1, 2, 2) -> 11
		sumRegion(1, 2, 2, 4) -> 12
		
		����˼·����̬�滮,��һ���������о���Ԫ��a[i][j]����ԭ����matrix��[i,j]��[0,0]�涨���Ӿ���֮��
		a[i][j]=a[i-1][j]+rowSum
		����rowSum��ʾi�дӵ�һ�е���ǰ��֮��
 * @author Administrator
 *
 */
public class RangeSumQuery2D304 {
	public int getResult(int[][] matrix,int row1, int col1, int row2, int col2){
		NumMatrix matr=new NumMatrix();
		matr.iniMatrixSum(matrix);
		return matr.sumRegion(row1, col1, row2, col2);
	}
	class NumMatrix {
         int[][] matrixSum=null;
	    public void iniMatrixSum(int[][] matrix) {
	    	if(matrix==null||matrix[0]==null)
	    		return;
	    	matrixSum=new int[matrix.length][matrix[0].length];
	    	for(int i=0;i<matrix.length;i++){
	    		int rowSum=0;
	    		for(int j=0;j<matrix[0].length;j++){
	    			rowSum+=matrix[i][j];
	    			matrixSum[i][j]=(i>0?matrixSum[i-1][j]:0)+rowSum;
	    		}
	    	}
	    }
	    
	    public int sumRegion(int row1, int col1, int row2, int col2) {
	        if(matrixSum==null)
	        	return 0;
	        return matrixSum[row2][col2]-matrixSum[row1-1][col2]-matrixSum[row2][col1-1]+matrixSum[row1-1][col1-1];
	    }
	}
	public static void main(String[] args) {
		int[][] matrix={ {3, 0, 1, 4, 2},
				  {5, 6, 3, 2, 1},
				  {1, 2, 0, 1, 5},
				  {4, 1, 0, 1, 7},
				  {1, 0, 3, 0, 5}
};
//		RangeSumQuery2D304.NumMatrix matri=new RangeSumQuery2D304().new NumMatrix(matrix);
		System.out.println(new RangeSumQuery2D304().getResult(matrix,2, 1, 4, 3));
		System.out.println(new RangeSumQuery2D304().getResult(matrix,1, 1, 2, 2));
		System.out.println(new RangeSumQuery2D304().getResult(matrix,1, 2, 2, 4));
	}
}
