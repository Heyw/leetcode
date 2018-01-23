package leetcode.binarysearch;

import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * ����leetcode330���⣬���������һ��������������������еĳ��ȣ�
 * ����[10, 9, 2, 5, 3, 7, 101, 18],����������г���Ϊ[2��3��7��101]��������4
 * [10, 9, 2, 5, 3, 7, 101, 18��19],��ô����������г���Ϊ[2,3,7,18,19]������Ϊ5
 * @author Administrator
 *
 */
public class LongestIncreasingSubsequence330 {
	/**
	 * ���ö��ַ�����������⣬�ڱ�������Ĺ����У���ǰλ��Ϊi���ҵ��պ�С��nums[i]��nums[j]����ȡj�������򳤶ȣ�Ȼ��ʹ���1
	 * @param nums
	 * @return
	 */
	 public int lengthOfLIS(int[] nums) {
		     if(nums==null||nums.length==0) return 0;
	        TreeMap<Integer ,Integer> map=new TreeMap<>();
	        map.put(nums[0], 1);
	        int max=1;
	        for(int i=1;i<nums.length;i++){
	        	Integer low = map.floorKey(nums[i]);
	        	Integer high = map.higherKey(nums[i]);
	        	if(low!=null){
	        		map.put(nums[i],low==nums[i]? map.get(low):map.get(low)+1);
	        	}else{
	        		map.put(nums[i], 1);
	        	}
	        	if(high!=null&&map.get(high)<=map.get(nums[i])) map.remove(high);//�������nums[i]�ĳ���С��num[i]�ĳ��ȣ���ô�����Ƴ�
	        	max=Math.max(max, map.get(nums[i]));
	        }
	        return max;
	    }
	 /**
	  * 
	  * @param nums
	  * @return
	  */
	 public int lengthOfLISByBS(int[] nums) {
	        int n = nums.length;
	        if(n == 0) return 0;
	        int[] tail = new int[n];
	        tail[0] = nums[0];
	        int len = 1;
	        for(int i = 1; i < n; ++i) {
	            int pos = searchInsertPos(tail, 0, len-1, nums[i]);
	            tail[pos] = nums[i];
	            if(len < pos+1) len = pos+1;
	        }
	        return len;
	    }
	    /**
	     * �ҵ�С�ڵ���x�����λ��
	     * @param A
	     * @param left
	     * @param right
	     * @param x
	     * @return
	     */
	    private int searchInsertPos(int[] A, int left, int right, int x) {
	        while(left <= right) {
	            int mid = (left + right) / 2;
	            if(A[mid] == x) return mid;
	            else if(A[mid] < x) left = mid+1;
	            else right = mid-1;
	        }
	        return left;
	    }

	 public static void main(String[] args) {
		int[] nums={10, 9, 2, 5, 3, 7, 101,1,2,3,4,5, 18,19};
//		nums=new int[]{2,2};
		System.out.println(new LongestIncreasingSubsequence330().lengthOfLISByBS(nums));
	}
}
