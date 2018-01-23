package leetcode.dynamicprogram;

/**
 * �ƶ����ӻ�ȡ���������⣬һ���Ƴ�k��������ͬ��ɫ�����ӿ��Ի��k^2������������ɫ������������ʾ
 * ����Example 1: 
Input: 
[1, 3, 2, 2, 2, 3, 4, 3, 1] 
Output: 
23 
Explanation: 
[1, 3, 2, 2, 2, 3, 4, 3, 1] 
��-> [1, 3, 3, 4, 3, 1] (3*3=9 points) 
��-> [1, 3, 3, 3, 1] (1*1=1 points) 
��-> [1, 1] (3*3=9 points) 
��-> [] (2*2=4 points)
�ܵ�pointsΪ23�����ponits������
 * @author Administrator
 *
 */
public class RemoveBoxes {
    /**
     * ��������boxes[n]��˵���������ң�����ÿһi����������֣���ֻ������ѡ��
     * 1 ���������Լ����������ͬ����ɫ�Ƴ�����õ�����i+1��n-1 ��֮��������֮�ͣ�
     * 2.��i+1��ʼѰ�ң��ҵ�ӵ����ɫ��ͬ��m��Ȼ���m��ʼ����1�Ĳ���
     * ����dp[i][j][k]:����i������������߽磬j�����������ұ߽磬k��ʾλ��С��i���Һ�boxes[i]��С����ɫ����ͬ���������Ӹ���
     * ��ô����dp[i][j][k]�����Ƴ�boxes��i+1��j���������Լ�i�����boxes[i]��ͬ��ɫ�����ӵ�������
     * ���ǰ��������ѡ��
     * 1.����Ƴ�boxes[i]�����Լ������ͬ����ɫ������,��dp[i][j][k]=(1+k)^2+dp[i+1][j][0];
     * 2.�����i+1���ұ���Ѱ��mʹ��boxes[m]=boxes[i]��Ȼ������Ƴ�i+1��m-1֮��������dp[i+1][m-1][0]��
     * ��m��j֮��������dp[m][j][k+1]֮��,k+1����Ϊi��m��ߣ�С��i��λ����k����ͬ��ɫ����ôm��߾���k+1����ɫ��ͬ������
     * �Ƚ������ߴ�С��ȥ����Ϊ��
     * 
     * ע:�������������ⶼ���Թ���Ϊ���������Ե���������(i,j,k)����0<=i,j,k<length��Ȼ��ͨ��һ����λ����n*n*n���㹻�䵱�����м����ݵ�
     * @param boxes
     * @return
     */
	public int removeBoxes(int[] boxes){
		int n=boxes.length;
		int[][][] dp=new int[n][n][n];
    	int max= getMaxPointers(boxes,dp,0,boxes.length-1,0);
    	 return max;
      }

	private int getMaxPointers(int[] boxes, int[][][] dp, int i, int j, int k) {
		   if(i>j) return 0;//��Ϊi����jʱ������������û�����ӵ�
		   if(dp[i][j][k]>0) return dp[i][j][k];
		   int res=(k+1)*(k+1)+getMaxPointers(boxes,dp,i+1,j,0);//��һ�ַ�ʽ
		   for(int m=i+1;m<=j;m++){
			   if(boxes[m]==boxes[i]){
				   int second=getMaxPointers(boxes,dp,i+1,m-1,0)+getMaxPointers(boxes,dp,m,j,k+1);
				   res=Math.max(res, second);
			   }
		   }
		   dp[i][j][k]=res;
		   return res;
	}
	public static void main(String[] args) {
		int[] boxes={1, 3, 2, 2, 2, 3, 4, 3, 1};
		System.out.println(new RemoveBoxes().removeBoxes(boxes));
	}
}
