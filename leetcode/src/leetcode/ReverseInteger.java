package leetcode;

/**
 * 123 reverse 321
 * -321 reverse -123
 * @author Administrator
 */
public class ReverseInteger {
     public int solution1(int x){
    	int result=0;
    	 while(x!=0){
    		 int pre=result;
    		 result=10*result+x%10;
    		 if((result-x%10)/10!=pre) return 0;
    		 x=x/10;
    		 
    	 }
    	 return result;
     }
     public static void main(String[] args) {
		System.out.println(new ReverseInteger().solution1(153423646));
		System.out.println((int)('a'));
	}
}
