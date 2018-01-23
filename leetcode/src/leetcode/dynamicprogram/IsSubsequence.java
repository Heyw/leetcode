package leetcode.dynamicprogram;

/**
 * �ж������ַ���s��t��s�Ƿ���t�����ַ���������s����С��100,��t�ĳ���С��50000
 * s��t���ַ����ı�׼�ǣ�s�е��ַ���˳��������t�г��֣�����ace��abcde�����ַ�����aec����
 * @author Administrator
 *
 */
public class IsSubsequence {
	/**
	 * ���ö�����������⣺
	 * ��t��Ϊ�����֣�left��rignt���ж�left�а�����s����߶��ٸ��ַ����򽫹����s�����ƶ���λ��
	 * ͬ��right�а�����t���ұ߶��ٸ��ַ����ͽ��й��������s�����ƶ���λ
	 * ����Ѿ��ƶ�������򷵻ع��λ��
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
    * @param sL:����
    * @param sR���ҹ��
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
