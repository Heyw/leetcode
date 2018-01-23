package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ComBination Sumָ���Ǹ���һ�������һ��Ŀ�������ҳ�������������Ԫ�غ͵���Ŀ��������ϵļ���
 * [10, 1, 2, 7, 6, 1, 5] ��8
 * ��ô��Щ���Ϊ
 * [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]

 * @author Administrator
 *
 */
public class ComBinationSum {
       public List<List<Integer>> solution(int[] arr,int target){
    	   List<List<Integer>> res=new ArrayList<>();
    	   Arrays.sort(arr);
    	   List<Integer> list=null;
    		   list=new ArrayList<>();
    	       dfs(arr, 0, target, list,res);
    	   return res;
       }
       void dfs(int[] arr,int left,int target,List<Integer>list,List<List<Integer>>res){
    		   for(int i=left;i<arr.length;i++){
    			   if(target>arr[i]){
    				   if(i>left&&arr[i]==arr[i-1])//�������ͬ������ϣ�i>left˵����ʱ��һ��ѭ���Ѿ�������ʼ����һ��ѭ��
    					   continue;
    				   list.add(list.size(),arr[i]);
    		          dfs(arr,i+1,target-arr[i],list,res);
    		          list.remove(list.size()-1);
    			   }else if(target==arr[i]){
    				  list.add(list.size(),arr[i]);
				     res.add(new ArrayList<>(list));
				     list.remove(list.size()-1);
					  return;
				   }else{
                   return;
			   }
    		
    		   }
    		   
       }
       public static void main(String[] args) {
		int[] arr={10, 1, 2, 7, 6, 1, 5};
		int target=8;
		System.out.println(new ComBinationSum().solution(arr, target));
	}
}
