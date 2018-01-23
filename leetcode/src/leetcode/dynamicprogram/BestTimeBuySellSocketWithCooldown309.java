package leetcode.dynamicprogram;

/**
 * 题目大意：
    给定一个数组，第i个元素代表某只股票在第i天的价格。
   设计一个算法计算最大收益。你可以完成多次交易（亦即，多次买入、卖出同一只股票），需要满足下列限制：
   你不可以在同一时间参与多个交易（亦即，在买入股票之前必须卖出）。
    在卖出股票之后，你不可以在第二天马上买入。（亦即，需要一天的冷却（CD）时间）;
   example: 
      prices = [1, 2, 3, 0, 2]
       maxProfit = 3
       transactions = [buy, sell, cooldown, buy, sell]
    解法思路：用动态规划
    考虑两个状态sells和buys
    其中sells[i]表示：第i天卖出股票所能获得的最大收益,sells[0]=0
    buys[i]表示：第i天买入股票所能获得的最大收益:buys[0]=-prices[0]
    对于sells[i]:前一天存在的情况有buy和sell两种情况、
	    如果前一天为buy那么sells[i]=buys[i-1]+prices[i],
	    如果前一天为sell那么改为前一天cooldown，今天sell，则sell[i]=sell[i-1]+prices[i]-prices[i-1]
         则sells[i]取以上两种情况最大值    
    对于buys[i]:前一天可能为buy，也可能前两天为sell,
         如果前一天为buy那么改为前一天cooldow，今天buy，则buys[i]=buys[i-1]+prices[i-1]-prices[i];
         如果前两天为sell那么buys[i]=sells[i-2]-prices[i]
 * @author Administrator
 *
 */
public class BestTimeBuySellSocketWithCooldown309 {
			public int maxProfit(int[] prices) {
				if(prices==null||prices.length==0) return 0;
			        int[] buys=new int[prices.length];buys[0]=-prices[0];
			        int[] sells=new int[prices.length];sells[0]=0;
			        if(prices.length>=2){
				        buys[1]=-prices[1];
			            sells[1]=buys[0]+prices[1];
			        }else{
			        	return sells[0];
			        }
			        int maxprofit=Math.max(sells[0],sells[1]);
			        for(int i=2;i<prices.length;i++){
			        	int delta=prices[i-1]-prices[i];
			        	sells[i]=Math.max(buys[i-1]+prices[i], sells[i-1]-delta);
			        	if(sells[i]>maxprofit)maxprofit=sells[i];
			        	buys[i]=Math.max(buys[i-1]+delta, i>=2?sells[i-2]-prices[i]:0);
			        }
			        return maxprofit;
			    }
			public static void main(String[] args) {
				int[] prices=new int[]{3,4,5,6,7};
				System.out.println(new BestTimeBuySellSocketWithCooldown309().maxProfit(prices));
			}
}
