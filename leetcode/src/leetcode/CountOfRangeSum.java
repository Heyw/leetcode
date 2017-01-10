package leetcode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

/**
 * 给定一个整型数组，并给出上届和下届
 * 求出该数组中区间和位于上届下届之间的个数
 * 比如nums=[-2,5,-1],上届为2，下届为-2
 * 那么有三个这样的区间[0,0]=-2,[2,2]=-1,[0,2]=-2+5+-1=2
 * @author Administrator
 *
 */
public class CountOfRangeSum {
      /**
       * 解法一:用归并思想来解决
       */
	public void countNum(int[] num,int low,int up,int left,int right,List<String>list){
		int sum=0;
		for(int i=low;i<=up;i++){
			sum+=num[i];
		}
		if(sum<=right&&sum>=left){
			list.add(low+":"+up);
		}
		if(up<num.length-1){//如果右指针没有达到边界，那么使得右指针不断向右移动
			 countNum(num,low,++up,left,right,list);
			 return;
		}
		if(low<num.length-1){//如果右指针已经到达数组边界，那么让左指针+1,并赋值给右指针，重新开始递归
		  	countNum(num,++low,low,left,right,list);
		  	return ;
		}
		if(low==num.length-1 && up==num.length-1)
			return ;
	}
	
	/**
	 * 用线性思想来解决；sum[i]等于数组前i+1项之和，如果存在j，使得sum[i]-sum[j]位于区间之内即 left<=sum[i]-sum[j]<=right，那么就存在一个区间[j+1,i]满足条件
	 * 这时可以利用TreeMap的自然升序，对于每个i，我们只需要找到TreeMap中有多少键值存在于sum[i]-right和sum[i]-left之间
	 * @param args
	 */
	public int countNumForTreeMap(int[] num,int left,int right){
		long[] sum=new long[num.length];
		sum[0]=num[0];
		for(int i=1;i<num.length;i++){
			sum[i]=sum[i-1]+num[i];
		}
		TreeMap<Long,Integer>treeMap=new TreeMap<>();
		int count=0;
		for(int i=0;i<sum.length;i++){
			if(sum[i]<=right&&sum[i]>=left)
				count++;
			//subMap是求出键值位于指定范围内的map,c代表该sum的个数
			//比如s1和s3的值相同的，那么对于s5来[2,5]和[4:5]区间都是存在的，但是TreeMap不能包含重复的key，故用value来表示重复的sum的个数
			for( Integer c:treeMap.subMap(sum[i]-right, true, sum[i]-left, true).values()){
				count=count+c;
			}
			if(treeMap.containsKey(sum[i]))
			treeMap.put(sum[i], treeMap.get(sum[i])+1);
			else
				treeMap.put(sum[i], 1);
		}
		return count;
	}
	/**
	 * 该解法:
	 * (1)计算sum[i],(2)将sums进行排序，得到排序后数组copy(3)遍历每个sum[i],找到copy数组第一个j满足copy[j]-sum[i]>upper,找到第一个k满足copy[k]-sum[i]>=low,那么对sum[i]来说就有j-k个该区间
	    该解法在遍历copy数组是无法区分sum中的元素是否等于当前的copy数组的元素，故难以实现
	 * @param nums
	 * @param left
	 * @param high
	 * @return
	 */
	@Deprecated
	public int countAftersort(int nums[],int low,int high){
		long[] sum=new long[nums.length];
		sum[0]=nums[0];
		for(int i=1;i<nums.length;i++)
			sum[i]=sum[i-1]+nums[i];
		long[] copy=new long[sum.length];
		System.arraycopy(sum, 0, copy, 0, sum.length);
		mergSort(copy, 0, copy.length-1, new long[sum.length]);
		int count=0;
		for(int i=0;i<sum.length;i++){
			for(int j=0;j<copy.length;j++){
				if(copy[j]-sum[i]<=high)
					return count;
			}
		}
		return count;
	}
	
	/**
	 * 归并排序
	 * @param sums
	 * @param low
	 * @param high
	 */
	private  void mergSort(long[] sums,int low,int high,long[] temp){
		if(low>high)
			throw new IllegalArgumentException();
		int mid=(high+low)/2;
		if(high-low>2){
			mergSort(sums, low, mid,temp);
			mergSort(sums,mid+1,high,temp);
		}
		int lIndex=low,tIndex=low,rIndex=mid+1;
		while(lIndex<=mid&&rIndex<=high){
			if(sums[lIndex]<sums[rIndex])
				temp[tIndex++]=sums[lIndex++];
			else
				temp[tIndex++]=sums[rIndex++];
		}
		while(lIndex<=mid){
			temp[tIndex++]=sums[lIndex++];
		}
		while(rIndex<=high){
			temp[tIndex++]=sums[rIndex++];
		}
		System.arraycopy(temp, low, sums, low, high-low+1);
	}
	
	public int countNum(int[] nums,int lower,int upper){
		long[] sums=new long[nums.length];
		sums[0]=nums[0];
		for(int i=1;i<nums.length;i++ ){
			sums[i]=sums[i-1]+nums[i];
		}
		if(sums.length>1)
		 return countNumWhileMergeSort(sums, 0, sums.length-1, lower, upper);
		else{
				return sums[0]<=upper&&sums[0]>=lower?1:0;
		}
	}
	/**
	 * 该解法主要是在merge过程中获取count数目:在数组merge过程时，数组在[low,mid]，[mid,high]中已是排好序了，
	 * 这时对于左边的每个i，从右边找出两个指数j和k:
	 * j是第一个满足sum[j]-sum[i]>high的指数,k是第一个满足sum[k]-sum[i]<low的指数，那么对于i来说，此时有j-k个这样的区间
	 * 接下来将遍历右边数组，将右边所有小于sum[i]的数组放到cache数组中,以完成merge
	 * @param sums
	 * @param low
	 * @param high
	 * @return
	 */
	public int countNumWhileMergeSort(long[] sums,int start,int end,int lower,int upper){
		if(end-start==0)//为了保证数组判断自身是否符合要求
			return sums[end]<=upper&&sums[end]>=lower?1:0;
//        if(end-start==1){
//        	if(sums[end]<sums[start]){
//        		long temp=sums[start];
//        		sums[start]=sums[end];
//        		sums[end]=temp;
//        	}
//        	if(sums[end]-sums[start]>=lower&& sums[end]-sums[start]<=upper)
//        		return 1;
//        }
		int mid=(end+start)/2;//向下取整
		int count=countNumWhileMergeSort(sums, start, mid,lower,upper)+countNumWhileMergeSort(sums, mid+1, end,lower,upper);
		int t=mid+1;
		long[] cach=new long[end-start+1];
		int j=mid+1,k=mid+1;
		for(int i=start,r=0;i<=mid;i++,r++){
			/* 满足sum-sum[i]>=low的右边数组：不满足，不满足，满足，满足，满足(对于排序数组，从第一个开始满足，那么后面数组元素肯定满足)
			 * 满足sum-sum[i]<=upper的右边数组:满足，满足，满足，满足(sum-sum[i]<low肯定满足sum-sum[i]<upper),不满足，不满足
			 * 则满足两者之间的为4-2
			 *  假设从第一个满足sum-sum[i]>=low的指针为mid+k到第一个满足sum-sum[k]<=upper的指针为mid+j,
			 *  那么满足两者之间的元素共有mid+j-mid+k+1=j-k+1个
			 */
			//计算出mid+1到end之间共有k个sum满足sum-sum[i]<low,那么从mid+k+1个开始就满足sum-sum[i]>=low
			 while (k <=end && sums[k] - sums[i] < lower) k++;
			 //计算出mid+1到end之间共有j个sum满足sum-sum[i]<=upper,那么满足两者之间的元素共有mid+j-mid-k-1+1=j-k个
		      while(j<=end&&sums[j]-sums[i]<=upper) j++;
		      while(t<=end&&sums[t]<sums[i]) //这部分代码是为了完成merge，对于右边数组每个小于sum[i]的元素都应该放在sum[i]的前面
		    	  cach[r++]=sums[t++];
		      cach[r]=sums[i];
		      count+=j-k;
		}
			System.arraycopy(cach, 0, sums, start, t-start);//完成sort,t-start表示右边数组小于左边数组的个数，故应替换掉这些元素
		return count;
	}
	public static void main(String[] args) {
		int[] num={-2,5,-1,-3,4,1};
		int right=2;
		int left=-2;
		List<String> list=new ArrayList<>();
		new CountOfRangeSum().countNum(num, 0, 0, left, right, list);
		for(String str:list){
			System.out.println(str);
		}
		System.out.println("第二种方法求出结果为："+new CountOfRangeSum().countNumForTreeMap(num, left, right));
		System.out.println("第三种方法求出结果为："+new CountOfRangeSum().countNum(num, left, right));
	}
}
