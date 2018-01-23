package leetcode.dynamicprogram;

import java.util.Arrays;

/**
 * 该题目就是给定一个数组，数组元素代表币种币值，然后再给定一个目标值，求出利用数组中的元素组成目标值的最小元素个数
 * 例如[1,2,3,5],goal=11,那么fewest=[1,5,5]=3
 * 每个币种都可以无限使用
 * 不存在则返回-1
 * @author Administrator
 *
 */
public class CoinChange322 {
	   /**
	    * 定义a[i]表示从数组中找到元素使得元素之和等于i的最小元素个数，
	    *a[k]=Min(a[k-j]+1),其中j<k且a[k-j]>0,j是数组中小于k的最大元素
	    * @param coins
	    * @param amount
	    * @return
	    */
		public int coinChange(int[] coins, int amount) {
			    int[] result=new int[amount+1];
			    Arrays.sort(coins);
			    if(amount>0)
		        getResult(coins,amount,result);
		       return result[amount]>=0?result[amount]:-1;
		    }

		private int getResult(int[] coins, int goal, int[] result) {
			if(goal==0) return 0;
			if(goal<0) return -1;
			if(result[goal]!=0) return result[goal];
			int min=Integer.MAX_VALUE;
			int res=0;
		    for(int i=coins.length-1;i>=0;i--){
		    		res=getResult(coins,goal-coins[i],result);
		    		if(res>=0&&res<min){
		    			min=res+1;
		    		}
		    	}
		    result[goal]=min==Integer.MAX_VALUE?-1:min;
			return result[goal];
		}
		
		int min=-1;
	    public int coinChangeBy5ms(int[] coins, int amount) {
	        Arrays.sort(coins);
	        helper(coins,amount,coins.length-1,0,0);
	        return min;
	    }
	    private void helper(int[] coins, int amount, int pos, int curV,int count){
	        if((amount-curV)%coins[pos]==0){
	            int tmp = count+(amount-curV)/coins[pos];
	            if(min!=-1)min = Math.min(min,tmp);
	            else min = tmp;
	        }
	        if(pos==0)return;
	        else {
	            int times = (amount-curV) / coins[pos];
	            for(int i=times;i>=0;i--){
	                if(min!=-1 && min <= count+i)break;
	                helper(coins,amount,pos-1,curV+coins[pos]*i,count+i);
	            }
	        }
	    }

		public static void main(String[] args) {
			int[] coins={186,419,83,408};
			System.out.println(new CoinChange322().coinChange(coins,6249));
		}
}
