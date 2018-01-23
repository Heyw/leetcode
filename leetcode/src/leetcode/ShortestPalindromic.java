package leetcode;

/**
 * ��һ���ַ�����Ȼ���ڸ��ַ���ǰ����ַ���ʹ���Ϊһ�������ַ���
 * aacecaaa ��� a  aaacecaaa
 * abcd   ���   dcb    acdabce
 * @author Administrator
 *
 */
public class ShortestPalindromic {

	public String solution1(String s){
		/**
		 * �ⷨ˼·������s�ĵ�һ���ַ�Ϊ���ģ��ҵ����ַ�����������ַ�����ַ�����Ȼ��˳�����ʣ���ַ������ַ�����
		 */
		StringBuilder sub=new StringBuilder();
		for(int i=s.length()-1;i>=0;i--){
			int left=0,right=i;
			while(s.charAt(left)==s.charAt(right)){
				if(left==right-1||left==right){
					return sub.append(s).toString();
				}
				left++;right--;
			}
			sub.append(s.charAt(i));
		}
		return sub.toString();
	}
	public static void main(String[] args) {
		String s="aacecaaa";
		System.out.println(new ShortestPalindromic().solution1(s));
	}
}
