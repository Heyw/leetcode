package leetcode.dynamicprogram;

import java.util.HashMap;
import java.util.Map;

/**
 * 该问题就是将一个大于等于2的正整数分为几个正整数，然后返回这几个正整数之积，求该积最大为多少
 * 例如10=3+3+4,result=3*3*4
 * 任何一个数n=n-1+1，n相对与n-1来说多了1，这个1要添加到n-1分为了子整数中的最小的一个上去；
 * 因为假设f(n-1)=a*b，a<b;那么f(n)=(a+1)*b=ab+b>a(b+1)=ab+a，因此把1往最小正整数上分配就可以取得最大值；
 * 因为任何数都可以分解成2和3，且3^2>2^3，故对于n>=4的数，只要在保证分解的最小正整数大于等于2的情况下，3越多越好
 * @author Administrator
 *
 */
public class IntegerBreak343 {
	 public int integerBreak(int n) {
	        if(n<2) return 0;
	        if(n==2) return 1;
	        if(n==3) return 2;
	        if(n==4) return 4;
	      int max=1;
	       while(n>=5){
	    	   max*=3;
	    	   n-=3;
	       }
	       return max*n;
	    }
	 public static void main(String[] args) {
		System.out.println(new IntegerBreak343().integerBreak(11));
	}
}
