package leetcode.dynamicprogram;

/**
 * 给定一个正整数n，求出在[0,n]之间每个正整数的二进制数的1的个数
 * 例如n=5，返回[0,1,1,2,1,2]
 * 要求：时间复杂度为o(n),空间复杂度也为o(n)
 * @author Administrator
 *
 */
public class CountingBits338 {
	/**
	 * 任何数n都可以转化为2^m+x,2^m只包含1个1，x中有i个那么n中就有i+1个
	 * @param num
	 * @return
	 */
	 public int[] countBits(int num) {
	        int[] ones=new int[num+1];
	        int ref=1;
	        if(num>1){
	        	ones[1]=1;
	        }
	        for(int i=2;i<=num;i++){
	         if(i==2*ref){
	        		ones[i]=1;ref*=2;
	         }else if(i>ref){
	        		ones[i]=ones[i-ref]+ones[ref];
	        	}
	        }
	        return ones;
	    }
	 /**
	  * 判断i是奇数还是偶数+i左移一位后的个数，就等于当前i的1的个数
	  * @param num
	  * @return
	  */
	 public int[] countBitsTwo(int num) {
	        int[] res =  new int[num + 1];
	        for(int i = 1; i<=num; i++) 
	            res[i] = res[i >> 1] + (i & 1);
	        return res;
	    }

	 public static void main(String[] args) {
		System.out.println(new CountingBits338().countBits(7));
	}
}
