package leetcode.depthfirst;

/**
 * ���������������Ƴ��߽磬ʹ������ͼ���һ��directed tree��
 * directed treeָ�������ͼ����ֻ��һ�����ڵ㣬���������ӽڵ㣬�ӽڵ㶼ֻ��һ�����ڵ㣬���˸��ڵ�
 * �߽��ǿ�����[u,v]������ʾ����uָ��v��u��v�ĸ��ڵ�,
 * ������ڶ��������ı߽磬������������ı߽�
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
     remove [4,1] ��all graph will be a direct tree
 * @author Administrator
 *
 */
public class RedundantConnectionII685 {
	public int[] findRedundantDirectedConnection(int[][] edges) {
		return null;
	        
	    }
}
