package leetcode.dynamicprogram;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 这道题和ArithmeticSlices有点类似，但是上面那道题是寻找一个切点，切点之间的数列是等差数列
 * 这道题是求在给定数组中依次寻找k个元素，这k个元素按照数组中的先后顺序排列是等差数列，求给定数组中，这样等差数列个数
 * 例如[1,2,3,5]，有一个等差数列[1,3,5]
 * @author Administrator
 *
 */
public class ArithmeticSlicesII {
	/**
	 * 泛化等差数列长度为2，用G(dif)来表示,等差序列用M来表示
	 *1,2: 对于i=2，T(2,1)=G(1)=1;M=0
	 *1,2,3,对于i=3,T(3,1)=T(2,1)+1=2;M=1
	 *1,1,2:i=3,T(3,1)=G(1)=2;M=0;
	 *1,1,2,3:i=4,T(4,1)=T(3,1)+1=3;M=2;
	 *如果在原来泛化等差数列后面新添一个同dif的数据，那么泛化等差数列个数加一，新增实际等差数列等于原泛化等差数列，证明：
	 *等差数列长度为n，那么泛化等差数列为n-1;实际等差数列：(n-2)(n-1)/2;
	 *后面新增一个等差数据，那么泛化等差数列为n,实际等差数列：（n-1）n/2,那么新增的实际等差数列长度为(n-1)n/2-(n-2)(n-1)/2=n-1
	 *	 * @param A
	 * @return
	 */
	 public int numberOfArithmeticSlices(int[] A) {
			Map<Integer,Map<Integer,Integer>> map=new HashMap<>();//value值所代表的map用来保存数组i元素与前面其他元素的差值以及其个数
			int sum=0;
			for(int i=0;i<A.length;i++){
			    Map<Integer,Integer> difMap=new HashMap<>();
			    for(int j=i-1;j>=0;j--){
			    	long dif=(long) A[i]-A[j];
			    	if(dif>=Integer.MAX_VALUE||dif<=Integer.MIN_VALUE) continue;
			    	Map<Integer, Integer> map2 = map.get(j);
			    	int nums_i=difMap.getOrDefault((int)dif, 0);
			    	int nums_j=map2.getOrDefault((int)dif, 0);
			    	difMap.put((int)dif, nums_i+nums_j+1);
			    sum+=nums_j;
			    }
			    map.put(i, difMap);
			}
			return sum;
	    }
	 
	 public static void main(String[] args) {
			System.out.println( new ArithmeticSlicesII().numberOfArithmeticSlices(new int[]{0,2000000000,-294967296}));
		}
}
