package leetcode.dynamicprogram;

import java.util.Arrays;

/**
 * ����Ŀ���Ǹ���һ�����飬����Ԫ�ش�����ֱ�ֵ��Ȼ���ٸ���һ��Ŀ��ֵ��������������е�Ԫ�����Ŀ��ֵ����СԪ�ظ���
 * ����[1,2,3,5],goal=11,��ôfewest=[1,5,5]=3
 * ÿ�����ֶ���������ʹ��
 * �������򷵻�-1
 * @author Administrator
 *
 */
public class CoinChange322 {
	   /**
	    * ����a[i]��ʾ���������ҵ�Ԫ��ʹ��Ԫ��֮�͵���i����СԪ�ظ�����
	    *a[k]=Min(a[k-j]+1),����j<k��a[k-j]>0,j��������С��k�����Ԫ��
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
