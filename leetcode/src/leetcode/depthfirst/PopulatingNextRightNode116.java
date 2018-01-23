package leetcode.depthfirst;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ����һ�����ṹTreeLinkNode,��ͬһ��ȵ�ÿ���ڵ�����ң����������ҽڵ�
 *����߽ڵ��next��ָ���ұߵĽڵ�
 *ps:��Ϊ��ȫ��������ֻ��ʹ�ù̶�����ռ�
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
