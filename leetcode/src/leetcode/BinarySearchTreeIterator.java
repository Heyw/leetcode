package leetcode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * leetcode 上面的二叉树搜索遍历：
 * (1)遍历一个二叉树
 * (2)遍历方法next()返回接下来BST中最小的数
 * (3)时间复杂度o(1),空间复杂度为o(h)，h为树的高度
 * 解法思路：
 * 用栈来保存二叉树，栈顶放置最小的数的树节点，然后中序遍历（左根右）来实现next方法
 * 二叉树其实就是连串的TreeNode拼接而成的，不需要保存到什么链表中，对于二叉树，只要晓得根节点，就晓得所有子节点
 * TreeMap中就保存了一个二叉树
 * @author Administrator
 *
 */
public class BinarySearchTreeIterator {
    private class TreeNode{
    	int val;
    	TreeNode leftNode;
    	TreeNode rightNode;
    	TreeNode(int val){
    		this.val=val;
    	}
    }
    
    Stack<TreeNode> stack=new Stack<>();
    TreeNode root=null;
    //该方法是用来将当前节点及其所有的左子节点都放入到堆栈中，使得栈顶永远为最小的当前节点
    private void putAll(TreeNode node){
     	while(node!=null){
    		stack.push(node);//将当前节点压入栈中
    		node=node.leftNode;//换成左子节点
    	}
        //当循环结束时，最小的左子节点位于栈顶
    }
    /**
     * 该方法的实现其实就是中序遍历二叉树
     * 时间复杂度为o(h),h为二叉树的深度,空间复杂度为o(h)memory,即额外空间内存不超过二叉树深度
     * @return
     */
    public TreeNode next(){
    	TreeNode minNode=stack.pop();
    	//当stack弹出栈顶元素minNode后，新的栈顶元素是minNode的父节点，父节点肯定是大于左子节点的右节点的
    	//因此将minNode的右子节点及该子节点的左子节点压入堆栈
    	if(minNode.rightNode!=null){
    		putAll(minNode.rightNode);
    	}
    	return minNode;
    }
    
    public void addTreeNode(int val){
    	if(root==null)
    		root=new TreeNode(val);
    	else{
	    	TreeNode currNode=root;
	    	TreeNode preNode=null;
	    	boolean right=true;
	    	while(currNode!=null){
	    		preNode=currNode;
	    		if(currNode.val<=val){
	    			currNode=currNode.rightNode;
	    			right=true;
	    			}
	    		else{
	    			currNode=currNode.leftNode;
	    			right=false;
		    	}
	    	}
	    	if(right)
	    		preNode.rightNode=new TreeNode(val);
	    	else
	    		preNode.leftNode=new TreeNode(val);
    	}
    	stack.clear();
    	putAll(root);
    }
   //时间复杂度为o(1)
    public boolean hasNext(){
    	return !stack.isEmpty();
    }
    
    /**
     * 本方法用栈的方式来后序遍历二叉树，后序遍历:左右中方式遍历节点
     * 该方式核心在于：栈顶保存的是当前尚未访问的节点，需要一个标记节点表示上一个访问节点，当该标记节点等于当前节点的右子节点时就访问当前节点，并将当前设置为标记节点，
     * 然后将栈顶设置为接下来要访问的当前节点
     */
    public void postOrderBST(){
    	stack.clear();
    	TreeNode currNode=root;
    	TreeNode preNode=root;//当遍历完左子节点时，preNode用来标记该左子节点
    	while(currNode!=null || !stack.isEmpty()){//如果当前节点不为null或者栈中还有需要访问的节点那么循环下去
    		//先将所有不为null的左子节点都放入堆栈中
    	     if(currNode!=null){
    			stack.push(currNode);
    			currNode=currNode.leftNode;
    		  }else {//currNode等于null，说明栈顶节点的左子节点已经被访问或者为null
    			currNode=stack.pop();//获取栈顶节点
    			if(currNode.rightNode!=null&&currNode.rightNode!=preNode){//如果当前节点的右子节点尚未访问
    				stack.push(currNode);
    				currNode=currNode.rightNode;
    			}else{//如果当前节点右子节点为null或者已经访问，那么访问当前节点，并将preNode设置为currNode，currNode设置为null,null表示当前节点已经被访问
    				System.out.println(currNode.val);
    				preNode=currNode;
    				currNode=null;
    			}
	    	}
      }
    	
    }
    public static void main(String[] args) {
		int[] nums={4,3,5,1,7,2,6,9,8,10};
	      BinarySearchTreeIterator it = new BinarySearchTreeIterator();
		for(int i=0;i<nums.length;i++){
			it.addTreeNode(nums[i]);
		}
//		while(it.hasNext()){
//			System.out.println(it.next().val);
//		}
		it.postOrderBST();
	}
}
