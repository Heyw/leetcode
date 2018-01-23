package leetcode.binarysearch;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 该问题就是给一定一组列长为2的二维数组a[x][2],其中a[i][0]，代表信封长，a[i]代表信封宽，如果一个信封长和宽都大于另外一个，
 * 那么就说该信封能套住另外一个，如果一个套住一个，里面的信封接着套住另一个，则深度加一，求给定数组深度最多是？
 * @author Administrator
 *
 */
public class RussianDollEnvelopes {
        /**
         *利用dp，treenode保存最大深度在count里面,通过测试
         */
   public int maxEnvelopes(int[][] envelopes) {
	   if(envelopes==null||envelopes.length==0||envelopes[0]==null||envelopes[0].length<2) return 0;
        TreeNode[] nodes=new TreeNode[envelopes.length];
        for(int i=0;i<envelopes.length;i++){
        	nodes[i]=new TreeNode(envelopes[i][0], envelopes[i][1]);
        }
        int max=0;
        Arrays.sort(nodes,new Comparator<TreeNode>(){
			@Override
			public int compare(TreeNode o1, TreeNode o2) {
				return o1.wight==o2.wight&&o1.height==o2.height?0:o1.wight>o2.wight||o1.wight==o2.wight&&o1.height>o2.height?1:-1;
			}});
        for(int i=0;i<nodes.length;i++){
        	max=Math.max(max,getMaxCount(nodes, i, 1));
        	System.out.println(nodes[i].wight+" "+nodes[i].height+" "+nodes[i].count);
        }
        return max;
    }
   
   private int getMaxCount(TreeNode[] nodes,int index,int count){
	   if(nodes[index].count>0) return nodes[index].count;
	   int max=count;
	   for(int i=index+1;i<nodes.length;i++){
		   if(nodes[i].wight>nodes[index].wight&&nodes[i].height>nodes[index].height){
			   max=Math.max(getMaxCount(nodes, i, 1)+count, max);
		   }
	   }
	   nodes[index].count=max;
	   return nodes[index].count;
   }
   
     class TreeNode {
         int wight;
         int height;
         int count;
    	 public TreeNode(int wigth,int height){
    		 this.wight=wigth;
    		 this.height=height;
    	 }
     }
     
     
    

 	/**
 	 * 利用二分查找法优化
 	 * @param envelopes
 	 * @return
 	 */
 	public int maxEnvelopesByBS(int[][] envelopes) {
 		if (envelopes == null || envelopes.length < 1){
 			return 0;
 		}
 		envelopes = radixSort(envelopes);
 		
 		int length = 0;
 		int[] dp = new int[envelopes.length];
 		for (int[] envelope : envelopes) {
 			int index = Arrays.binarySearch(dp, 0, length, envelope[1]);
 			if (index < 0){
 				index = -(index + 1);
 			}
 			dp[index] = envelope[1];
 			if (index == length){
 				length++;
 			}
 		}
 		return length;
 	}
 	 private static int[][] sort(int[][] envelopes, int minValue, int range, int sortBy) {

  		int[] count = new int[range + 1];

  		for (int[] envelope : envelopes) {
  			count[envelope[sortBy] - minValue + 1]++;
  		}

  		for (int i = 0; i < count.length - 1; i++) {
  			count[i + 1] += count[i];
  		}

  		int[][] sortedEnvelopes = new int[envelopes.length][envelopes[0].length];

  		for (int[] envelope : envelopes) {
  			sortedEnvelopes[count[envelope[sortBy] - minValue]][0] = envelope[0];
  			sortedEnvelopes[count[envelope[sortBy] - minValue]++][1] = envelope[1];
  		}
  		
  		if(sortBy == 0){
  			return sortedEnvelopes;
  		}

  		int[] tmp;
  		int j =  sortedEnvelopes.length - 1;
  		for (int i = 0; i < (sortedEnvelopes.length >> 1); i++, j--) {
  			tmp = sortedEnvelopes[i];
  			sortedEnvelopes[i] = sortedEnvelopes[j];
  			sortedEnvelopes[j] = tmp;
  		}
  		return sortedEnvelopes;
  	}
 	public int maxEnvelopesByDP(int[][] envelopes) {
 	    if(envelopes==null||envelopes.length==0)
 	        return 0;
 	    Arrays.sort(envelopes,new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0]!=o2[0]) return o1[0]-o2[0];
				else return o2[1]-o1[1];
			}
		});
 	    int max=1;
 	    int[] arr = new int[envelopes.length];
 	    for(int i=0; i<envelopes.length; i++){
 	        arr[i]=1;
 	        for(int j=i-1; j>=0; j--){
 	            if(envelopes[i][0]>envelopes[j][0]&&envelopes[i][1]>envelopes[j][1]){
 	                arr[i]=Math.max(arr[i], arr[j]+1);
 	            }
 	        }
 	        max = Math.max(max, arr[i]);
 	    }
 	    return max;
 	}
  	public static int[][] radixSort(int[][] envelopes) {
  		int minW = Integer.MAX_VALUE, maxW = Integer.MIN_VALUE;
  		int minH = Integer.MAX_VALUE, maxH = Integer.MIN_VALUE;
  		for (int[] envelope : envelopes) {
  			minW = minW < envelope[0] ? minW : envelope[0];
  			maxW = maxW > envelope[0] ? maxW : envelope[0];
  			minH = minH < envelope[1] ? minH : envelope[1];
  			maxH = maxH > envelope[1] ? maxH : envelope[1];
  		}
  		envelopes = sort(envelopes, minH, maxH - minH + 1, 1);
  		envelopes = sort(envelopes, minW, maxW - minW + 1, 0);
  		return envelopes;
  	}
   public static void main(String[] args) {
	  int[][] envelopes={{2,100},{3,200},{4,300},{5,500},{5,400},{5,250},{6,370},{6,360},{7,380}};
//	  envelopes=new int[][]{{15,8},{2,20},{2,14},{4,17},{8,19},{8,9},{5,7},{11,19},{8,11},{13,11},{2,13},{11,19},{8,11},{13,11},{2,13},{11,19},{16,1},{18,13},{14,17},{18,19}};
	  System.out.println(new RussianDollEnvelopes().maxEnvelopesByBS(envelopes));
}
}
