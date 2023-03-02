package code_test;


import java.util.*;
import java.io.*;


public class Main {
	
	static char[][] graph = new char[12][6];
	static boolean[][] visited;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static List<int[]> bombs;
	static int answer=0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<12;i++) {
			String input = br.readLine();
			for(int j=0;j<6;j++) {
				graph[i][j] = input.charAt(j);
			}
		}
		
		while(true) {
			visited = new boolean[12][6];
			bombs = new ArrayList<>();
			for(int i=0;i<12;i++) {
				for(int j=0;j<6;j++) {
					if(graph[i][j] != '.' && !visited[i][j]) {
						visited[i][j] = true;
						bfs(new int[] {i,j});
					}
				}
			}
			
			if(bombs.isEmpty()) {
				break;
			}else {
				//bombs들 폭파
				for(int[] bomb:bombs) {
					graph[bomb[0]][bomb[1]] = '.';
				}
				
				//그래프 떨구기
				downGraph();
			}
			answer++;
//			for(char[] c:graph) {
//				System.out.println(Arrays.toString(c));
//			}
//			System.out.println("-------------------");
			
		}
	
		

		
		
		System.out.println(answer);
		
	}
	
	static void bfs(int[] start) {
		Queue<int[]> needVisit = new LinkedList<>();
		List<int[]> unit = new ArrayList<>(); // 유닛 단위
		needVisit.add(start);
		visited[start[0]][start[1]] = true;
		unit.add(start);
		int cnt = 1;
		
		while(!needVisit.isEmpty()) {
			int[] node = needVisit.poll();
			for(int i=0;i<4;i++) {
				int mx = node[0]+dx[i];
				int my = node[1]+dy[i];
				if(0<=mx&&mx<12&&0<=my&&my<6 && !visited[mx][my]) {
					if(graph[mx][my]==graph[start[0]][start[1]]) {
						needVisit.add(new int[] {mx,my});
						unit.add(new int[] {mx,my});
						visited[mx][my] = true;
						cnt++;
					}
				}
			}
		}
		if(cnt >= 4) {
			for(int[] u:unit) {
				bombs.add(u);
			}
		}
	}
	
	static void downGraph() {
		for(int j=0;j<6;j++) {
			int sum = 0; // 누적 . 개수
			boolean flag = false; //.이 중간에 있는지
			for(int i=11;i>-1;i--) {
				if(!flag && graph[i][j]=='.') {
					flag = true;
					sum = 1;
				}
				else if(flag && graph[i][j]=='.') {
					sum++;
				}
				else if(flag && graph[i][j]!='.') {
					graph[i+sum][j] = graph[i][j];
					graph[i][j] = '.';
				}
			}
		}
	}
	
}