import java.util.*;
import java.io.*;

/*
 * 자신보다 큰 수가 나오면? => 기준 높이를 큰 수로 변경 
 * 이 전에 지나온 부분을 변경전 기준 높이로 계산 
 */


public class Main {
	
	static int H, W;
	static int[] HIGH;
	static int[] visited;
	static int maxH = 0;
	static int res = 0; 
	static List<Integer> xList;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		H = Integer.parseInt(s[0]);
		W = Integer.parseInt(s[1]);
		
		HIGH = new int[W];
		visited = new int[W];
		
		String[] s2 = br.readLine().split(" ");
		for(int i=0; i<s2.length; i++) {
			HIGH[i] = Integer.parseInt(s2[i]);
			if(maxH < HIGH[i]) maxH = HIGH[i];
		}
		
		// 높이를 maxH에서 하나씩 내리면서 동일한 높이의 벽이 존재하는지 확인 
		// 만약 존재한다면 두 벽사이에서 물이 찰 수 있는 양을 계산 
		for (int h=maxH; h>0; h--) {

			findSameHigh(h);
			
			// 같은 높이가 적어도 두개일때 물 채우기가 가능 
			if(xList.size() >= 2) {
				res += pour(h);	
			}
			else if(xList.size() == 1){ // 제일 높은 벽이 1개 뿐일 경우 -> 높이를 -1씩 해준다. & 방문을 하지 않은 것으로 변경한다.  
				HIGH[xList.get(0)] -= 1;
			}
		}
		
		System.out.println(res);
		
		
	}
	
	// 높이가 똑같은 곳의 위치를 찾고 방문 처리를 해준다. 
	static void findSameHigh(int h) {
		xList = new ArrayList<Integer>(); // 높이가 똑같은 곳들의 x축 좌표를 저장 
		for(int i=0; i<W; i++) {
			if (HIGH[i] == h) {
				xList.add(i);
			}
		}
	}
	
	static int pour(int h) {
		int cnt=0;
		int start = 0; 
		int end = 0; 
		for (int i=0; i<xList.size()-1; i++) {
			start = xList.get(i);
			end = xList.get(i+1);
			visited[xList.get(i)] = 1;
			visited[xList.get(i+1)] = 1;
			for (int s=start+1; s<end; s++) {
				if(visited[s]==0) { // 현재 기준 높이보다 높은 곳은 이미 방문처리가 되어 있음 
					visited[s] = 1;
					cnt += ( h - HIGH[s] ); 
				}
			}
			
		}
		visited[xList.get(0)] = 0; 
		visited[xList.get(xList.size()-1)] = 0; 
		HIGH[xList.get(0)] -= 1;
		HIGH[xList.get(xList.size()-1)] -= 1;
		
		return cnt; 
	}

	
	
}