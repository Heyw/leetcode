package leetcode;

/**
 * 该问题就是给定一个数字字符串，然后判断该数字字符串是否是"Addtive number"；
 * "Addtive number"的标准就是：将该字符串按顺序分成一个子字符串组，如果该子字符串组大于等于3个，且从第三个字符串组开始，
 * 该字符串组所代表的组织等于前面两个之和，那么这样的字符串就是"Addtive number"
 * 199100199=1+99+100+199，这是一个"Addtive number"
 * 112358=1+1+2+3+5+8，这也是一个"Addtive number"
 * @author Administrator
 *
 */
public class AddtiveNumber306 {
		public boolean isAdditiveNumber(String num) {
		        for(int i=1;num!=null&&i<=num.length()/2;i++){//i代表第一个数字的长度,不能超过数字长度的一半
		        	long first=Long.valueOf(num.substring(0,i));
		        	if(String.valueOf(first).length()<i) return false;
		        	for(int j=1;j<=num.length()/2;j++){//j代表第二个数字的长度
		        		Long second=Long.valueOf(num.substring(i,i+j));
		        		if(i+j<num.length()&&String.valueOf(second).length()==j&&isAdditive(first, second, i+j, num))
		        			return true;
		        	}
		        }
		        return false;
		    }
		
		/**
		 * 
		 * @param first 第一个数字
		 * @param second 第二个数字
		 * @param loc 第二数字后的位置
		 * @return
		 */
		private boolean isAdditive(long first,long second,int loc,String num){
			if(loc>=num.length()) return true;
			String sum=String.valueOf(first+second);
			int nextloc=loc+sum.length();
			if(nextloc>num.length()) return false;
			String cur=num.substring(loc,nextloc);
			if(cur.equals(sum)){
				return isAdditive(second,Long.valueOf(sum),loc+sum.length(),num);
			}else{
				return false;
			}
		}
		public static void main(String[] args) {
			String num="0235813";
			System.out.println(new AddtiveNumber306().isAdditiveNumber(num));
		}
}
