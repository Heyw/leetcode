package leetcode.dynamicprogram;

/**
 * s是“abcdefghijklmnopqrstuvwxyz”的无限循环，然后输入p，求出p有多少个不同的非空子字符串出现在s中，
 * 例如：p="aca",只有a和c是出现在s中
 * p="zabc"，其中z,a,b,c加上za，ab，bc，zab，abc，出现在s中，故返回6
 * @author Administrator
 *
 */
public class UniqueSubstringsInWraparoundString {
		public int findSubstringInWraproundString(String p) {
			  if(p==null||p.length()==0) return 0;
		      int[] dp=new int[26];//dp[i]表示字母i+'a'处按顺序出现的个数，例如‘abc’dp[a-a]=1,dp[b-a]=2,dp[c-a]=3;
		      char[] ar=p.toCharArray();
		      dp[ar[0]-'a']=1;
		      int pre=1;
		      int result=1;
		      for(int i=1;i<ar.length;i++){
		    	    int dif=ar[i]-ar[i-1];
		    	    pre=dif==1||dif==-25?pre+1:1;
		    		if(pre>dp[ar[i]-'a']){
		    			result+=pre-dp[ar[i]-'a'];
		    			dp[ar[i]-'a']=pre;
		    		}
		      }
		      return result;
		    }
		public static void main(String[] args) {
		  String str="zaba";
		  System.out.println(new UniqueSubstringsInWraparoundString().findSubstringInWraproundString(str));
		}
}
