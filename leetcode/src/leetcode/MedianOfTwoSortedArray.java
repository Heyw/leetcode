package leetcode;

/**
 * 该类是用来解决求出两个排好序的数组的中位数，算法复杂度应该是O(lg(m+n)),m和n分别是两个数组的长度
 * @author Administrator
 *
 */
public class MedianOfTwoSortedArray {

	
	/**
	 * 解法思路：用left1，right1和left2，right2分别指向两个数组的左右端，然后每次循环同时比较两端的大小
	 * 左边小的放入数组3中，同时移动左边小的指针，右边大的放入数组3中，同时移动右边大的指针
	 * 算法复杂度为o(n)，不符合要求
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
	 * leecode寻找中间数算法
	 * 中间数：将集合分成长度相同的两部分LeftPart={A[0],A[1],.....,A[i-1]}和rightPart=｛A[i],....A[m]｝,其中都有max(leftPart)<=min(rightPart),那么中间数就是(min(rightPart)+max(leftPart))/2;
	 * 同理，对于数组a1,数组a2，长度分别为m,n,我们分别从中取出i，j个元素，确保i+j=(m+n)/2or(m+n+1)/2,同时满足 a1[i-1]<a2[j],a1[i]>a2[j-1],这样
	 * 对于leftPart={a1[0],......a1[i-1]，a2[0],.....a[j-1]}以及rightPart={a1[i],....a1[m],a2[j],....a2[n]},都有max(leftPart)<=min(rightPart),这样就可以找到中间数；
	 * 解法步骤：
	 * 1.如果m<=n，在a1数组0~m中寻找i，j=(m+n+1)/2-i,因为m<=n,0<=i<=m，那么j=(m+n+1)/2-i>=(m+n+1)/2-m>=(2m+1)/2-m>0
	 * 同时能满足j=j=(m+n+1)/2-i<=(2n+1)/2=n
	 * 2.循环过程中，对于每个i，j，如果a1[i]<a2[j-1],那么i++，j--,如果a1[i-1]>a2[j],那么i--，j++
	 * 如果满足了a1[i-1]<a2[j]且a1[i]>a2[j-1]，那么中间数是 ：
	 * 对于(m+n)%2=1,则为max(a1[i-1],a[j-1])
	 * 否则为(max(a1[i-1],a[j-1])+min(a1[i],a2[j]))/2
	 * 
	 * 算法复杂度为o(min(lg(n),lg(m)))
	 * 注意：i+j=(m+n+1)/2则将确保leftPart的长度不比rightPart的长度短，因此如果m+n为奇数，那么leftPart比rightPart多出一个元素，
	 * 此时中间数就是leftPart最大的那个数即max(a1[i-1],a2[j-1])
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
