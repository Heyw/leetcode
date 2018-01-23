package leetcode.depthfirst;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定前序遍历和中序遍历的顺序，求重组
 * @author Administrator
 *
 */
public class ContrustBSTFromPreAndInorderTraversal105 {
	/**
	 * 先序遍历：先访问根，在左，再右 
	 * 中序遍历：先访问左，在根，再右
	 *  先序遍历第一个值是根节点的值；当先序遍历访问到中序遍历第一个值时，表示中左节点都已经访问完了，要开始访问右节点了，
	 *  右节点是先序遍历中当前访问指针代表的节点，父节点是中序遍历当前访问指针的前一个

      利用个map保存序列中每个值形成的节点，
	 * @param preorder
	 * @param inorder
	 * @return
	 */
	 public TreeNode buildTree(int[] preorder, int[] inorder) {
	        if(preorder==null|| inorder==null) return null;
	        int preIndex=0;
	        int inIndex=0;
	        TreeNode root=null;
	        TreeNode curr=null;
	        TreeNode pre=null;//表示遍历中上一个访问的节点
	        Map<Integer,TreeNode> nodeMap=new HashMap<>();
	        while(preIndex<preorder.length&&inIndex<preorder.length){
	        	if(preIndex==0){
	        		curr=root=new TreeNode(preorder[preIndex++]);
	        		pre=curr;
	        		nodeMap.put(curr.val, curr);
	        	}else{
	        		TreeNode node=nodeMap.get(inorder[inIndex]);
	        		boolean isright=false;
	        		while(node!=null&&inIndex<inorder.length-1){//node不等于null时，说明该节点已经被访问了，那么继续中序遍历下一个值直到没有被访问的
	        			pre=node;
	        			isright=true;
        				node=nodeMap.get(inorder[++inIndex]);
        			 }
	        		while(pre!=null&&preIndex<preorder.length&&isright&&preorder[preIndex]==inorder[inIndex]){
	        			curr=new TreeNode(preorder[preIndex++]);
	        			pre.right=curr;
	        			nodeMap.put(curr.val, curr);
	        			while(inIndex<inorder.length&&nodeMap.containsKey(inorder[inIndex])){
	        				pre=nodeMap.get(inorder[inIndex]);
	        				inIndex++;
	        			}
	        		}
	        		//如果node==null，那么说明此时先序遍历还没遍历完左子节点
	        		if(preIndex<preorder.length){
	        			 curr=new TreeNode(preorder[preIndex++]);
	        			 if(!isright)
	        			     pre.left=curr;
	        			 else
	        				 pre.right=curr;
	        			 pre=curr;
	        			 nodeMap.put(curr.val, curr);
	        		}
	        	}
	           }
	        return root;
	    }
	 
	 int preIdx, inIdx;
	    
	    public TreeNode buildTreeByAns(int[] preorder, int[] inorder) {        
	        inIdx = 0;
	        preIdx = 0;
	        return helper(inorder, preorder, Integer.MAX_VALUE);    
	    }
	    
	    private TreeNode helper(int[] in, int[] pre, int rootValue) {
	        if (inIdx == in.length || in[inIdx] == rootValue) {
	            return null;
	        }
	        TreeNode node = new TreeNode(pre[preIdx]);
	        preIdx++;
	        node.left = helper(in, pre, node.val);
	        inIdx++;
	        node.right = helper(in, pre, rootValue);
	        return node;
	    }

	 public static void main(String[] args) {
		 //4,3,2,1,6,5,7,1,2,3,4,5,6,7
		TreeNode root=new ContrustBSTFromPreAndInorderTraversal105().buildTree(new int[]{1,2,3}, new int[]{1,3,2});
		root.preorder(root);
		System.out.println();
		root.inorder(root);
	}
}
