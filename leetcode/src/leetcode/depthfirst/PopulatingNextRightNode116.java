package leetcode.depthfirst;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 给定一个树结构TreeLinkNode,将同一深度的每个节点从左到右，依次设置右节点
 *即左边节点的next，指向右边的节点
 *ps:树为完全二叉树，只能使用固定额外空间
 * @author Administrator
 *
 */
public class PopulatingNextRightNode116 {
		public void connect(TreeLinkNode root) {
		        helper(root);
		 }
		
		private void helper(TreeLinkNode node){
			if(node==null) return ;
			TreeLinkNode pre=null;
			TreeLinkNode root=node.left;
			while(node!=null&&node.left!=null&&node.right!=null){
				if(pre!=null){
					pre.next=node.left;
				}
				pre=node.left;
				pre.next=node.right;
				pre=node.right;
				node=node.next;
			}
			helper(root);
		}
}
