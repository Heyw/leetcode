package leetcode.depthfirst;

/**
 * leetcode第99题，指的是一个BST中有两个节点被错误交换，求利用constant space来找出这两个节点并还原树
 * 两个节点被交换只有三种情况：一父节点和子节点错误交换，二父节点和孙子节点被错误交换，三是左子节点和右子节点交换错误
 * 解法：中序遍历每一个节点，如果前面有节点大于后面的节点的值，则将前者标记为a，接着遍历，如果的值，则将后者标记为b，
 * 则将a和b交换
 * @author Administrator
 *
 */
public class RecoverBST99 {
	private TreeNode first;
	private TreeNode second;
	private TreeNode prevNode=new TreeNode(Integer.MIN_VALUE);
	public void recoverTree(TreeNode root) {
		iterator(root);	   
		swap(first, second);
	 }
	public void iterator(TreeNode node){
		if(node==null) return;
		iterator(node.left);
		if(first==null&&prevNode.val>node.val)
			first=prevNode;
		if(first!=null&&prevNode.val>node.val)
			second=node;
		prevNode=node;
		iterator(node.right);
	}
	public void swap(TreeNode nodeA ,TreeNode nodeB){
		int pv=nodeA.val;
		nodeA.val=nodeB.val;
		nodeB.val=pv;
	}
}
