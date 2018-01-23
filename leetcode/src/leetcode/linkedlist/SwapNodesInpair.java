package leetcode.linkedlist;

/**
 * 交换list的两个毗邻的节点，然后返回头结点
 * 例如：1-2-3-4 交换后就是2-1-4-3
 * @author Administrator
 *
 */
public class SwapNodesInpair {
public ListNode swapPairs(ListNode head) {
	   ListNode curr=head;
	   ListNode pre=null;
	   if(head.next!=null)
   		head=head.next;
	    while(curr!=null&&curr.next!=null){
	    	ListNode next=curr.next;
	    	curr.next=next.next;
	    	next.next=curr;
	    	if(pre!=null) pre.next=next;
	    	pre=curr;
	    	curr=curr.next;
	    	
	    }
        return head;
    }
   public static void main(String[] args) {
	ListNode root=new ListNode(1);
	   root.next=new ListNode(2);
	   root.next.next=new ListNode(3);
	   root.next.next.next=new ListNode(4);
	   root=new SwapNodesInpair().swapPairs(root);
	   while(root!=null){
		   System.out.println(root.val);
	   root=root.next;
	   }
			
}
}
