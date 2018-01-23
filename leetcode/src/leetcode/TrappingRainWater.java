package leetcode;

/**
 * ����һ��Ǹ������飬�����ڶ�ά�����Ͽ�ȶ���һ���߶�Ϊÿ��Ԫ��ֵ�����������x����ɵ�ͼ�������ɶ�����ˮ
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
		 * �ҳ���������[left,right]������Ԫ�ص�λ��
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
		 *�ⷨ˼·��һ��������ߺ��ұ߶���һ�����ֵ�����������ֵС���ұ����ֵ����ôˮ����״��������ֻ�ܱ���������ֵ��С������
		 *ͬʱ����ָ�����ƶ�ʱ�������һ��Ԫ�ش��ڵ�ǰ���ֵ����ô�����Ӳ���������
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
