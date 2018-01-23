package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 给定几对中括号()，就这这几队中括号能组成多少个完整的括号，如3对中括号(),有如下几种组合
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
	//解法思路：n对括号，循环2n次，如果左括号数多于右括号数，每次可以插入左括号或者右括号，
	//如果相等只能插入右括号，左括号数等于n，则只能插入右括号
	 public List<String> generateParenthesis(int n) {
		 //该map用来保存字符串和左括号数
		 Map<String,Integer> map=new HashMap<>();
		 List<String> list=new ArrayList<>();
		 for(int i=0;i<2*n;i++){
			 if(map.entrySet().size()==0){
				 map.put("(", 1);
			 }else{
			     Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();//遍历集合map同时修改元素，必须用迭代
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
