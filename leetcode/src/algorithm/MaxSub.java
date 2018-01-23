package algorithm;

/**
 * 本类是用来求序列最大子序列的算法
 * 给定整数 A1,A2,.......AN,求某个区间之和的最大值，为了方便起见，如果都为负数，则最大值为零
 * @author Administrator
 *
 */
public class MaxSub {
	
	/**
	 * O(N^3)时间复杂度算法，最简单
	 * @param a
	 * @return
	 */
       public int maxSub1(int[] a){
    	   int maxSubSum=0;
    	   for(int i=0;i<a.length;i++){
    		   for(int j=i;j<a.length;j++){
    			   int thisSubSum=0;
    			   for(int k=i;k<=j;k++){
    				   thisSubSum+=a[k];
    				   if(thisSubSum>maxSubSum)
    					   maxSubSum=thisSubSum;
    			   }
    		   }
    	   }
    	   return maxSubSum;
       }
       
       public int maxSub2(int[] a){
    	   int maxSubSum=0;
    	   for(int i=0;i<a.length;i++){
    		   int thisSubSum=0;
    		   for(int j=i;j<a.length;j++){
    			   thisSubSum+=a[j];
    			   if(thisSubSum>maxSubSum)
    				   maxSubSum=thisSubSum;
    		   }
    	   }
    	   return maxSubSum;
       }
       
       /**
        * 本算法采用分治算法，因为最大子序列要么出现在序列左半部，要么出现在序列右半部，要么出现在横跨这两个部分的中间
        * @param a
        * @param left
        * @param right
        * @return
        */
       public int maxSub3(int[] a,int left,int right){
    	   if(left==right){
    		   if(a[left]>0)
    			   return a[left];
    		   else
    			   return 0;
    	   }
    		 
    		   int mid=(left+right)/2;
    		   int leftMaxSum=maxSub3(a,left,mid);
    		   int rightMaxSum=maxSub3(a,mid+1,right);
    		   int leftBorderSum=0,maxLeftBorderSum=0;
    		   for(int i=mid;i>=left;i--){
    			   leftBorderSum+=a[i];
    			   if(leftBorderSum>maxLeftBorderSum)
    				   maxLeftBorderSum=leftBorderSum;
    		   }
    		   int rightBorderSum=0,maxRightBorderSum=0;
    		   for(int i=mid+1;i<=right;i++){
    			   rightBorderSum+=a[i];
    			   if(rightBorderSum>maxRightBorderSum)
    				   maxRightBorderSum=rightBorderSum;
    		   }
    		   
    		   int maxSubSum=leftMaxSum;
    		   if(rightMaxSum>maxSubSum)
    			   maxSubSum=rightMaxSum;
    		   if(maxLeftBorderSum+maxRightBorderSum>maxSubSum)
    			   maxSubSum=maxLeftBorderSum+maxRightBorderSum;
    		   return maxSubSum;
    	   
    		   
       }
       
       public int maxSub4(int[] a){
    	   int thisSubSum=0;
    	   int maxSubSum=0;
    	   for(int i=0;i<a.length;i++){
    		   thisSubSum+=a[i];
    		   if(thisSubSum>maxSubSum)
    			   maxSubSum=thisSubSum;
    		   else if(thisSubSum<0)
    			   thisSubSum=0;
    	   }
    	   return maxSubSum;
       }
       public static void main(String[] args) {
		int [] a={4,-3,5,-2,-1,2,6,-1};
		MaxSub maxSub = new MaxSub();
		System.out.println(maxSub.maxSub1(a));
		System.out.println(maxSub.maxSub2(a));
		System.out.println(maxSub.maxSub3(a, 0, a.length-1));
		System.out.println(maxSub.maxSub4(a));
	}
}
