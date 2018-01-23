package leetcode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * leetcode ����Ķ���������������
 * (1)����һ��������
 * (2)��������next()���ؽ�����BST����С����
 * (3)ʱ�临�Ӷ�o(1),�ռ临�Ӷ�Ϊo(h)��hΪ���ĸ߶�
 * �ⷨ˼·��
 * ��ջ�������������ջ��������С���������ڵ㣬Ȼ���������������ң���ʵ��next����
 * ��������ʵ����������TreeNodeƴ�Ӷ��ɵģ�����Ҫ���浽ʲô�����У����ڶ�������ֻҪ���ø��ڵ㣬�����������ӽڵ�
 * TreeMap�оͱ�����һ��������
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
    //�÷�������������ǰ�ڵ㼰�����е����ӽڵ㶼���뵽��ջ�У�ʹ��ջ����ԶΪ��С�ĵ�ǰ�ڵ�
    private void putAll(TreeNode node){
     	while(node!=null){
    		stack.push(node);//����ǰ�ڵ�ѹ��ջ��
    		node=node.leftNode;//�������ӽڵ�
    	}
        //��ѭ������ʱ����С�����ӽڵ�λ��ջ��
    }
    /**
     * �÷�����ʵ����ʵ�����������������
     * ʱ�临�Ӷ�Ϊo(h),hΪ�����������,�ռ临�Ӷ�Ϊo(h)memory,������ռ��ڴ治�������������
     * @return
     */
    public TreeNode next(){
    	TreeNode minNode=stack.pop();
    	//��stack����ջ��Ԫ��minNode���µ�ջ��Ԫ����minNode�ĸ��ڵ㣬���ڵ�϶��Ǵ������ӽڵ���ҽڵ��
    	//��˽�minNode�����ӽڵ㼰���ӽڵ�����ӽڵ�ѹ���ջ
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
   //ʱ�临�Ӷ�Ϊo(1)
    public boolean hasNext(){
    	return !stack.isEmpty();
    }
    
    /**
     * ��������ջ�ķ�ʽ������������������������:�����з�ʽ�����ڵ�
     * �÷�ʽ�������ڣ�ջ��������ǵ�ǰ��δ���ʵĽڵ㣬��Ҫһ����ǽڵ��ʾ��һ�����ʽڵ㣬���ñ�ǽڵ���ڵ�ǰ�ڵ�����ӽڵ�ʱ�ͷ��ʵ�ǰ�ڵ㣬������ǰ����Ϊ��ǽڵ㣬
     * Ȼ��ջ������Ϊ������Ҫ���ʵĵ�ǰ�ڵ�
     */
    public void postOrderBST(){
    	stack.clear();
    	TreeNode currNode=root;
    	TreeNode preNode=root;//�����������ӽڵ�ʱ��preNode������Ǹ����ӽڵ�
    	while(currNode!=null || !stack.isEmpty()){//�����ǰ�ڵ㲻Ϊnull����ջ�л�����Ҫ���ʵĽڵ���ôѭ����ȥ
    		//�Ƚ����в�Ϊnull�����ӽڵ㶼�����ջ��
    	     if(currNode!=null){
    			stack.push(currNode);
    			currNode=currNode.leftNode;
    		  }else {//currNode����null��˵��ջ���ڵ�����ӽڵ��Ѿ������ʻ���Ϊnull
    			currNode=stack.pop();//��ȡջ���ڵ�
    			if(currNode.rightNode!=null&&currNode.rightNode!=preNode){//�����ǰ�ڵ�����ӽڵ���δ����
    				stack.push(currNode);
    				currNode=currNode.rightNode;
    			}else{//�����ǰ�ڵ����ӽڵ�Ϊnull�����Ѿ����ʣ���ô���ʵ�ǰ�ڵ㣬����preNode����ΪcurrNode��currNode����Ϊnull,null��ʾ��ǰ�ڵ��Ѿ�������
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
