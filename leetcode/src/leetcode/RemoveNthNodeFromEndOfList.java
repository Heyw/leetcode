package leetcode;

//从一个不知道长度的固定链表末端开始删除第n个元素，然后返回删除元素后的列表
//要求一遍循环就可实现
//复制两个相同链表，一个先走n个节点后另一个才开始从头遍历列表，当前前面那个链表遍历到尾端时，后面那个链表的下一个元素就是要删除的
public class RemoveNthNodeFromEndOfList {
	public ListNode removeNthFromEnd(ListNode head, int n) {
	        ListNode first=head;
	        ListNode second=head;
	        int index=n;
	        while(first.next!=null){
	        	first=first.next;
	        	if(index>0) index--;
	        	else{
	        		second=second.next;
	        	}
	        	
	        }
	        if(index==0){
		        ListNode delete=second.next;
		        second.next=(delete==null?null:delete.next);
		        delete=null;
	        }else{
	        	head=head.next;
	        }
	        return head;
	    }
	public static void main(String[] args) {
		int[] nums={1};
		ListNode head=new ListNode(1);
		ListNode curr=head;
		for(int i=1;i<nums.length;i++){
		    curr.next=new ListNode(nums[i]);
		    curr=curr.next;
		}
		curr=new RemoveNthNodeFromEndOfList().removeNthFromEnd(head,1);
	    while(curr!=null){
	    	System.out.println(curr.val);
	    	curr=curr.next;
	    };
	}
}

class ListNode {
   int val;
	ListNode next;
	ListNode(int x) { val = x; }
	 }
