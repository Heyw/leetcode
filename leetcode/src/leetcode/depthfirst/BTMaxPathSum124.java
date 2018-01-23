package leetcode.depthfirst;

/**
 * ����������ҵ�һ���������к�ֵ����һ��·������·������Ҫ��һ���ڵ㣬�������������Ľڵ�ͨ�����ڵ�����ӽڵ�����
 * @author Administrator
 *
 */
public class BTMaxPathSum124 {
	private int max=Integer.MIN_VALUE;
   public int maxPathSum(TreeNode root) {
	   helper(root);
       return max;    
    }
    private int helper(TreeNode root){
    	if(root==null)
    		return 0;
    	int leftMax=helper(root.left);
    	int rightMax=helper(root.right);
    	int maxSum=root.val;
    	if(leftMax>0)maxSum+=leftMax;
    	if(rightMax>0) maxSum+=rightMax;
    	if(maxSum>max) max=maxSum;
    	int val=leftMax>rightMax?leftMax:rightMax;
    	return root.val+(val>0?val:0);
    }
    public static void main(String[] args) {
		int[] in={2,1,3,-2,6};
		int[] post={2,3,1,6,-2};
		TreeNode root=new ContrustBSTFromPostAndInorderTraversal106().buildTree(in, post);
		System.out.println(new BTMaxPathSum124().maxPathSum(root));
	}
}
