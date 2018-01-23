package leetcode;

/**
 * 给定一组非负整数组，数组在二维数组上宽度都是一，高度为每个元素值，求该数组与x轴组成的图形能容纳多少雨水
 * @author Administrator
 *
 */
public class TrappingRainWater {
		public int trap(int[] height) {
		   int leftLoc=0,rightLoc=height.length-1;
		   int maxloc=findMaxHeight(height, leftLoc, rightLoc);
		   leftLoc=maxloc;rightLoc=maxloc;
		   int result=0;
		   while(leftLoc>0){
			  int  left=findMaxHeight(height, 0, leftLoc-1);
			  for(int i=left+1;i<=leftLoc-1;i++)
				  result+=height[left]-height[i];
			  leftLoc=left;
		   }
		   while(rightLoc<height.length-1){
				  int  right=findMaxHeight(height, rightLoc+1, height.length-1);
				  for(int i=rightLoc+1;i<=right-1;i++)
					  result+=height[right]-height[i];
				  rightLoc=right;
			   }   
			return result;
		    }
		/**
		 * 找出数组中在[left,right]中最大的元素的位置
		 * @param height
		 * @param left
		 * @param right
		 * @return
		 */
		public int findMaxHeight(int[] height,int left,int right){
			int max=0,index=left;
	        for(int i=left;i<=right;i++){
	        	if(height[i]>max){
	        		max=height[i];
	        		index=i;
	        	}
	        }
	        return index;
		}
		
		/**
		 *解法思路，一个数组左边和右边都有一个最大值，如果左边最大值小于右边最大值，那么水会向底处流，最多只能保留左边最大值大小的雨量
		 *同时左右指标在移动时，如果下一个元素大于当前最大值，那么是增加不了雨量的
		 * @param height
		 * @return
		 */
		public int trap2(int[] height){
			int left=0,right=height.length-1;
			int result=0;
			int maxLeft=0,maxRight=0;
			while(left<right){
				if(height[left]<=height[right]){
					if(height[left]>maxLeft)
						maxLeft=height[left];
					else
						result+=maxLeft-height[left];
					left++;
				}else{
					if(height[right]>maxRight)
						maxRight=height[right];
					else
						result+=maxRight-height[right];
					right--;
				}
			}
			return result;
		}
		public static void main(String[] args) {
		int[] height={0,1,0,2,1,0,1,3,2,1,2,1};
		 System.out.println(new TrappingRainWater().trap2(height));
		}
}
