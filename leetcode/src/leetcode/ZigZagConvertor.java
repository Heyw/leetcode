package leetcode;

/**
 * 该解法就是将一个字符串按照“W”形状排列，然后按顺序返回该字符串
 * 如果 abcdefghijklm字符串 按照3行zigzag排列如下：
 * a         e          i        m
 * b   d    f    h    j    l   
 * c         g         k
 * 按照顺序返回就是 aeimbdfhjlcgk，W形状和行数有关系，需要观察每行与行数的关系,找出规律就很好解决
 * 0 1 2 3 4 5 6 7 8 9 10 4行zigzag
 * 0            6                12
 * 1       5   7          11
 * 2   4       8    10
 * 3            9
 * @author Administrator
 *
 */
public class ZigZagConvertor {
	/**
	 * 每行两个主元素位置相差2*rows-2个
	 * 中间行还需要加上一个副元素，该副元素比主元素多lag-2*i,i代表行数
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
    			  if(i<rows-1&&i>0&&j+lag-2*i<s.length()){//中间行需要加个副元素
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
