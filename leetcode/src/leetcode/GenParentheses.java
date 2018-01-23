package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ��������������()�������⼸������������ɶ��ٸ����������ţ���3��������(),�����¼������
 * [
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 * @author Administrator
 *
 */
public class GenParentheses {
	//�ⷨ˼·��n�����ţ�ѭ��2n�Σ��������������������������ÿ�ο��Բ��������Ż��������ţ�
	//������ֻ�ܲ��������ţ�������������n����ֻ�ܲ���������
	 public List<String> generateParenthesis(int n) {
		 //��map���������ַ�������������
		 Map<String,Integer> map=new HashMap<>();
		 List<String> list=new ArrayList<>();
		 for(int i=0;i<2*n;i++){
			 if(map.entrySet().size()==0){
				 map.put("(", 1);
			 }else{
			     Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();//��������mapͬʱ�޸�Ԫ�أ������õ���
			     map=new HashMap<>();
			    while(it.hasNext()){
			    	Map.Entry<String, Integer> entry=it.next();
			    	if(entry.getValue()==n){
			    		map.put(entry.getKey()+")", entry.getValue());
			    	}else{
			    		if(entry.getValue()==entry.getKey().length()-entry.getValue())
			    			map.put(entry.getKey()+"(", entry.getValue()+1);
			    		else if(entry.getValue()>entry.getKey().length()-entry.getValue()){
			    			map.put(entry.getKey()+")", entry.getValue());
			    			map.put(entry.getKey()+"(", entry.getValue()+1);
			    		}
			    	}
			    	it.remove();
			    }
			 }
		 }
		 list.addAll(map.keySet());
	      return list;
	    }
	 public static void main(String[] args) {
		System.out.println(new GenParentheses().generateParenthesis(3));
	}
}
