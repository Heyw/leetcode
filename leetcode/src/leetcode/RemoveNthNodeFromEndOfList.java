package leetcode;

//��һ����֪�����ȵĹ̶�����ĩ�˿�ʼɾ����n��Ԫ�أ�Ȼ�󷵻�ɾ��Ԫ�غ���б�
//Ҫ��һ��ѭ���Ϳ�ʵ��
//����������ͬ����һ������n���ڵ����һ���ſ�ʼ��ͷ�����б���ǰǰ���Ǹ����������β��ʱ�������Ǹ��������һ��Ԫ�ؾ���Ҫɾ����
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
