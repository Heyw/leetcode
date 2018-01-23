package leetcode.linkedlist;

/**
 * 给定一个链表，然后从后面倒数k处断开旋转到链表表头
 * 1-2-3-4-5-6 ，k=2 则5-6-1-2-3-4
 * @author Administrator
 *
 */
public class RotateList {
		public ListNode rotateRight(ListNode head, int k) {
			 if(k==0||head==null) return head;
		        ListNode pre=new ListNode(-1);
		        pre.next=head;
		        ListNode curr=head;
		        int count=0;
		        while(curr!=null){
		        	curr=curr.next;
		        	count++;
		        }
		        k=k%count;
             if(k==0||head==null) return head;
		        curr=head;
		        ListNode preNode=null;
		        while(count>k){
		        	preNode=curr;
		        	curr=curr.next;
		        	count--;
		        }
		        pre.next=curr;
		        preNode.next=null;
		        while(curr.next!=null){
		        	curr=curr.next;
		        }
		        curr.next=head;
		        return pre.next;
		    }
		
		public static void main(String[] args) {
			ListNode root=new ListNode(1);
			int i=2;
			ListNode node=root;
			while(i<=5){
				node.next=new ListNode(i++);
				node=node.next;
			}
			root=new RotateList().rotateRight(root, 3);
			while(root!=null){
				System.out.println(root.val);
                root=root.next;
			}
				
		}
}
