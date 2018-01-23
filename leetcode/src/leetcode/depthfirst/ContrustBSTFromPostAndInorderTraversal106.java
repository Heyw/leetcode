package leetcode.depthfirst;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定中序和后序遍历序列求重组该二叉树
 * @author Administrator
 *
 */
public class ContrustBSTFromPostAndInorderTraversal106 {
	private int  inIdx=0;
	private int postIdx=0;
	private Map<Integer,TreeNode> visitMap=new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(inorder,postorder,null);
    }
    private TreeNode helper(int[] in,int[] post,TreeNode pre){
		while(postIdx<post.length&&visitMap.containsKey(post[postIdx])){
		    visitMap.get(post[postIdx]).right=pre;
		    pre= visitMap.get(post[postIdx++]);
		}
		if(inIdx==in.length||postIdx==post.length)
    		return pre;
		TreeNode  root=new TreeNode(in[inIdx]);
		root.left=pre;
		visitMap.put(root.val, root);
		if(in[inIdx++]==post[postIdx]){
			postIdx++;
			return helper(in,post,root);
		}else{
			return helper(in,post,null);
		}
    }
    public static void main(String[] args) {
    	int[] in=new int[]{1,2,3,4,5,6,7,8};
    	int[] post=new int[]{1,4,3,2,6,7,8,5};
    	TreeNode root=new ContrustBSTFromPostAndInorderTraversal106().buildTree(in, post);
    	root.inorder(root);
    	System.out.println();
    	root.postorder(root);
	}
}
