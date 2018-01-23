package leetcode.dynamicprogram;

import java.util.ArrayList;
import java.util.List;

/**
 * 该问题就是求一个给定的正整数n，求出从0~n的正整数中其二进制表达式不包含连续1的数目
 * 例如5
 * 0:0
 * 1:1
 * 2:10
 * 3:11
 * 4:100
 * 5:101 其中只有3违反了规定，包含了连续1，故有5个数其二进制表达式不包含连续1
 * @author Administrator
 *
 */
public class NNIntegerswithoutConsecutiveOnes {
	//非连续1的偶数其二倍及二倍加一都是非连续的
	//非连续1的奇数的二倍是非连续的
		public int findIntegers(int num) {
		        int init=1;
		        int count=1;
		        List<Integer> nnListNew=new ArrayList<>();//用来保存新增所有二进制非连续的1的数
		        List<Integer> nnListOld=new ArrayList<>();
		        nnListNew.add(init);
		        while(init<num){
		        	nnListOld.addAll(nnListNew);
		        	nnListNew.clear();
		        	int size=nnListOld.size();
		        	for(int i=0;i<size;i++){
		        		int c=nnListOld.get(i);
		        		if(c<<1>num){
		        			break;
			        		  }
		        		if(c%2==0){
		        			nnListNew.add(c<<1);
		        			if(c<<1<num)
		        				nnListNew.add((c<<1)+1);
		        		}else{
		        			nnListNew.add(c<<1);
		        		}
		        	}
		        	nnListOld.clear();
		        	if(nnListNew.size()==0)
			        	 break;
		        	init=nnListNew.get(nnListNew.size()-1);
		        	count+=nnListNew.size();
		        }
		        return count+1;
		    }
		/**
		 * num的二进制表达式长度为n，那么该n长度下不包含连续1的最大组合是1010101.....01或者101010...10
		 * 长度为n时，其最多有a[n]个不包含连续1的组合数，证明a[n]=a[n-1]+a[n-2] ，n>=2
		 * a[n]肯定包含a[n-1]，另外a[n]相对a[n-1]多了1.....0到101...0这部分数据也就是a[n-2]这部分数据,故a[n]=a[n-1]+a[n-2]
		 * 就是斐波那契数列
		 * @param num
		 * @return
		 */
		public int findIntegers2(int num) {
	        StringBuilder sb = new StringBuilder(Integer.toBinaryString(num)).reverse();
	        int n = sb.length();
	        
	        int a[] = new int[n];
	        int b[] = new int[n];
	        a[0] = b[0] = 1;
	        for (int i = 1; i < n; i++) {
	            a[i] = a[i - 1] + b[i - 1];
	            b[i] = a[i - 1];
	        }
	        
	        int result = a[n - 1] + b[n - 1];//n长度最大组合数
	        for (int i = n - 2; i >= 0; i--) {
	            if (sb.charAt(i) == '1' && sb.charAt(i + 1) == '1') break;//连续遇到1，那么说明在当前i肯定包含10以及01，00的情况，故不需要再循环下去
	            if (sb.charAt(i) == '0' && sb.charAt(i + 1) == '0') //连续遇到0，那么说明当前i长度所包含的非连续1的组合数不被包含在n长度的最大非连续1组合数中，需要减去
	            	result -= b[i];
	        }
	        
	        return result;
	    }

		public static void main(String[] args) {
			System.out.println(new NNIntegerswithoutConsecutiveOnes().findIntegers2(7));
		}
}
