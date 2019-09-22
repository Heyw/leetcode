package leetcode.dynamicprogram;

/**
 * s�ǡ�abcdefghijklmnopqrstuvwxyz��������ѭ����Ȼ������p�����p�ж��ٸ���ͬ�ķǿ����ַ���������s�У�
 * ���磺p="aca",ֻ��a��c�ǳ�����s��
 * p="zabc"������z,a,b,c����za��ab��bc��zab��abc��������s�У��ʷ���6
 * @author Administrator
 *
 */
public class UniqueSubstringsInWraparoundString {
		public int findSubstringInWraproundString(String p) {
			  if(p==null||p.length()==0) return 0;
		      int[] dp=new int[26];//dp[i]��ʾ��ĸi+'a'����˳����ֵĸ��������确abc��dp[a-a]=1,dp[b-a]=2,dp[c-a]=3;
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
