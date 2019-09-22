package leetcode.dynamicprogram;

/**
 * [s,n]表示n个s字符串拼接成的一个字符串
 * 如果s1移除若干字符串可以得到s2，那么可以说成s2可以从s1得到，例如“abcdef”可以从acdcbef获取
 * S1=[s1,n1],S2=[s2,n2],S3=[S2,M]，求出最大的M可以使得S3从S1得到
 * @author Administrator
 *
 */
public class CountTheRepetitions {

	/**
	 * 如果s1中包含x个s2，那么则M=n1*x/n2
	 * @param s1
	 * @param n1
	 * @param s2
	 * @param n2
	 * @returnT
	 */
	 public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
		return n2;
	    }
}
