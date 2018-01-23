package leetcode.dynamicprogram;

import java.util.HashMap;
import java.util.Map;

/**
 * ��������ǽ�һ�����ڵ���2����������Ϊ������������Ȼ�󷵻��⼸��������֮������û����Ϊ����
 * ����10=3+3+4,result=3*3*4
 * �κ�һ����n=n-1+1��n�����n-1��˵����1�����1Ҫ��ӵ�n-1��Ϊ���������е���С��һ����ȥ��
 * ��Ϊ����f(n-1)=a*b��a<b;��ôf(n)=(a+1)*b=ab+b>a(b+1)=ab+a����˰�1����С�������Ϸ���Ϳ���ȡ�����ֵ��
 * ��Ϊ�κ��������Էֽ��2��3����3^2>2^3���ʶ���n>=4������ֻҪ�ڱ�֤�ֽ����С���������ڵ���2������£�3Խ��Խ��
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
