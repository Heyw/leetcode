package leetcode.depthfirst;

import java.util.HashMap;
import java.util.Map;

/**
 * ����ǰ����������������˳��������
 * @author Administrator
 *
 */
public class ContrustBSTFromPreAndInorderTraversal105 {
	/**
	 * ����������ȷ��ʸ����������� 
	 * ����������ȷ������ڸ�������
	 *  ���������һ��ֵ�Ǹ��ڵ��ֵ��������������ʵ����������һ��ֵʱ����ʾ����ڵ㶼�Ѿ��������ˣ�Ҫ��ʼ�����ҽڵ��ˣ�
	 *  �ҽڵ�����������е�ǰ����ָ�����Ľڵ㣬���ڵ������������ǰ����ָ���ǰһ��

      ���ø�map����������ÿ��ֵ�γɵĽڵ㣬
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
	        TreeNode pre=null;//��ʾ��������һ�����ʵĽڵ�
	        Map<Integer,TreeNode> nodeMap=new HashMap<>();
	        while(preIndex<preorder.length&&inIndex<preorder.length){
	        	if(preIndex==0){
	        		curr=root=new TreeNode(preorder[preIndex++]);
	        		pre=curr;
	        		nodeMap.put(curr.val, curr);
	        	}else{
	        		TreeNode node=nodeMap.get(inorder[inIndex]);
	        		boolean isright=false;
	        		while(node!=null&&inIndex<inorder.length-1){//node������nullʱ��˵���ýڵ��Ѿ��������ˣ���ô�������������һ��ֱֵ��û�б����ʵ�
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
	        		//���node==null����ô˵����ʱ���������û���������ӽڵ�
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
