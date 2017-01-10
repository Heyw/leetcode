package leetcode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

/**
 * ����һ���������飬�������Ͻ���½�
 * ����������������λ���Ͻ��½�֮��ĸ���
 * ����nums=[-2,5,-1],�Ͻ�Ϊ2���½�Ϊ-2
 * ��ô����������������[0,0]=-2,[2,2]=-1,[0,2]=-2+5+-1=2
 * @author Administrator
 *
 */
public class CountOfRangeSum {
      /**
       * �ⷨһ:�ù鲢˼�������
       */
	public void countNum(int[] num,int low,int up,int left,int right,List<String>list){
		int sum=0;
		for(int i=low;i<=up;i++){
			sum+=num[i];
		}
		if(sum<=right&&sum>=left){
			list.add(low+":"+up);
		}
		if(up<num.length-1){//�����ָ��û�дﵽ�߽磬��ôʹ����ָ�벻�������ƶ�
			 countNum(num,low,++up,left,right,list);
			 return;
		}
		if(low<num.length-1){//�����ָ���Ѿ���������߽磬��ô����ָ��+1,����ֵ����ָ�룬���¿�ʼ�ݹ�
		  	countNum(num,++low,low,left,right,list);
		  	return ;
		}
		if(low==num.length-1 && up==num.length-1)
			return ;
	}
	
	/**
	 * ������˼���������sum[i]��������ǰi+1��֮�ͣ��������j��ʹ��sum[i]-sum[j]λ������֮�ڼ� left<=sum[i]-sum[j]<=right����ô�ʹ���һ������[j+1,i]��������
	 * ��ʱ��������TreeMap����Ȼ���򣬶���ÿ��i������ֻ��Ҫ�ҵ�TreeMap���ж��ټ�ֵ������sum[i]-right��sum[i]-left֮��
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
			//subMap�������ֵλ��ָ����Χ�ڵ�map,c�����sum�ĸ���
			//����s1��s3��ֵ��ͬ�ģ���ô����s5��[2,5]��[4:5]���䶼�Ǵ��ڵģ�����TreeMap���ܰ����ظ���key������value����ʾ�ظ���sum�ĸ���
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
	 * �ýⷨ:
	 * (1)����sum[i],(2)��sums�������򣬵õ����������copy(3)����ÿ��sum[i],�ҵ�copy�����һ��j����copy[j]-sum[i]>upper,�ҵ���һ��k����copy[k]-sum[i]>=low,��ô��sum[i]��˵����j-k��������
	    �ýⷨ�ڱ���copy�������޷�����sum�е�Ԫ���Ƿ���ڵ�ǰ��copy�����Ԫ�أ�������ʵ��
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
	 * �鲢����
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
	 * �ýⷨ��Ҫ����merge�����л�ȡcount��Ŀ:������merge����ʱ��������[low,mid]��[mid,high]�������ź����ˣ�
	 * ��ʱ������ߵ�ÿ��i�����ұ��ҳ�����ָ��j��k:
	 * j�ǵ�һ������sum[j]-sum[i]>high��ָ��,k�ǵ�һ������sum[k]-sum[i]<low��ָ������ô����i��˵����ʱ��j-k������������
	 * �������������ұ����飬���ұ�����С��sum[i]������ŵ�cache������,�����merge
	 * @param sums
	 * @param low
	 * @param high
	 * @return
	 */
	public int countNumWhileMergeSort(long[] sums,int start,int end,int lower,int upper){
		if(end-start==0)//Ϊ�˱�֤�����ж������Ƿ����Ҫ��
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
		int mid=(end+start)/2;//����ȡ��
		int count=countNumWhileMergeSort(sums, start, mid,lower,upper)+countNumWhileMergeSort(sums, mid+1, end,lower,upper);
		int t=mid+1;
		long[] cach=new long[end-start+1];
		int j=mid+1,k=mid+1;
		for(int i=start,r=0;i<=mid;i++,r++){
			/* ����sum-sum[i]>=low���ұ����飺�����㣬�����㣬���㣬���㣬����(�����������飬�ӵ�һ����ʼ���㣬��ô��������Ԫ�ؿ϶�����)
			 * ����sum-sum[i]<=upper���ұ�����:���㣬���㣬���㣬����(sum-sum[i]<low�϶�����sum-sum[i]<upper),�����㣬������
			 * ����������֮���Ϊ4-2
			 *  ����ӵ�һ������sum-sum[i]>=low��ָ��Ϊmid+k����һ������sum-sum[k]<=upper��ָ��Ϊmid+j,
			 *  ��ô��������֮���Ԫ�ع���mid+j-mid+k+1=j-k+1��
			 */
			//�����mid+1��end֮�乲��k��sum����sum-sum[i]<low,��ô��mid+k+1����ʼ������sum-sum[i]>=low
			 while (k <=end && sums[k] - sums[i] < lower) k++;
			 //�����mid+1��end֮�乲��j��sum����sum-sum[i]<=upper,��ô��������֮���Ԫ�ع���mid+j-mid-k-1+1=j-k��
		      while(j<=end&&sums[j]-sums[i]<=upper) j++;
		      while(t<=end&&sums[t]<sums[i]) //�ⲿ�ִ�����Ϊ�����merge�������ұ�����ÿ��С��sum[i]��Ԫ�ض�Ӧ�÷���sum[i]��ǰ��
		    	  cach[r++]=sums[t++];
		      cach[r]=sums[i];
		      count+=j-k;
		}
			System.arraycopy(cach, 0, sums, start, t-start);//���sort,t-start��ʾ�ұ�����С���������ĸ�������Ӧ�滻����ЩԪ��
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
		System.out.println("�ڶ��ַ���������Ϊ��"+new CountOfRangeSum().countNumForTreeMap(num, left, right));
		System.out.println("�����ַ���������Ϊ��"+new CountOfRangeSum().countNum(num, left, right));
	}
}
