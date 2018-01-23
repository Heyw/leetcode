package leetcode;

public class IsPalindromic {
     public boolean isPalindromic(Integer n){
    	 int rev=0;
    	 int copy=n;
    	 while(n>rev){
    		 rev=rev*10+n%10;
    		 n=n/10;
    	 }
    	 return rev==n||rev/10==n;
     }
     public static void main(String[] args) {
		System.out.println(new IsPalindromic().isPalindromic(121));
	}
}
