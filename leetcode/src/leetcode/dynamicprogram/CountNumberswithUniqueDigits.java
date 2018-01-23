package leetcode.dynamicprogram;

/**
 * ����һ���Ǹ�����n��������[0,10^)��������Ψһ������ɵ����ָ���
 * ���磺n=2����ô[0,99)�г���(11,22,33,44,55,66,77,88,99)�ⶼ����Ψһ������ɣ���˹���91��
 * @author Administrator
 *
 */
public class CountNumberswithUniqueDigits {
	/**
	 * dp[i]��ʾiλ��ʱ�ɲ��ظ���������ɵĸ���,���Կ���i��Ͱ�ų�һ��
	 * dp[1]=10;
	 * dp[2]=9*9+10;��һ��Ͱ���Է���[1,9]�е�����һ��������9��ѡ�񣬵ڶ���ͬ���Է���[0,9]�г��˵�һ�����������һ����Ҳ��9��ѡ��
	 * dp[3]=9*9*8+dp[2]��
	 *
	 * dp[i]=9*C+dp[i-1],����C��ʾ��10������ѡ�����ظ�������=9*8*...*(10-i+1)

	 * @param 
	 * @return
	 */
	public int countNumbersWithUniqueDigits(int n) {
		if(n<1) return 0;     
		int total=10;
		int c=9;
        for(int i=2;i<=n;i++){
        	if(11-i<=0) break;
        	c*=(11-i);
            total+=c;
        }
        return total;
	    }
	 public static void main(String[] args) {
		System.out.println(new CountNumberswithUniqueDigits().countNumbersWithUniqueDigits(3));
	}
}
