package leetcode.depthfirst;

/**
 * leetcode��99�⣬ָ����һ��BST���������ڵ㱻���󽻻���������constant space���ҳ��������ڵ㲢��ԭ��
 * �����ڵ㱻����ֻ�����������һ���ڵ���ӽڵ���󽻻��������ڵ�����ӽڵ㱻���󽻻����������ӽڵ�����ӽڵ㽻������
 * �ⷨ���������ÿһ���ڵ㣬���ǰ���нڵ���ں���Ľڵ��ֵ����ǰ�߱��Ϊa�����ű����������ֵ���򽫺��߱��Ϊb��
 * ��a��b����
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
