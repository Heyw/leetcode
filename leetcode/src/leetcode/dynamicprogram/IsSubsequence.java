package leetcode.dynamicprogram;

/**
 * 判断两个字符串s和t，s是否是t的子字符串，其中s长度小于100,而t的长度小于50000
 * s是t的字符串的标准是，s中的字符按顺序依次在t中出现，例如ace是abcde的子字符串，aec不是
 * @author Administrator
 *
 */
public class IsSubsequence {
	/**
	 * 利用二分搜索法求解：
	 * 将t分为两部分，left和rignt，判断left中包含了s中左边多少个字符，则将光标在s中右移多少位，
	 * 同理right中包含了t中右边多少个字符，就将有光标右移在s中右移多少位
	 * 如果已经移动到最后，则返回光标位置
	 * @param s
	 * @param t
	 * @return
	 */
   public boolean isSubsequenceByBS(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        return binarySearch(sArr, tArr, 0, 0,tArr.length-1)>=sArr.length;
    }
   /**
    * 
    * @param s
    * @param t
    * @param sL:左光标
    * @param sR：右光标
    * @param tL
    * @param tR
    * @return
    */
   private int binarySearch(char[] s,char[] t ,int index,int tL,int tR){
	   if(index>=s.length) return index;
	   if(tR-tL>2){
		  int tM=(tL+tR)/2;
		  index=binarySearch(s, t, index, tL, tM);
	      index=binarySearch(s, t, index, tM+1, tR);
	   }
	   else{
			  for(int j=tL;j<=tR;j++){
				  if(index>=s.length) return index;
				  if(t[j]==s[index]){
					  index++;
				  }
			  }
		   }
		  return index;
	   }
   public static void main(String[] args) {
	   String s="abc";
	   String t="ahbgdc";
	   System.out.println(new IsSubsequence().isSubsequenceByBS(s, t));
}
}
