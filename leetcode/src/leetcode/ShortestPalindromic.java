package leetcode;

/**
 * 给一个字符串，然后在该字符串前添加字符，使其成为一个回文字符串
 * aacecaaa 添加 a  aaacecaaa
 * abcd   添加   dcb    acdabce
 * @author Administrator
 *
 */
public class ShortestPalindromic {

	public String solution1(String s){
		/**
		 * 解法思路就是以s的第一个字符为核心，找到该字符串类包含该字符的最长字符串，然后反顺序添加剩下字符串的字符即可
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
