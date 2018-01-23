package leetcode;

/**
 * ��������Ǹ���һ�������ַ�����Ȼ���жϸ������ַ����Ƿ���"Addtive number"��
 * "Addtive number"�ı�׼���ǣ������ַ�����˳��ֳ�һ�����ַ����飬��������ַ�������ڵ���3�����Ҵӵ������ַ����鿪ʼ��
 * ���ַ��������������֯����ǰ������֮�ͣ���ô�������ַ�������"Addtive number"
 * 199100199=1+99+100+199������һ��"Addtive number"
 * 112358=1+1+2+3+5+8����Ҳ��һ��"Addtive number"
 * @author Administrator
 *
 */
public class AddtiveNumber306 {
		public boolean isAdditiveNumber(String num) {
		        for(int i=1;num!=null&&i<=num.length()/2;i++){//i�����һ�����ֵĳ���,���ܳ������ֳ��ȵ�һ��
		        	long first=Long.valueOf(num.substring(0,i));
		        	if(String.valueOf(first).length()<i) return false;
		        	for(int j=1;j<=num.length()/2;j++){//j����ڶ������ֵĳ���
		        		Long second=Long.valueOf(num.substring(i,i+j));
		        		if(i+j<num.length()&&String.valueOf(second).length()==j&&isAdditive(first, second, i+j, num))
		        			return true;
		        	}
		        }
		        return false;
		    }
		
		/**
		 * 
		 * @param first ��һ������
		 * @param second �ڶ�������
		 * @param loc �ڶ����ֺ��λ��
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
