package leetcode;

/**
 * �ýⷨ���ǽ�һ���ַ������ա�W����״���У�Ȼ��˳�򷵻ظ��ַ���
 * ��� abcdefghijklm�ַ��� ����3��zigzag�������£�
 * a         e          i        m
 * b   d    f    h    j    l   
 * c         g         k
 * ����˳�򷵻ؾ��� aeimbdfhjlcgk��W��״�������й�ϵ����Ҫ�۲�ÿ���������Ĺ�ϵ,�ҳ����ɾͺܺý��
 * 0 1 2 3 4 5 6 7 8 9 10 4��zigzag
 * 0            6                12
 * 1       5   7          11
 * 2   4       8    10
 * 3            9
 * @author Administrator
 *
 */
public class ZigZagConvertor {
	/**
	 * ÿ��������Ԫ��λ�����2*rows-2��
	 * �м��л���Ҫ����һ����Ԫ�أ��ø�Ԫ�ر���Ԫ�ض�lag-2*i,i��������
	 * @param s
	 * @param rows
	 * @return
	 */
      public String convertor(String s,int rows){
    	  
    	  StringBuilder sb=new StringBuilder();
    	  int lag=(rows==1?rows:2*rows-2);
    	  for(int i=0;i<rows;i++){
    		  for(int j=i;j<s.length();j+=lag){
    			  sb.append(s.charAt(j));
    			  if(i<rows-1&&i>0&&j+lag-2*i<s.length()){//�м�����Ҫ�Ӹ���Ԫ��
    				  sb.append(s.charAt(j+lag-2*i));
    			  }
    		  }
    	  }
    	  return sb.toString();
    	  
      }
      public static void main(String[] args) {
		String s="abcdefghijklmnopq";
	    System.out.println(new ZigZagConvertor().convertor("AB", 2));
	}
}
