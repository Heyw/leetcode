package leetcode.dynamicprogram;

/**
 * 这道题目是给定n台洗衣机，每个洗衣机都会有整数件衣服，一次性最多可以移动n台洗衣机中一件的衣服到邻近洗衣机，
 * 求使得每台洗衣机平分衣服的最少移动次数
 * [0,1,5]
 * first：1,1,4;second: 2,1,3;three:2,2,2,returne 3；
 * 如果不能平分返回-1
 * @author Administrator
 *
 */
public class SuperWashingMachine {
	/**
	 * 从左向右检索，对于每台机器判断需要经过它的衣服数，同时加上自身溢出的衣服数，就是该机器移动次数，取出所有机器的移动次数最大值就是最终答案，
	 *如果i机器衣服数少于平均值，那么它需要从下一台机器移动avg-machines[i]件衣服;
	 *如果i机器衣服数多于平均值,那么它会向下一台机器移动machines[i]-avg件衣服
	 *另外machines[i]等于本身衣服数加上上一台机器给它移动的衣服数，或者等于本身衣服数减去上一台机器需要从它移动过去的衣服数
	 * @param machines
	 * @return
	 */
		public int findMinMoves(int[] machines) {
		        int sum=0;
		        for(int i=0;i<machines.length;i++) sum+=machines[i];
		        if(machines.length==0) return 0;
		        if(sum%machines.length!=0) return -1;
		        int avg=sum/machines.length;
		        int max=0,moveSteps=0;
		        for(int i=0;i<machines.length;i++){
		        	moveSteps+=machines[i]-avg;
		        	max=Math.max(Math.max(machines[i]-avg, max), Math.abs(moveSteps));
		        }
		        return max;
		    }
		public static void main(String[] args) {
			int[] machines=new int[]{6,0,6};
			System.out.println(new SuperWashingMachine().findMinMoves(machines));
		}
}
