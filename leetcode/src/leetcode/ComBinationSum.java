package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ComBination Sum指的是给定一个数组和一个目标数，找出该数组中所有元素和等于目标数的组合的集合
 * [10, 1, 2, 7, 6, 1, 5] 和8
 * 那么这些组合为
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
    				   if(i>left&&arr[i]==arr[i-1])//避免出现同样的组合，i>left说明此时上一个循环已经结束开始了下一个循环
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
