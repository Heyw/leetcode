package leetcode.dynamicprogram;

import java.util.ArrayList;
import java.util.List;

/**
 * 给我们两对升序排列的数组，进行组合，然后返回和最小的k个数组对
 * 例如下:
Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3
Return: [1,2],[1,4],[1,6]
The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * @author Administrator
 *
 */
public class FindKPairsWithSmallestSum {
	public List<int[]> kSmallestPairs(int[] nums1,int[] nums2,int k){
		 if(nums1==null||nums1.length==0||nums2==null||nums2.length==0) return null;
		List<int[]> arraylist=new ArrayList<>();	
		int[] indexArr=new int[nums1.length];//保存nums1各个元素保存的对应nums2最小可选择值的序号
		while(arraylist.size()<k){
			int min=Integer.MAX_VALUE;
			int goal=0;
			for(int i=0;i<nums1.length;i++){
				if(indexArr[i]>=nums2.length) continue;
				int temp=nums1[i]+nums2[indexArr[i]];
				if(temp<min){
					min=temp;
					goal=i;
				}
			}
			if(indexArr[goal]>=nums2.length)
				break;
			arraylist.add(new int[]{nums1[goal],nums2[indexArr[goal]]});
			indexArr[goal]++;
		}
		return arraylist;
	}
	public static void main(String[] args) {
		int[] nums1=new int[]{1,1,2};
		int[] nums2=new int[]{1,2,3};
		List<int[]> arrList=new FindKPairsWithSmallestSum().kSmallestPairs(nums1, nums2, 10);
		for(int[]arr:arrList){
			for(int a:arr){
				System.out.print(a+"  ");
			}
			System.out.println(arr);
		}
	}
}
