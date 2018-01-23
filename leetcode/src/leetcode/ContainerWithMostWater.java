package leetcode;

import java.util.Map;
import java.util.TreeMap;

public class ContainerWithMostWater {
	/**
	 * ����һ��Ǹ�����[a0,a1,a2,...an],����ÿ��Ԫ�ص�λ�ú�ֵ����һ����ά����(i,ai),�䵽x��Ĵ�ֱ�߶Σ�����������(j,aj)��x��Ĵ�ֱ�߶Σ�
	 * ��x�᱾����һ�����������������������ɶ���ˮ��Ҳ������(j-i)*min(ai,aj)�����ֵ
	 * ���һ��ʼj-i=n-0��������С���ұߣ���ô���ƣ���Ϊi����j-1,j-2,j-3�ȵ���ɵ�����϶�С��lef��right��ɵ����
	 * ֤�����£�ai<aj,��result=(j-i)*ai
	 *  ��a(j-m)>ai,��result>(j-m-i)*ai
	 *  ��a(j-m)<ai,��result>(j-m-i)*ai>(j-m-i)*a(j-m)
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
    		//������С���ұߣ���ô���ƣ���Ϊleft����right-1,right-2,right-3�ȵ���ɵ�����϶�С��lef��right��ɵ����
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
