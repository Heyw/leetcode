package leetcode;

/**
 * ���������������������ź�����������λ�����㷨���Ӷ�Ӧ����O(lg(m+n)),m��n�ֱ�����������ĳ���
 * @author Administrator
 *
 */
public class MedianOfTwoSortedArray {

	
	/**
	 * �ⷨ˼·����left1��right1��left2��right2�ֱ�ָ��������������Ҷˣ�Ȼ��ÿ��ѭ��ͬʱ�Ƚ����˵Ĵ�С
	 * ���С�ķ�������3�У�ͬʱ�ƶ����С��ָ�룬�ұߴ�ķ�������3�У�ͬʱ�ƶ��ұߴ��ָ��
	 * �㷨���Ӷ�Ϊo(n)��������Ҫ��
	 * @param array1
	 * @param array2
	 * @return
	 */
	public double solution1(int[] array1,int[] array2){
		int left1=0,left2=0;
		int right1=array1.length-1,right2=array2.length-1;
		int[] array3=new int[array1.length+array2.length];
		for(int i=0,j=array3.length-1;i<array3.length&&j>=i;i++,j--){
			if(array1[left1]<array2[left2]&&left1<=right1)
				array3[i]=array1[left1++];
			else if(left2<=right2){
				array3[i]=array2[left2++];
			}
		    if(array1[right1]>array2[right2]&&right1>=left1)
		    	array3[j]=array1[right1--];
		    else if(right2>=left2)
		    	array3[j]=array2[right2--];
		}
		if(array3.length%2==1)
			return array3[array3.length/2];
		else
			return new Double((array3[(array3.length)/2]+array3[array3.length/2-1]))/2;
	}
	
	/**
	 * leecodeѰ���м����㷨
	 * �м����������Ϸֳɳ�����ͬ��������LeftPart={A[0],A[1],.....,A[i-1]}��rightPart=��A[i],....A[m]��,���ж���max(leftPart)<=min(rightPart),��ô�м�������(min(rightPart)+max(leftPart))/2;
	 * ͬ����������a1,����a2�����ȷֱ�Ϊm,n,���Ƿֱ����ȡ��i��j��Ԫ�أ�ȷ��i+j=(m+n)/2or(m+n+1)/2,ͬʱ���� a1[i-1]<a2[j],a1[i]>a2[j-1],����
	 * ����leftPart={a1[0],......a1[i-1]��a2[0],.....a[j-1]}�Լ�rightPart={a1[i],....a1[m],a2[j],....a2[n]},����max(leftPart)<=min(rightPart),�����Ϳ����ҵ��м�����
	 * �ⷨ���裺
	 * 1.���m<=n����a1����0~m��Ѱ��i��j=(m+n+1)/2-i,��Ϊm<=n,0<=i<=m����ôj=(m+n+1)/2-i>=(m+n+1)/2-m>=(2m+1)/2-m>0
	 * ͬʱ������j=j=(m+n+1)/2-i<=(2n+1)/2=n
	 * 2.ѭ�������У�����ÿ��i��j�����a1[i]<a2[j-1],��ôi++��j--,���a1[i-1]>a2[j],��ôi--��j++
	 * ���������a1[i-1]<a2[j]��a1[i]>a2[j-1]����ô�м����� ��
	 * ����(m+n)%2=1,��Ϊmax(a1[i-1],a[j-1])
	 * ����Ϊ(max(a1[i-1],a[j-1])+min(a1[i],a2[j]))/2
	 * 
	 * �㷨���Ӷ�Ϊo(min(lg(n),lg(m)))
	 * ע�⣺i+j=(m+n+1)/2��ȷ��leftPart�ĳ��Ȳ���rightPart�ĳ��ȶ̣�������m+nΪ��������ôleftPart��rightPart���һ��Ԫ�أ�
	 * ��ʱ�м�������leftPart�����Ǹ�����max(a1[i-1],a2[j-1])
	 * @param a1
	 * @param a2
	 */
	public Double solution2(int[] a1,int[] a2){
		int m=a1.length,n=a2.length;
		if(m>n)
			return solution2(a2,a1);
		int i=m/2,j=0;
		int left=0,right=m;
		while(i<=m&&i>=0){
			i=(left+right)/2;
			 j=(m+n+1)/2-i;
			if(i<right&&a1[i]<a2[j-1]){
				left=i+1;
			}else if(i>left&&a1[i-1]>a2[j]){
			  right=i-1;
			}else{
				break;
			}
		}
		int maxOfLeft;
		if(i==0)
			maxOfLeft=a2[j-1];
		else if(j==0)
			 maxOfLeft=a1[i-1];
		else 
			maxOfLeft=Math.max(a1[i-1],a2[j-1]);
			
		if((m+n)%2==1)
			return (double) maxOfLeft;
		int minOfRight;
		if(i==m)
			minOfRight=a2[j];
		else if(j==n)
			minOfRight=a1[i];
		else
			minOfRight=Math.min(a1[i], a2[j]);
		return (maxOfLeft+minOfRight)/2.0;
	}
	public static void main(String[] args) {
		int[] a1={1,2,7,8};
		int[] a2={3,4};
		System.out.println(new MedianOfTwoSortedArray().solution2(a1, a2));
	}
}
