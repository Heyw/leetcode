package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * ����һ����������a[n]������Ϊn,����0<=a[i]<n
 * @author yiwuhe
 *
 */
public class Offser03DuplicateElementArray {

	/**
	 * ��Ϊ����Ԫ����[0,n-1]֮�䣬���ǿ�����Ϊ����Ԫ��ֵa[m]������i֮����ڶ��1��ӳ�䣻
	 * ���ǿ���ͨ��ԭ�ؽ����ķ�ʽ����a[m]������a[a[m]]����ʹ��a[m]=m;
	 * ��ô�ظ�Ԫ�ؾͳ����ڽ����������У���Ȼ���a[i]=i��˵����ʱ������ֵ�Ѿ�ӳ�����ˣ����轻�������a[a[i]]!=a[i]����ô���Խ��н���������������ʾ�������ظ���
	 * @param a
	 */
	public void swapIndex(int[] a) {
		
		List<Integer> dups=new ArrayList<>();
		for(int i=0;i<a.length;i++) {
			
			while(a[i]!=i) {//ÿ�ν�����a[i]���ḳֵΪa[a[i]]��ֵ,��a[a[i]]��һ������i,���Ե�ѭ����ȥ��
				
				if(a[a[i]]!=a[i]) {
					int tmp=a[a[i]];
					a[a[i]]=a[i];
					a[i]=tmp;
				}else {
					dups.add(a[i]);
					break;
				}
			}
			
		}
		System.out.println(dups);
	}
	public static void main(String[] args) {
		new Offser03DuplicateElementArray().swapIndex(new int[] {2,2,4,3,3,1,1});
	}
}
