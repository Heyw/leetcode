package leetcode;
/**
 * ��һ���ַ����������������Ӵ�
 * abdcdb,����ľ���bdcdb
 * aacbaef,����ľ���aa
 * ���ľ��Ǵ����Ļ��ֿ�����������
 * @author Administrator
 *
 */
public class LongestPalindromicSubString {
       public String    solution1(String s){
    	   int max=1;
    	   String sub=s.substring(0,1);
    	   for(int i=0;i<s.length();i++){
    		   int size=1;
    		   int j=i-1,k=i+1;
    		   for(;k<s.length();){
    			  if(j>=0&&s.charAt(j)==s.charAt(k)){
    				   size+=2;
    				   j--;
    				   k++;
    			   } else if(s.charAt(k-1)==s.charAt(k)&&s.charAt(i)==s.charAt(k)){
    				   size+=1;
    				   k++;
    			   }else{
    		           break;
    			   }
    		   }
    		   if(max<size||size==s.length()){
				   max=size;
				   //����ѭ���󣬴�ʱj����λ�õ��ַ���k����λ�õ��ַ��϶�������ͬ�ģ�j+1����λ���ǻ��Ŀ�ʼ��λ��
				   sub=s.substring(j+1,j+size+1);
			   }
    	   }
    	   System.out.println(sub);
    	   return sub;
       }
       
       public static void main(String[] args) {
		String s="aaaa";
		new LongestPalindromicSubString().solution1(s);
	}
}
