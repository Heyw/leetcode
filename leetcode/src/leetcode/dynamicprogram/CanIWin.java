package leetcode.dynamicprogram;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个最大值max，和目标值total，两个人轮流选择一个小于等于max的不重复数相加，直到和等于，total请问第一个人能先到total么？
 * 分析，两个人都是理性人，都会选择能让自己获胜的数，只要第一个人选择的数后，第二个人有获胜的机会那么，第一个人将会失败
 * 所以第一个人选择的数必须使得第二个人无论如何选择都无法获胜
 * 举例：max=10，total=11
 * 无论第一个人选择什么数，第二个人都能选择一个数到达total，故返回false
 * @author Administrator
 *
 */
public class CanIWin {
	/**
	 * 解法：brute force和dp算法相结合，dp用来保存计算过程中一些重复数据，在本例中dp用HashMap来表示
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
	  * a表示用二进制表示状态，其中1表示该位数字已经被占用
	  * @param total
	  * @param max
	  * @param a
	  * @param map
	  */
	private boolean canWin(int total, int max, int a, Map<Integer, Boolean> map) {
		if(map.containsKey(a)) return map.get(a);
		for(int i=0;i<max;i++){
			if((a&1<<i)!=0) continue;
			if(total<=i+1|| !canWin(total-i-1,max,a|1<<i,map)){//当前选择的数字要大于total或者下位在(total-i-1,max)选择不会成功,这样当前的选择才会首先超过total
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
