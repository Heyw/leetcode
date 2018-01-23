package leetcode.dynamicprogram;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一组字符串数组，判断该数组中有多少个字符串是完全由其他至少两个字符串组成的
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 *Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".

 * @author Administrator
 *
 */
public class ConcatenatedWords {
		public List<String> findAllConcatenatedWordsInADict(String[] words) {
			List<String>list=new ArrayList<>();
			Set<String> set=new HashSet<>();
			if(words==null||words.length<=0) return null;
			int minLength=words[0].length();
			for(String s:words){
				if(s.length()<minLength)
					minLength=s.length();
				if(s.length()>=1)
				  set.add(s);
			}
		        for(String s:words){
		        	if(s.length()<2*minLength)
		        		continue;
		        	Integer[][] dp=new Integer[s.length()+1][s.length()+1];
		        	for(int i=minLength;i<=s.length();i++){
		        			if(concatenateNums(s,0,i,set,dp,minLength)){
		        				list.add(s);
		        				break;
		        			}
		        		}
		        	}
		        return list;
		        }

		private boolean concatenateNums(String s, int i, int j, Set<String> set,Integer[][] dp,int min ) {
			if(dp[i][j]!=null)
				return  false;
			if(set.contains(s.substring(i, j))&&min+j<=s.length()){
				dp[i][j]=1;
				if(!set.contains(s.substring(j,s.length()))){
					for(int k=j+min;k<=s.length();k++){
				     if(concatenateNums(s, j, k, set, dp,min))
				    	 return true;
					}
				}else{
					dp[j][s.length()]=dp[i][j]+1;
					set.add(s.substring(i,s.length()));
					return true;
				}
			}else{
				dp[i][j]= 0;
				return false;
			}
			return false;
		}
		/**82ms算法
		 * 
		 * @param args
		 */
		 public List<String> findAllConcatenatedWordsInADict2(String[] words) {
		        List<String> res = new ArrayList<>();
		        if(words == null || words.length == 0) return res;
		        TrieNode root = new TrieNode();
		        for(String s : words) add(root, s);
		        for(String s : words){
		            if(dfs(root, root, s, 0)) res.add(s);
		        }
		        return res;
		    }
		    
		    private boolean dfs(TrieNode root, TrieNode cur, String s, int index){
		    	//如果index已经是字符串s的尽头
		        if(index == s.length()) return cur.word != null && cur.word != s;
		        //如果curr子节点不包含s在index处的字符，那么说明不存在
		        if(cur.children[s.charAt(index)-'a'] == null) return false;
		        //cur.children[s.charAt(index)-'a'].word != null表明s.substring(0,index+1)包含了某个单词，然后接着从根节点判断s.charAt(index+1)后面是否还包含其他单词
		        if(cur.children[s.charAt(index)-'a'].word != null && dfs(root, root, s, index+1)) return true;
		        return dfs(root, cur.children[s.charAt(index)-'a'], s, index+1);
		    }
		    
		    class TrieNode{
		        String word;
		        TrieNode[] children;
		        TrieNode(){
		            word = null;
		            children = new TrieNode[26];
		        }
		    }
		    
		    private void add(TrieNode root, String s){
		        if(s.length()==0) return;
		        TrieNode cur = root;
		        for(int i = 0; i < s.length(); i++){
		            char c = s.charAt(i);
		            if(cur.children[c-'a'] == null) cur.children[c-'a'] = new TrieNode();
		            cur = cur.children[c-'a'];
		        }
		        cur.word = s;
		    }
		public List<String> findAllConcatenatedWordsInADict3(String[] words) { 
			List<String> list=new ArrayList<>();
			LetterNode root=new LetterNode();
			int min=words[0].length();
			for(String s:words){
				if(s.length()>=1) {addNode(root,s);min=min>s.length()?s.length():min;};
			}
			for(String s:words){
				if(s.length()<2*min) continue;
				if(isComposited(root,0,s)) list.add(s);
			}
			return list;
		}
		private boolean isComposited(LetterNode root, int index, String s) {
			LetterNode curr=root;
			for(int i=index;i<s.length();i++){
				 curr=curr.next[s.charAt(i)-'a'];//判断字符串s在i处是否存在在于字母树中
			     if(curr==null) return false;
			     if(curr.word!=null&&!curr.word.equals(s)&&isComposited(root, i+1, s)) return true; 
			     
			}
			return index>=s.length();
		}

		private void addNode(LetterNode root, String s) {
			LetterNode curr=root;
		     for(int i=0;i<s.length();i++){
		    	 if(curr.next[s.charAt(i)-'a']==null) curr.next[s.charAt(i)-'a']=new LetterNode();
		         curr=curr.next[s.charAt(i)-'a'];
		     }
		     curr.word=s;//将字符串s挂在了树上，字符串末尾字符标记该字符串
		}

		class LetterNode{//字母树
			String word;
			LetterNode[] next;
			LetterNode(){
				word=null;
				next=new LetterNode[26];//每个字母下面都有26个字母
			}
		}
		public static void main(String[] args) {
			String[] str={"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
			str=new String[]{"cat","dog","dogcat"};
//			str=new String[]{"a","b","ab","abc"};
			str=new String[]{""};
			List<String> list=new ConcatenatedWords().findAllConcatenatedWordsInADict3(str);
			for(String s:list)
				System.out.println(s);
		}
  }
