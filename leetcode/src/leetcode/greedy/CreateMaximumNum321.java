package leetcode.greedy;
/**
 * ��Ŀ������
          �����������ȷֱ�Ϊm��n�����飬����Ԫ��Ϊ0-9��ÿ������Ԫ�ش���һ�����֡�
  ��������������ѡ��һЩ���֣����һ�����飬����������е��������ܴ��䳤��k <= m + n��
   Ҫ��������ѡ����Ԫ�ص����˳����ԭ���鱣��һ�¡����շ���һ������k�����ֵ����顣
����˼·��
  1���ֱ��nums1(����Ϊm����nums2������Ϊn������ѡ��i��max(0, k - n) <= i <= min(m, k) ��k-i������
     �ڱ�����ѡ�����Ԫ�����˳�򲻱������£�ʹѡ������������󻯣���Ҫ����̰���㷨����ѡȡ��
  2���ڱ���Ԫ�����λ�ò����ǰ���£�������nums1��nums2�ϲ���ʹ�ϲ���������󻯡�
 * @author Administrator
 *
 */
public class CreateMaximumNum321 {
    /**
     * ������nums���ҳ�k����Ա��ɳ���Ϊk�����������飬ͬʱ��Ҫ����������Ԫ����ԭ�����е�˳�򲻱�    
     * @param nums
     * @param k
     * @return
     */
	private int[] getMaxArray(int[] nums,int k){
        	int[] res=new int[k];
        	int len=0;
        	for(int i=0;i<nums.length;i++){
        		//nums.length-i>k-len��ʾ����ʣ�µĳ�Ա�������ڻ���Ҫ��Ԫ�ظ���k-i��ͬʱ��ǰi����Ԫ�ش���֮ǰ��ֵ��Ԫ��
        		//��ô������ǰֱ��ʹ�õ�ǰi����Ԫ��������
        		while(len>0&&nums.length-i>k-len&&res[len-1]<nums[i])
        			len--;
        		if(len<k)
        			res[len++]=nums[i];
        	}
        	return res;
        }
	/**
	 * �ӵ�һ��Ԫ�ؿ�ʼ�Ƚ���������Ĵ�С�������pos1��pos2��nums1[pos1]>nums2[pos2],����true
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
	        		//�˴���compareArray���������жϣ�����Ϊ���pos1=res1.length��
	        		//��ô�ͻ����compareArray�����Զ��жϲ�������������д�����������
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
