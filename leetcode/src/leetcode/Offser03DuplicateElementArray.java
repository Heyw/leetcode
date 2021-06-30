package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数数组a[n]，长度为n,其中0<=a[i]<n
 * @author yiwuhe
 *
 */
public class Offser03DuplicateElementArray {

	/**
	 * 因为数组元素在[0,n-1]之间，我们可以认为数组元素值a[m]与索引i之间存在多对1的映射；
	 * 我们可以通过原地交换的方式，将a[m]交换到a[a[m]]处，使得a[m]=m;
	 * 那么重复元素就出现在交换的条件中，当然如果a[i]=i，说明此时索引和值已经映射上了，无需交换，如果a[a[i]]!=a[i]，那么可以进行交换，如果等于则表示出现了重复；
	 * @param a
	 */
	public void swapIndex(int[] a) {
		
		List<Integer> dups=new ArrayList<>();
		for(int i=0;i<a.length;i++) {
			
			while(a[i]!=i) {//每次交换后a[i]都会赋值为a[a[i]]的值,但a[a[i]]不一定等于i,所以得循环下去；
				
				if(a[a[i]]!=a[i]) {
					int tmp=a[a[i]];
					a[a[i]]=a[i];
					a[i]=tmp;
				}else {
					dups.add(a[i]);
					break;
				}
			}
			
		}
		System.out.println(dups);
	}
	public static void main(String[] args) {
		new Offser03DuplicateElementArray().swapIndex(new int[] {2,2,4,3,3,1,1});
	}
}
