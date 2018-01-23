package leetcode.depthfirst;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 该题指的是有n个课程，编号为0-n-1，每门课程都有先决条件，就是必须先修完指定课程才能学习改门课,
 * 先决条件用一个数组对表示，如:[0,1]
 * (2,[0,1])表示两门课程中1门课是0门课的先决条件，这样是可以修完该课程
 * (2,[0,1],[1,0])表示0，1互为先决条件，故是无法修完课程的
 * 
 * 如此，求给定条件中是否能修完课
 * @author Administrator
 *
 */
public class CourseSchedule207 {
		public boolean canFinish(int numCourses, int[][] prerequisites) {
		        Map<Integer,Set<Integer>> map=new HashMap<>();
		        for(int j=0;j<prerequisites.length;j++){
		        	if(map.containsKey(prerequisites[j][0]))
		        	    map.get(prerequisites[j][0]).add(prerequisites[j][1]);
		        	else{
		        		Set<Integer> set=new HashSet<>();
		        		set.add(prerequisites[j][1]);
		        		map.put(prerequisites[j][0], set);
		        	}
		        }
		    Boolean[] isvisited=new Boolean[numCourses];
		    for(int i=0;i<numCourses;i++){
		    	Set<Integer> visitedSet=new HashSet<>();
		    	if(!helper(map, isvisited,visitedSet,i))
		    	   return false;
		      }
		    return true;
		    }
		private boolean helper(Map<Integer,Set<Integer>>map,Boolean[] isvisited,Set<Integer> visitedSet,int goal){
			if(isvisited[goal]!=null)
				return isvisited[goal];
			if(visitedSet.contains(goal))
				 return false;
			visitedSet.add(goal);
			if(map.get(goal)!=null&& map.get(goal).size()>0){
				for(Integer i:map.get(goal)){
					if(!helper(map,isvisited,visitedSet,i))
						return false;
				}
			}
			isvisited[goal]=true;
			return isvisited[goal];
		}
		public static void main(String[] args) {
			int[][] pre=new int[][]{{0,1},{1,2},{0,2},{1,3},{2,3},{3,0}};
			System.out.println(new CourseSchedule207().canFinish(4, pre));
			
		}
}
