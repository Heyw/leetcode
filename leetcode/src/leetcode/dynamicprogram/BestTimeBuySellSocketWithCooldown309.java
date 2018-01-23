package leetcode.dynamicprogram;

/**
 * ��Ŀ���⣺
    ����һ�����飬��i��Ԫ�ش���ĳֻ��Ʊ�ڵ�i��ļ۸�
   ���һ���㷨����������档�������ɶ�ν��ף��༴��������롢����ͬһֻ��Ʊ������Ҫ�����������ƣ�
   �㲻������ͬһʱ����������ף��༴���������Ʊ֮ǰ������������
    ��������Ʊ֮���㲻�����ڵڶ����������롣���༴����Ҫһ�����ȴ��CD��ʱ�䣩;
   example: 
      prices = [1, 2, 3, 0, 2]
       maxProfit = 3
       transactions = [buy, sell, cooldown, buy, sell]
    �ⷨ˼·���ö�̬�滮
    ��������״̬sells��buys
    ����sells[i]��ʾ����i��������Ʊ���ܻ�õ��������,sells[0]=0
    buys[i]��ʾ����i�������Ʊ���ܻ�õ��������:buys[0]=-prices[0]
    ����sells[i]:ǰһ����ڵ������buy��sell���������
	    ���ǰһ��Ϊbuy��ôsells[i]=buys[i-1]+prices[i],
	    ���ǰһ��Ϊsell��ô��Ϊǰһ��cooldown������sell����sell[i]=sell[i-1]+prices[i]-prices[i-1]
         ��sells[i]ȡ��������������ֵ    
    ����buys[i]:ǰһ�����Ϊbuy��Ҳ����ǰ����Ϊsell,
         ���ǰһ��Ϊbuy��ô��Ϊǰһ��cooldow������buy����buys[i]=buys[i-1]+prices[i-1]-prices[i];
         ���ǰ����Ϊsell��ôbuys[i]=sells[i-2]-prices[i]
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
