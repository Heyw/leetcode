package leetcode.depthfirst;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树和一个数sum，求出二叉树所有满足根到叶子节点之和等于sum的路径
 * @author Administrator
 *
 */
public class SumPathII113 {
	private List<List<Integer>> list=new ArrayList<>();
	 public List<List<Integer>> pathSum(TreeNode root, int sum) {
	        helper(root,sum,new ArrayList<Integer>());
	        return list;
	    }
	 
	 public void helper(TreeNode root,int sum,List<Integer> arrlist){
		 if(root==null){
			 return;
		 }else{ 
			 arrlist.add(root.val);
			 if(root.val==sum && root.left==null&& root.right==null){
			 List<Integer> target=new ArrayList<>();
			 target.addAll(arrlist);
			 list.add(target);
			 }else {
				 helper(root.left,sum-root.val,arrlist);
				 helper(root.right,sum-root.val,arrlist);
			 }
			 arrlist.remove(arrlist.size()-1);	 
	    }
	 }
	 public static void main(String[] args) {
		 int[] in=new int[]{7,11,2,4,5,13,8,3,6,1};
    	int[] post=new int[]{7,2,11,4,13,3,1,6,8,5};
    	TreeNode root=new ContrustBSTFromPostAndInorderTraversal106().buildTree(in, post);
    	List<List<Integer>> list=new SumPathII113().pathSum(root, 22);
    	for(List<Integer> arr:list){
    		System.out.println(arr);
    	}
	}
}
