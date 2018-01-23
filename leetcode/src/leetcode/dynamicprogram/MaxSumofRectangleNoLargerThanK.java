package leetcode.dynamicprogram;

import java.util.TreeSet;

/**
 * ��������Ǹ���һ����ά����Ȼ������þ�����Ӿ�����ڲ�����k������µ����ֵ��
 * Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2

The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).
 * @author Administrator
 *
 */
public class MaxSumofRectangleNoLargerThanK {
	/**
	 * �����ⷨ���Ǳ��������Ӿ���Ȼ���ҳ����в�����k�����ֵ���������ֽⷨʱ�临�Ӷ�̫���ˣ�Ϊ�˽����Ż�����ʹ�ö��ֲ��ҷ���
	 * 
	 * ����left����right������col>right>=left>=0������left���Ǿ������߽磬right���Ǿ�����ұ߽磬colָ���Ǿ�����п�
	 * Ȼ�������left��right֮�����ͣ�Ϊ�˼��ټ����������Խ���ά���鿴��һ����һά���飬Ȼ������i�е���������left��right֮��ĺͣ�
	 * ����sum[i]����ô��i�к͵�j����left��right֮��ĺ;͵���sum[i]-sum[j]������Ҫ���sum[i]-sum[j]<=k�����Ԫ�أ���ô������Ҫ�ҳ�
	 * sum[j]>=sum[i]-k����СԪ��,Ϊ�˿��ٲ���������Ԫ�أ����ǿ��Խ���left��right����sum[i]��������һ���������ϣ�
	 * �����Ϳ������ö������Ķ��ֲ������ԣ���ȻΪ�˱�֤���ҵ��ȶ��Ϳ����ú�������棬TreeSet
	 * @param matrix
	 * @param k
	 * @return
	 */
		public int maxSumSubmatrix(int[][] matrix, int k) {
		        if(matrix==null|| matrix[0]==null)
		        	return 0;
		        int col=matrix[0].length;
		        int row=matrix.length;
		        int max=Integer.MIN_VALUE;
		        for(int left=0;left<col;left++){
		        	int[]sum=new int[row];
		        	for(int right=left;right<col;right++){//�ұ߽�
		        		  TreeSet<Integer> tre=new TreeSet<>();
			        	  tre.add(0);//��Ϊ�˷�ֹ��һ�з���Ҫ�󣬶�û�ܽ���һ����max���Ƚ�
			        	  int curSum=0;//curSum������֮�ͣ�����cursum֮��Ĳ�ֵ���Ǿ����֮��
		        		for(int up=0;up<row;up++){//up�����ϱ߽�
		        			sum[up]+=matrix[up][right];//sum[i]�����ڴ�col��[left,right֮���]��i��֮��
		        		  curSum+=sum[up];
		        		  Integer p=tre.ceiling(curSum-k);
		        		  if(p!=null){
		        			  max=Math.max(max, curSum-p);
		        		  }
		        		  tre.add(curSum);
		        	  }
		        	}
		        }
		        return max;
		    }
		
		public static void main(String[] args) {
			int[][] num=new int[][]{{1,0,1},{0,-2,3}};
			System.out.println(new MaxSumofRectangleNoLargerThanK().maxSumSubmatrix(num, 2));
		}
}
