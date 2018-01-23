package leetcode.dynamicprogram;

/**
 * leetcode304题，指的是给定一个二维数组，然后通过指定矩阵的左上和右下位置，求出在这个范围内的元素之和
 * 例如：
		 * Given matrix = [
		  [3, 0, 1, 4, 2],
		  [5, 6, 3, 2, 1],
		  [1, 2, 0, 1, 5],
		  [4, 1, 0, 1, 7],
		  [1, 0, 3, 0, 5]
		]
		
		sumRegion(2, 1, 4, 3) -> 8；其中(2,1)是矩阵左上的位置，表示2行1列，(4,3)是矩阵右下的位置 8=2+0+1+1+0+1+0+3+0
		sumRegion(1, 1, 2, 2) -> 11
		sumRegion(1, 2, 2, 4) -> 12
		
		解题思路：动态规划,用一个矩阵，其中矩阵元素a[i][j]保存原矩阵matrix中[i,j]到[0,0]规定的子矩阵之和
		a[i][j]=a[i-1][j]+rowSum
		其中rowSum表示i行从第一列到当前列之和
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
