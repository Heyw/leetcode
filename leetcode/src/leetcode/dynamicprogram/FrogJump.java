package leetcode.dynamicprogram;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * ���������⣬�������ú��е�ʯͷ����һ���ӣ�����һ�����飬�����Աֵ��ʾʯͷ���ں��е�λ�ã�����ÿ��ֻ����ǰ����
 * ����ϴ����ĳ�����k����ô�´���������k+1��k����k-1����һ��������ֻ��Ϊ1��
 * �ڸ�������������£����������ܲ���������
 * @author Administrator
 *
 */
public class FrogJump {
	/**
	 * ��������ܴ�i����jλ�ô�����ô���ܴ�ʱ�������Ŀ�ȿ϶�������stones[j]-stones[i],
	 * ��һ��HashMap����������stones�ĸ���λ�������Ŀ��
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
