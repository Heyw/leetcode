package leetcode.depthfirst;

/**
 * 判断一个树是否是valid binary search tree
 * 标准是:节点左子节点小于节点，右子节点大于节点，子树都是valid binary search tree
 * @author Administrator
 *
 */
public class ValidBSTree98 {
	 public boolean isValidBST(TreeNode root) {
		 if(root==null) return true;
	     return isValid(root.left,root.val,null)&&isValid(root.right,null,root.val);
	    	  
	 }
	 
	/**
	 * 判断当前节点是否符合bst，当前节点要小于max，大于min
	 * @param root
	 * @param max：指的当前节点所能获取的最大值
	 * @param min:指的当前节点所能获取的最小值
	 * @return
	 */
	 public boolean isValid(TreeNode root,Integer max,Integer min){
		       if(root==null) return true;
		      if(min!=null&&root.val<=min||max!=null&&root.val>=max)
		          return false;
		      return isValid(root.left,root.val,min)&&isValid(root.right,max,root.val);
	 }
	 
	
}
