package leetcode.depthfirst;

/**
 * 该问题求得是如何移除边界，使得整个图变成一个directed tree；
 * directed tree指的是真个图形中只有一个根节点，其他都是子节点，子节点都只有一个父节点，除了根节点
 * 边界是可以用[u,v]对来表示，从u指向v，u是v的父节点,
 * 如果存在多条这样的边界，返回最后这样的边界
 Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3

Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
Output: [4,1]
Explanation: The given directed graph will be like this:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3
     remove [4,1] ，all graph will be a direct tree
 * @author Administrator
 *
 */
public class RedundantConnectionII685 {
	public int[] findRedundantDirectedConnection(int[][] edges) {
		return null;
	        
	    }
}
