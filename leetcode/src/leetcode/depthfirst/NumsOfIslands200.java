package leetcode.depthfirst;
/**
 * 给定一个二维数组，由1和0组成，其中1及其相连的1组成的岛被0组成的谁包围着，求二维数组中共有多少个岛
 * 如果一个1周围有一个岛，那么这个1就属于这个岛
 * @author Administrator
 *
 */
public class NumsOfIslands200 {
	int num=0;
    public int numIslands(char[][] grid) {
    	if(grid==null) return 0;
        for(int i=0;i<grid.length;i++){
        	for(int j=0;j<grid[0].length;j++){
        		if(grid[i][j]=='1'){
        			helper(grid,i,j);
        			num++;
        		}
        	}
        }
        return num;
    }
    
    public void helper(char[][] grid,int i,int j){
    	if(i>=0&&i<grid.length&&j>=0&&j<grid[0].length){
    		if(grid[i][j]=='1'){
    			grid[i][j]='x';
    			helper(grid,i+1,j);
    			helper(grid,i-1,j);
    			helper(grid,i,j+1);
    			helper(grid,i,j-1);
    		}
    	}
    }
    public static void main(String[] args) {
		String[] strs=new String[]{"10000","01000","00100","00001"};
		char[][] grid=new char[strs.length][strs[0].length()];
		for(int i=0;i<grid.length;i++){
			grid[i]=strs[i].toCharArray();
		}
		System.out.println(new NumsOfIslands200().numIslands(grid));
	}
}
