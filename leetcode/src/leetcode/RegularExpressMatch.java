package leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

public class RegularExpressMatch {
	/**
	 * "."可以匹配任意单个字符
	 * "*"可以前面字符可以出现零个或者多个
	 * @param s
	 * @param p
	 * @return
	 */
      public Boolean isMatch(String s,String p){
    	  char x='*';
    	  char d='.';
    	  Node root=null;
    	  Node curr=null;
    	  int marchNum=0;//表示p字符串有后面字符不为*的字符数;
    	   for(int i=0;i<p.length();i++){
    		  int num=0;
    		  while(i+num+1<p.length()&&p.charAt(i+num+1)==x){
    			   num++;
    		  }
    		  Node node=new Node(p.charAt(i),num);
    		  if(num==0)marchNum++;
    		  if(root==null){
    			  curr=root=node;
    		  }else{
    			  curr.setNext(node);
    			  curr=node;
    		  }
    		  i+=num;
    	   }
    	   int j=0;
    	   Node now=root;
    	   s=s.trim();
    	   if("".equals(s)){//处理s为空的情况
				while(now!=null&&now.getNum()>0)
					now=now.next;
				if(now==null)
					return true;
				else
					return false;
   		  }
   		  if(marchNum>s.length())
   		    return false;
    	  char last;
    	  do{
	    		 if(now!=null&&now.getC()==x)//避免***情况
	    			  now=now.getNext();
	    		if(now!=null&&now.getC()==d&&now.getNum()>0){//处理.*情况
	    			now=now.getNext();
	    			while(now!=null&&now.getNum()>0)
	    				now=now.getNext();
	    			if(now==null)
	    				return true;
	    			while(now!=null&&j<s.length()&&now.getC()!=s.charAt(j)&&now.getC()!=d){
	    				 j++;
	    			}
	    			if(j>=s.length())
	    				return false;
	    		}
	    		if(now==null)
	    			return false;
	    		if(now.getC()==d||now.getC()==s.charAt(j)){
	    			j++;
	    			if(now.getNum()>0){
	    			    while(j<s.length()&&now.getC()==s.charAt(j)){
	    			    	j++;
	    			    }
	    			}
	    			
	    		}else if(now.getNum()==0){
	    			return false;
	    		}
	    		now=now.getNext();
	    		last=s.charAt(j<s.length()?j:j-1);
    	  }while(j<s.length());
    	  while(now!=null&&now.getNum()>0)
    		  now=now.getNext();
    	  if(now==null||now.getC()==last)
    		  return true;
    	  else
    		  return false;
      }
    public boolean isMatch2(String s,String p){
    	int m = s.length(), n = p.length();
		boolean[] res = new boolean[n + 1];
		res[0] = true;

		int i, j;
		for (j = 2; j <= n; j++)
			res[j] = res[j - 2] && p.charAt(j - 1) == '*';

		char pc, sc, tc;
		boolean pre, cur; // pre=res[i - 1][j - 1], cur=res[i-1][j]

		for (i = 1; i <= m; i++) {
			pre = res[0];
			res[0] = false;
			sc = s.charAt(i - 1);

			for (j = 1; j <= n; j++) {
				cur = res[j];
				pc = p.charAt(j - 1);
				if (pc != '*')
					res[j] = pre && (sc == pc || pc == '.');
				else {
					// pc == '*' then it has a preceding char, i.e. j>1
					tc = p.charAt(j - 2);
					res[j] = res[j - 2] || (res[j] && (sc == tc || tc == '.'));
				}
				pre = cur;
			}
		}

		return res[n];
    }
    public boolean isMatch3(String s, String p) {

        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i-1]) {
                dp[0][i+1] = true;
            }
        }
        for (int i = 0 ; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.') {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == s.charAt(i)) {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    } else {
                        dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
    public static void main(String[] args) {
		System.out.println(new RegularExpressMatch().isMatch("ab", ".*.."));
	}
    class Node {
    	char c;
		int num;
    	Node next;
    	public Node(char c,int num){
    		this.c=c;
    		this.num=num;
    	}
    	public void setNext(Node node){
    		this.next=node;
    	}
    	public Node getNext(){
    		return  next;
    	}
    	public char getC() {
			return c;
		}
		public void setC(char c) {
			this.c = c;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
    }
}
