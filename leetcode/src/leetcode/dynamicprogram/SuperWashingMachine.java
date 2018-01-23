package leetcode.dynamicprogram;

/**
 * �����Ŀ�Ǹ���n̨ϴ�»���ÿ��ϴ�»��������������·���һ�����������ƶ�n̨ϴ�»���һ�����·����ڽ�ϴ�»���
 * ��ʹ��ÿ̨ϴ�»�ƽ���·��������ƶ�����
 * [0,1,5]
 * first��1,1,4;second: 2,1,3;three:2,2,2,returne 3��
 * �������ƽ�ַ���-1
 * @author Administrator
 *
 */
public class SuperWashingMachine {
	/**
	 * �������Ҽ���������ÿ̨�����ж���Ҫ���������·�����ͬʱ��������������·��������Ǹû����ƶ�������ȡ�����л������ƶ��������ֵ�������մ𰸣�
	 *���i�����·�������ƽ��ֵ����ô����Ҫ����һ̨�����ƶ�avg-machines[i]���·�;
	 *���i�����·�������ƽ��ֵ,��ô��������һ̨�����ƶ�machines[i]-avg���·�
	 *����machines[i]���ڱ����·���������һ̨���������ƶ����·��������ߵ��ڱ����·�����ȥ��һ̨������Ҫ�����ƶ���ȥ���·���
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
