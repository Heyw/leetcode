package leetcode.depthfirst;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * ����ָ������n���γ̣����Ϊ0-n-1��ÿ�ſγ̶����Ⱦ����������Ǳ���������ָ���γ̲���ѧϰ���ſ�,
 * �Ⱦ�������һ������Ա�ʾ����:[0,1]
 * (2,[0,1])��ʾ���ſγ���1�ſ���0�ſε��Ⱦ������������ǿ�������ÿγ�
 * (2,[0,1],[1,0])��ʾ0��1��Ϊ�Ⱦ������������޷�����γ̵�
 * 
 * ��ˣ�������������Ƿ��������
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
