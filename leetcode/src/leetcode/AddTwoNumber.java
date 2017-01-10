package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 该问题是：给两个数：342，465，用list保存，表头放置低位，如下：
 * 2->4->3,5->6->4，然后将这个两个数相加，得到list:
 * 7->0->8
 * @author Administrator
 *
 */
public class AddTwoNumber {
      public List<Integer> solutionOne(List<Integer>list1,List<Integer>list2){
    	 List<Integer> ansList=new ArrayList<Integer>();
    	 for(int i=0;i<list1.size()||i<list2.size();i++){
    		 if(list1.size()==i)
    			list1.add(new Integer(0));
    		 if(list2.size()==i)
    			list2.add(new Integer(0));
    		 int num=list1.get(i)+list2.get(i);
    		 if(ansList.size()>i){
    			 num+=ansList.get(i);
    		   }
    		 if(ansList.size()==i){
    			 ansList.add(num%10);
    		 }else{
    			 ansList.set(i, num%10);
    		 }
    		 if(num/10>0)
    			   ansList.add(num/10);
    	 }
    	 return ansList;
      }
      
      public List<Integer>solutionTwo(List<Integer>list1,List<Integer>list2){
    	  List<Integer> ansList=new LinkedList<>();
    	  Iterator<Integer> it1 = list1.iterator();
    	  Iterator<Integer> it2 = list2.iterator();
    	  int num=0;
    	  int d=0;
    	  while(it1.hasNext()||it2.hasNext()){
    		  if(it1.hasNext())
    			  num+=it1.next();
    		  if(it2.hasNext())
    			  num+=it2.next();
    		  num+=d;
    		  ansList.add(num%10);
    		  d=num/10;
    		  num=0;
    	  }
    	  return ansList;
      }
      public static void main(String[] args) {
		Integer[] nums={2,4,3};
		Integer[] ums={4,6};
		List<Integer> list1=new ArrayList<>();
		list1.addAll(Arrays.asList(nums));
		List<Integer>list2=new ArrayList<>();
		list2.addAll(Arrays.asList(ums));
		System.out.println(new AddTwoNumber().solutionOne(list1, list2).toString());
		System.out.println(new AddTwoNumber().solutionTwo(list1, list2).toString());
	}
}
