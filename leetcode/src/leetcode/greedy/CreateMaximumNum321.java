package leetcode.greedy;
/**
 * 题目分析：
          给定两个长度分别为m和n的数组，数组元素为0-9，每个数组元素代表一个数字。
  从这两个数组中选出一些数字，组成一个数组，是这个数组中的数尽可能大，其长度k <= m + n。
   要求数组中选出的元素的相对顺序与原数组保持一致。最终返回一个包含k个数字的数组。
解题思路：
  1）分别从nums1(长度为m）和nums2（长度为n）中挑选出i（max(0, k - n) <= i <= min(m, k) 和k-i个数，
     在保持挑选数组的元素相对顺序不变的情况下，使选出的子数组最大化，主要利用贪心算法进行选取；
  2）在保持元素相对位置不变的前提下，将数组nums1与nums2合并，使合并的数组最大化。
 * @author Administrator
 *
 */
public class CreateMaximumNum321 {
    /**
     * 从数组nums中找出k个成员组成长度为k的最大和子数组，同时需要保持子数组元素在原数组中的顺序不变    
     * @param nums
     * @param k
     * @return
     */
	private int[] getMaxArray(int[] nums,int k){
        	int[] res=new int[k];
        	int len=0;
        	for(int i=0;i<nums.length;i++){
        		//nums.length-i>k-len表示数组剩下的成员个数大于还需要的元素个数k-i，同时当前i所在元素大于之前赋值的元素
        		//那么不断向前直到使得当前i所在元素是最大的
        		while(len>0&&nums.length-i>k-len&&res[len-1]<nums[i])
        			len--;
        		if(len<k)
        			res[len++]=nums[i];
        	}
        	return res;
        }
	/**
	 * 从第一个元素开始比较两个数组的大小，如果在pos1，pos2处nums1[pos1]>nums2[pos2],返回true
	 * @param nums1
	 * @param pos1
	 * @param nums2
	 * @param pos2
	 * @return
	 */
	  private boolean comparetArray(int[] nums1,int pos1,int[] nums2,int pos2){
		  for(;pos1<nums1.length&&pos2<nums2.length;pos1++,pos2++){
			  if(nums1[pos1]>nums2[pos2])
				  return true;
			  if(nums1[pos1]<nums2[pos2])
				  return false;
		  }
		  return pos1!=nums1.length;
	  }
	  
	  public int[] maxNumber(int[] nums1, int[] nums2, int k) {
	        int[] res=new int[k];
	        for(int i=Math.max(k-nums2.length, 0);i<=Math.min(nums1.length,k);i++){
	        	int[] res1=getMaxArray(nums1, i);
	        	int[] res2=getMaxArray(nums2, k-i);
	        	int[] temp=new int[k];
	        	int pos1=0,pos2=0,pos=0;
	        	while(pos1<res1.length||pos2<res2.length){
	        		//此处用compareArray当作条件判断，是因为如果pos1=res1.length，
	        		//那么就会根据compareArray方法自动判断不成立，无须再写更多条件语句
	        		temp[pos++]=comparetArray(res1, pos1, res2, pos2)?res1[pos1++]:res2[pos2++];
	        	}
	        	if(!comparetArray(res, 0, temp, 0))
	        		res=temp;
	        }
	        return res;
	    }
	  public static void main(String[] args) {
		int[] nums1={6,7};
		int[] nums2={6,0,4};
		int[] res=new CreateMaximumNum321().maxNumber(nums1, nums2, 5);
		for(int i:res){
			System.out.println(i);
		}
	}
}
