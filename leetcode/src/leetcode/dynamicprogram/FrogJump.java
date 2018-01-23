package leetcode.dynamicprogram;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 青蛙跳问题，青蛙利用河中的石头跳过一条河，给定一个数组，数组成员值表示石头块在河中的位置，青蛙每次只能向前跳，
 * 如果上次跳的长度是k，那么下次跳可以是k+1，k或者k-1，第一次跳长度只能为1；
 * 在给定数组的条件下，请问青蛙能不能跳过河
 * @author Administrator
 *
 */
public class FrogJump {
	/**
	 * 如果青蛙能从i跳到j位置处，那么青蛙此时的能跳的宽度肯定包含了stones[j]-stones[i],
	 * 用一个HashMap保存青蛙在stones的各个位置能跳的宽度
	 * @param stones
	 * @return
	 */
	 public boolean canCross(int[] stones) {
		    Map<Integer,Set<Integer>> map=new HashMap<>();
		    map.put(0, new HashSet<>());
		    map.get(0).add(1);
	        for(int i=1;i<stones.length;i++){
	        	Set<Integer> steps=new HashSet<>();
	        	for(int j=i-1;j>=0;j--){
	        		if(map.get(j).contains(stones[i]-stones[j])){
	        			for(int k=-1;k<=1;k++){
	        			  steps.add(stones[i]-stones[j]+k);
	        			  if(stones[i]-stones[j]+k==stones[stones.length-1]-stones[1])
	        				  return true;
	        			}
	        		}else{
	        			continue;
	        		}
	        	}
	        	map.put(i, steps);
	        }
	        return map.get(stones.length-1).size()>0;
	    }
	 public boolean canCrossByDFS(int[] stones) {
	        if (stones == null || stones.length == 0) {return false;}
	        int n = stones.length;
	        if (n == 1) {return true;}
	        if (stones[1] != 1) {return false;}
	        if (n == 2) {return true;}
	        int last = stones[n - 1];
	        HashSet<Integer> hs = new HashSet();
	        for (int i = 0; i < n; i++) {
	            if (i > 3 && stones[i] > stones[i - 1] * 2) {return false;} // The two stones are too far away. 
	            hs.add(stones[i]);
	        }
	        return canReach(hs, last, 1, 1);
	    }
	    
	    private boolean canReach(HashSet<Integer> hs, int last, int pos, int jump) {
	        if (pos + jump - 1 == last || pos + jump == last || pos + jump + 1 == last) {
	            return true;
	        }
	        if (hs.contains(pos + jump + 1)) {
	            if (canReach(hs, last, pos + jump + 1, jump + 1)) {return true;}
	        }
	        if (hs.contains(pos + jump)) {
	            if (canReach(hs, last, pos + jump, jump)) {return true;}
	        }
	        if (jump > 1 && hs.contains(pos + jump - 1)) {
	            if (canReach(hs, last, pos + jump - 1, jump - 1)) {return true;}
	        }
	        return false;
	    }
	 public static void main(String[] args) {
		int[] stones=new int[]{0,1,2,3,4,8,9,11};
		System.out.println(new FrogJump().canCross(stones));
	}
}
