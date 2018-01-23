package leetcode.linkedlist;

import java.util.Stack;

/**
 * 在k组内将数组反转过来
 * 1- 2 -3-4-5-6-7-8
 * k=2 :2-1-4-3-6-5-8-7
 * k=3:3-2-1-6-5-4-8-7
 * @author Administrator
 *
 */
public class SwapNodesInKGroup {
		public ListNode reverseKGroup(ListNode head, int k) {
		    if(head==null) return null;    
			Stack<ListNode> stack=new Stack<>();
		        ListNode node=head;
		        head=null;
		        int j=k;
		        while(j>0&&node!=null){
		        	stack.push(node);
		        	node=node.next;
		        	j--;
		        }
		        ListNode nextHead=node;
		        while(stack.size()>0){
		        	if(head==null){
		        		head=stack.pop();
		        		node=head;
		        	}
		        	else{
		        		node.next=stack.pop();
		        		node=node.next;
		        	}
		        }
		        node.next=reverseKGroup(nextHead, k);
		        return head;
		   }
		
		public ListNode reverseKGroup2(ListNode head, int k) {
			   ListNode node=head;
			    ListNode pre=new ListNode(-1);
			    pre.next=head;
			    int j=k;
			    while(j>1&&node!=null){
			    	node=node.next;
			    	j--;
			    }
			    if(node==null) return head;
			    while(pre.next!=node){//实现不断将head放到tail的后面，知道tail成为head，head就是pre.next;
			    ListNode temp=pre.next;
			    pre.next=temp.next;
			     temp.next=node.next;
			    node.next=temp;
			    }
			    head.next=reverseKGroup2(head.next,k);
			return node;
			
		}

		public static void main(String[] args) {
			ListNode root=new ListNode(1);
			int i=2;
			ListNode node=root;
			while(i<=2){
				node.next=new ListNode(i++);
				node=node.next;
			}
			root=new SwapNodesInKGroup().reverseKGroup2(root, 3);
			while(root!=null){
				System.out.println(root.val);
                root=root.next;
			}
				
		}
}
