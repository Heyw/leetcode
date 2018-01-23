package leetcode.dynamicprogram;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * ������ArithmeticSlices�е����ƣ����������ǵ�����Ѱ��һ���е㣬�е�֮��������ǵȲ�����
 * ����������ڸ�������������Ѱ��k��Ԫ�أ���k��Ԫ�ذ��������е��Ⱥ�˳�������ǵȲ����У�����������У������Ȳ����и���
 * ����[1,2,3,5]����һ���Ȳ�����[1,3,5]
 * @author Administrator
 *
 */
public class ArithmeticSlicesII {
	/**
	 * �����Ȳ����г���Ϊ2����G(dif)����ʾ,�Ȳ�������M����ʾ
	 *1,2: ����i=2��T(2,1)=G(1)=1;M=0
	 *1,2,3,����i=3,T(3,1)=T(2,1)+1=2;M=1
	 *1,1,2:i=3,T(3,1)=G(1)=2;M=0;
	 *1,1,2,3:i=4,T(4,1)=T(3,1)+1=3;M=2;
	 *�����ԭ�������Ȳ����к�������һ��ͬdif�����ݣ���ô�����Ȳ����и�����һ������ʵ�ʵȲ����е���ԭ�����Ȳ����У�֤����
	 *�Ȳ����г���Ϊn����ô�����Ȳ�����Ϊn-1;ʵ�ʵȲ����У�(n-2)(n-1)/2;
	 *��������һ���Ȳ����ݣ���ô�����Ȳ�����Ϊn,ʵ�ʵȲ����У���n-1��n/2,��ô������ʵ�ʵȲ����г���Ϊ(n-1)n/2-(n-2)(n-1)/2=n-1
	 *	 * @param A
	 * @return
	 */
	 public int numberOfArithmeticSlices(int[] A) {
			Map<Integer,Map<Integer,Integer>> map=new HashMap<>();//valueֵ�������map������������iԪ����ǰ������Ԫ�صĲ�ֵ�Լ������
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
