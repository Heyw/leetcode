package leetcode.dynamicprogram;

import java.util.ArrayList;
import java.util.List;

/**
 * �����������һ��������������n�������0~n����������������Ʊ��ʽ����������1����Ŀ
 * ����5
 * 0:0
 * 1:1
 * 2:10
 * 3:11
 * 4:100
 * 5:101 ����ֻ��3Υ���˹涨������������1������5����������Ʊ��ʽ����������1
 * @author Administrator
 *
 */
public class NNIntegerswithoutConsecutiveOnes {
	//������1��ż���������������һ���Ƿ�������
	//������1�������Ķ����Ƿ�������
		public int findIntegers(int num) {
		        int init=1;
		        int count=1;
		        List<Integer> nnListNew=new ArrayList<>();//���������������ж����Ʒ�������1����
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
		 * num�Ķ����Ʊ��ʽ����Ϊn����ô��n�����²���������1����������1010101.....01����101010...10
		 * ����Ϊnʱ���������a[n]������������1���������֤��a[n]=a[n-1]+a[n-2] ��n>=2
		 * a[n]�϶�����a[n-1]������a[n]���a[n-1]����1.....0��101...0�ⲿ������Ҳ����a[n-2]�ⲿ������,��a[n]=a[n-1]+a[n-2]
		 * ����쳲���������
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
	        
	        int result = a[n - 1] + b[n - 1];//n������������
	        for (int i = n - 2; i >= 0; i--) {
	            if (sb.charAt(i) == '1' && sb.charAt(i + 1) == '1') break;//��������1����ô˵���ڵ�ǰi�϶�����10�Լ�01��00��������ʲ���Ҫ��ѭ����ȥ
	            if (sb.charAt(i) == '0' && sb.charAt(i + 1) == '0') //��������0����ô˵����ǰi�����������ķ�����1�����������������n���ȵ���������1������У���Ҫ��ȥ
	            	result -= b[i];
	        }
	        
	        return result;
	    }

		public static void main(String[] args) {
			System.out.println(new NNIntegerswithoutConsecutiveOnes().findIntegers2(7));
		}
}
