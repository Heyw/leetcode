package leetcode.depthfirst;

import java.util.List;

/**
 * 将一个二叉树根节点不变，按照先序遍历的方式转换成一个链表形式的树，
 * @author Administrator
 *
 */
public class FlattenBTtoLinkeList114 {
			public void flatten(TreeNode root) {
			       root=helper(root,null);
			}
			private TreeNode helper(TreeNode root,TreeNode last){
				if(root==null)
					return null;
				if(root.right!=null)
					last=helper(root.right,last);
				if(root.left!=null)
					last=helper(root.left,last);
				  root.right=last;
				  root.left=null;
				  return root;
			}
			public static void main(String[] args) {
				 int[] in=new int[]{3,2,4,1,6,5,7};
			     int[] post=new int[]{3,4,2,7,6,5,1};
			 	TreeNode root=new ContrustBSTFromPostAndInorderTraversal106().buildTree(in, post);
			 	new FlattenBTtoLinkeList114().flatten(root);
			}
}
