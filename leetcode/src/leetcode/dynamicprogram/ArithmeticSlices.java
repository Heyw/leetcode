package leetcode.dynamicprogram;

/**
 * ��������Ǹ���һ�������жϣ����������ж��ٸ��Ȳ�����,�Ȳ����г�������Ϊ3
 * ����[1,2,3,4]��[1,2,3],[2,3,4]��[1,2,3,4]
 * ����Ϊ4�ĵȲ����а��������и�����(len-2)(len-1)/2=(4-2)*(4-1)/2=3;
 * dp[len]-dp[len-1]=(len-2)*(len-1)/2-(len-2)*(len-3)/2=len-2;
 * dp[len]=dp[len-1]+len-2;
 * @author Administrator
 *
 */
public class ArithmeticSlices {
	 public int numberOfArithmeticSlices(int[] A) {
		    int num=0;
	        int index=0;
	        while(index+2<A.length){
	        	   int dif=A[index+1]-A[index];
	        	   int curr=0;
	        	   index++;
	        	   while(index+1<A.length&&dif==A[index+1]-A[index]){
	        		 curr+=1;
	        		 num+=curr;
	        		 index++;
	        	   }
//	        	   if(j-index>=2){
//	        		   num+=(j-index-1)*(j-index)/2;
//	        	   }
//	        		index=j;   
	        }
	        return num;
	    }
	 public static void main(String[] args) {
		System.out.println( new ArithmeticSlices().numberOfArithmeticSlices(new int[]{1,2,3,4,6,8}));
	}
}
