package leetcode.dynamicprogram;

import java.util.HashMap;
import java.util.Map;

/**
 * ����һ�����ֵmax����Ŀ��ֵtotal������������ѡ��һ��С�ڵ���max�Ĳ��ظ�����ӣ�ֱ���͵��ڣ�total���ʵ�һ�������ȵ�totalô��
 * �����������˶��������ˣ�����ѡ�������Լ���ʤ������ֻҪ��һ����ѡ������󣬵ڶ������л�ʤ�Ļ�����ô����һ���˽���ʧ��
 * ���Ե�һ����ѡ���������ʹ�õڶ������������ѡ���޷���ʤ
 * ������max=10��total=11
 * ���۵�һ����ѡ��ʲô�����ڶ����˶���ѡ��һ��������total���ʷ���false
 * @author Administrator
 *
 */
public class CanIWin {
	/**
	 * �ⷨ��brute force��dp�㷨���ϣ�dp����������������һЩ�ظ����ݣ��ڱ�����dp��HashMap����ʾ
	 * @param maxChoosableInteger
	 * @param desiredTotal
	 * @return
	 */
	 public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
	        if((1+maxChoosableInteger)*maxChoosableInteger/2<desiredTotal) return false;
	        if(desiredTotal<=0) return true;
	    return     canWin(desiredTotal,maxChoosableInteger,0,new HashMap<Integer,Boolean>());
	    }

	 /**
	  * a��ʾ�ö����Ʊ�ʾ״̬������1��ʾ��λ�����Ѿ���ռ��
	  * @param total
	  * @param max
	  * @param a
	  * @param map
	  */
	private boolean canWin(int total, int max, int a, Map<Integer, Boolean> map) {
		if(map.containsKey(a)) return map.get(a);
		for(int i=0;i<max;i++){
			if((a&1<<i)!=0) continue;
			if(total<=i+1|| !canWin(total-i-1,max,a|1<<i,map)){//��ǰѡ�������Ҫ����total������λ��(total-i-1,max)ѡ�񲻻�ɹ�,������ǰ��ѡ��Ż����ȳ���total
				map.put(a, true);
				return true;
			}
		}
		map.put(a,false);
		return false;
	}
	public static void main(String[] args) {
		System.out.println(new CanIWin().canIWin(10, 11));
	}
}
