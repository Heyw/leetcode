package leetcode.depthfirst;

/**假设一个二叉树每个节点的值只能为0-9，
 * 该题就是求根节点到叶子节点组成的数字之和
 * 例如根节点为1，左右子节点分别为2，和3
 * 那么sum=12+13=25
 * @author Administrator
 *
 */
public class SumRootTofLeaf129 {
	private int sum=0;
     public int sumNumbers(TreeNode root) {
        		helper(root,0);
        		return sum;
    }
     public void helper(TreeNode root,int val){
    	 if(root==null) return;
    	 if(root.left!=null){
    		 helper(root.left,val*10+root.val);
    	 }
    	 if(root.right!=null)
    		 helper(root.right,val*10+root.val);
    	 if(root.left==null&&root.right==null)
    		 sum+=root.val+val*10;
     }
   public static void main(String[] args) {
	TreeNode root=new TreeNode(2);
	root.left=new TreeNode(4);
	root.right=new TreeNode(6);
	System.out.println(new SumRootTofLeaf129().sumNumbers(root));
}
}
