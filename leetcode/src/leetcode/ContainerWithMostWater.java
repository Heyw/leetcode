package leetcode;

import java.util.Map;
import java.util.TreeMap;

public class ContainerWithMostWater {
	/**
	 * 给定一组非负数组[a0,a1,a2,...an],其中每个元素的位置和值代表一个二维坐标(i,ai),其到x轴的垂直线段，与其他坐标(j,aj)到x轴的垂直线段，
	 * 与x轴本身构成一个容器，求该容器最多能容纳多少水，也就是求(j-i)*min(ai,aj)的最大值
	 * 如果一开始j-i=n-0，如果左边小于右边，那么左移，因为i，与j-1,j-2,j-3等等组成的面积肯定小于lef与right组成的面积
	 * 证明如下：ai<aj,则result=(j-i)*ai
	 *  若a(j-m)>ai,则result>(j-m-i)*ai
	 *  若a(j-m)<ai,则result>(j-m-i)*ai>(j-m-i)*a(j-m)
	 * @param arr
	 * @return
	 */
       public int solution(int[] height){
    	  int left=0,right=height.length-1;
    	  int result=0;
    	  int max=0;
    	  while(left>=0&&right<height.length&&left<right){
    		  result=(right-left)*Math.min(height[right],height[left]);
    		  if(result>max)
    			  max=result;
    		//如果左边小于右边，那么左移，因为left，与right-1,right-2,right-3等等组成的面积肯定小于lef与right组成的面积
    		  if(height[left]< height[right]){
    			  left++;
    		  }else{
    			  right--;
    		  }
    	  }
    	   return max;
       }
       
       public static void main(String[] args) {
		int[] arr={1,3,2,5,25,24,5};
		System.out.println(new ContainerWithMostWater().solution(arr));
	}
}
