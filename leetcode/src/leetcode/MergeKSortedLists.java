package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MergeKSortedLists {
	public ListNode mergeKLists(ListNode[] lists) {
        ListNode list=null;
        ListNode root=null;
        TreeMap<Integer,ListNode> tree=new TreeMap<>();
        for(ListNode l:lists){
        	l=putSame(tree, l);
        	if(l!=null)
        	tree.put(l.val, l);
        	}
        ListNode curr=tree.firstEntry().getValue();
        while(curr!=null){
        	if(list==null){
        		list=new ListNode(curr.val);
        	    root=list;
        	}else{
        		list.next=new ListNode(curr.val);
        		list=list.next;
        	}
        	while(curr.next!=null&&curr.next.val==curr.val){
        		curr=curr.next;
        		list.next=new ListNode(curr.val);
        		list=list.next;
        	}
        	ListNode next=curr.next;
        	tree.remove(curr.val);
        	if(next!=null){
        	 next=putSame(tree, next);
        	if(next!=null)
        	 tree.put(next.val,next );
        	}
        	curr=tree.firstEntry()==null?null:tree.firstEntry().getValue();
        }
    
	    return root;
    }

private ListNode putSame(TreeMap<Integer,ListNode> tree,ListNode l){
	while(l!=null&&tree.containsKey(l.val)){//相同的值，插入前值所在list该值的后面
		ListNode next=l.next;//获取下一个值
		ListNode ll=  tree.get(l.val).next;
		tree.get(l.val).next=l;
		l.next=ll;
		l=next;
	  }
	return l;
}
        public ListNode mergeKLists1(ListNode[] lists) {
	        ListNode list=null;
	        ListNode root=null;
	        List<ListNode> arrList=new ArrayList<>();
	        while(hasNext(lists)){
	            int m=0;
	        	while(m<lists.length){
	        		if(lists[m]!=null)
	        			arrList.add(lists[m]);
	        		m++;
	        	}
	        	lists=arrList.toArray(new ListNode[0]);
	        	int x=lists[0].val;
	        	for(int i=1;i<lists.length;i++){
	        		if(lists[i]!=null&&lists[i].val<x){
	        			x=lists[i].val;
	        			m=i;
	        		}
	        	}
	        	if(list==null){
	        		list=new ListNode(x);
	        		root=list;
	        	}else{
	        		list.next=new ListNode(x);
	        		list=list.next;
	        	}
	        	lists[m]=lists[m].next;
	        }
		    return root;
	    }
	boolean hasNext(ListNode[] lists){
	   for(ListNode list:lists){
		   if(list!=null)
			   return true;
	   }
		return false;   
	}
	public ListNode mergeKLists3(ListNode[] lists) {
		ListNode root=null;
		ListNode curr=null;
		while(hasNext(lists)){
			List<ListNode> arrList=new ArrayList<>();
			int m=0;
			while(m<lists.length){
        		if(lists[m]!=null)
        			arrList.add(lists[m]);
        		m++;
        	}
        	lists=arrList.toArray(new ListNode[0]);
        	quickSortList(lists, 0, lists.length-1);
        	int i=0;
			if(curr==null){
				curr=lists[i];
				root=curr;
				lists[i]=lists[i].next;
			}else{
				curr.next=lists[i];
				curr=curr.next;
				lists[i]=lists[i].next;
			}
		}
		return root;
	}
	/**
	 * 使用快速排序算法对listNode进行排序
	 * @param lists
	 * @param l
	 * @param r
	 */
	private void quickSortList(ListNode[] lists,int l,int r){
		int i=l;
		int j=r;
		if(i>=r||lists==null||lists.length==0)
			return ;
		int key=lists[i++].val;
		while(true){
			//小于关键字都放在左边，大于关键字的都放在右边
			while(j>=i&&lists[j].val>=key)	j--;
			while(i<j&&lists[i].val<key) i++;
			if(i>=j) break;
			swap(lists,i,j);
			i++;j--;
		}
		swap(lists,l,j);//将关键数据放到中间
		quickSortList(lists, l, i-1);
		quickSortList(lists,j+1,r);
		
	}
	private void swap(ListNode[] lists,int i,int j){
		if(i>=j)
			return;
		ListNode temp=lists[i];
		lists[i]=lists[j];
		lists[j]=temp;
	}
	public ListNode mergeKLists4(ListNode[] lists) {
		return divide(lists,0,lists.length-1);
	}
	private ListNode divide(ListNode[] lists,int l,int r){
		if(l<r){
		     ListNode left=divide(lists,l,(l+r)/2);
		     ListNode right=divide(lists,(l+r)/2+1,r);
		     return mergeListTwo(left,right);
		}else if(l==r){
			return lists[l];
		}else 
			return null;
	}
	private ListNode mergeListTwo(ListNode list1,ListNode list2){
		if(list1==null) return list2;
		if(list2==null) return list1;
		if(list1.val<list2.val){
			list1.next=mergeListTwo(list1.next,list2);
			return list1;
		}
		else{
			list2.next=mergeListTwo(list1,list2.next);
			return list2;
		}
		
	}
	public static void main(String[] args) {
		ListNode list=new ListNode(0);
		list.next=new ListNode(2);
		list.next.next=new ListNode(5);
		ListNode list1=new ListNode(-2);
		list1.next=new ListNode(1);
		list1.next.next=new ListNode(6);
		ListNode list2=new ListNode(1);
		list2.next=new ListNode(5);
		list2.next.next=new ListNode(9);
//		ListNode li=new MergeKSortedLists().mergeKLists3(new ListNode[]{list,list1,list2});
		ListNode li=new MergeKSortedLists().mergeKLists4(new ListNode[]{new ListNode(1),new ListNode(0)});
		while(li!=null){
			System.out.println(li.val);
			li=li.next;
		}
		
	}
}
