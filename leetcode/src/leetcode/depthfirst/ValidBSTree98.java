package leetcode.depthfirst;

/**
 * �ж�һ�����Ƿ���valid binary search tree
 * ��׼��:�ڵ����ӽڵ�С�ڽڵ㣬���ӽڵ���ڽڵ㣬��������valid binary search tree
 * @author Administrator
 *
 */
public class ValidBSTree98 {
	 public boolean isValidBST(TreeNode root) {
		 if(root==null) return true;
	     return isValid(root.left,root.val,null)&&isValid(root.right,null,root.val);
	    	  
	 }
	 
	/**
	 * �жϵ�ǰ�ڵ��Ƿ����bst����ǰ�ڵ�ҪС��max������min
	 * @param root
	 * @param max��ָ�ĵ�ǰ�ڵ����ܻ�ȡ�����ֵ
	 * @param min:ָ�ĵ�ǰ�ڵ����ܻ�ȡ����Сֵ
	 * @return
	 */
	 public boolean isValid(TreeNode root,Integer max,Integer min){
		       if(root==null) return true;
		      if(min!=null&&root.val<=min||max!=null&&root.val>=max)
		          return false;
		      return isValid(root.left,root.val,min)&&isValid(root.right,max,root.val);
	 }
	 
	
}
