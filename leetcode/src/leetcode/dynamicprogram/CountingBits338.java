package leetcode.dynamicprogram;

/**
 * ����һ��������n�������[0,n]֮��ÿ���������Ķ���������1�ĸ���
 * ����n=5������[0,1,1,2,1,2]
 * Ҫ��ʱ�临�Ӷ�Ϊo(n),�ռ临�Ӷ�ҲΪo(n)
 * @author Administrator
 *
 */
public class CountingBits338 {
	/**
	 * �κ���n������ת��Ϊ2^m+x,2^mֻ����1��1��x����i����ôn�о���i+1��
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
	  * �ж�i����������ż��+i����һλ��ĸ������͵��ڵ�ǰi��1�ĸ���
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
