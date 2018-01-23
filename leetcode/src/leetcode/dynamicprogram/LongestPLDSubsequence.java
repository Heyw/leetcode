package leetcode.dynamicprogram;

/**
 * ������ַ�������
 * ����һ���ַ���,������ַ���������ܻ����Ӵ�
 * ��cbbd������:bb
 * "bbbad" ���أ�bbb
 * @author Administrator
 *
 */
public class LongestPLDSubsequence {
	/**
	 * �����ж���β�Ƿ�һ�������һ��������β��
	 * ʱ�临�Ӷ��е��
	 * @param s
	 * @return
	 */
	 public int longestPalindromeSubseq(String s) {
	        if(s==null) return 0;
	        char[] carr = s.toCharArray();
	        return calLongestPLDLength(carr, 0, carr.length-1);
	        
	    }
	 
	 private int calLongestPLDLength(char[] carr,int l,int h){
		 if(h<l) return 0;
		 if(l==h)
			return 1;
		 else{
			 if(carr[l]==carr[h]){
				return calLongestPLDLength(carr, l+1, h-1)+2;
			 }
			 else
				 return Math.max(calLongestPLDLength(carr, l+1, h), calLongestPLDLength(carr, l, h-1));
		 }
	 }
	 
	 /**
	  * ����ݹ�ʱ��̫���ˣ����ö�̬�滮������ݹ���ļ�¼����ʡʱ��
	  * @param s
	  * @return
	  */
	 public int longestPalindromeSubseq2(String s) {
	        if(s==null) return 0;
	        char[] carr = s.toCharArray();
	         int count=0;
	       int[][] dp=new int[carr.length][carr.length];//dp[i][j]��ʾi��j���Ӵ�������Ĵ�����
	       for(int i=carr.length-1;i>=0;i--){//�Ӵ�С��������Ϊdp[i][j]�е�i����i+1��ֵ
	    	   dp[i][i]=1;
	    	   for(int j=i+1;j<carr.length;j++){
	    		   if(carr[i]==carr[j])
	    			   dp[i][j]=dp[i+1][j-1]+2;
	    		   else
	    			   dp[i][j]=Math.max(dp[i+1][j], dp[i][j-1]);
	    	   }
	       }
	        return dp[0][carr.length-1];
	    }
	 
	 public static void main(String[] args) {
		String s="cbaaaacc";
		System.out.println(new LongestPLDSubsequence().longestPalindromeSubseq2(s));

	}
}
