import java.util.*;


import java.io.*;

public class Main {
	
	static int N, M;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		
		graph = new int[N][M];
		for(int i=0;i<N;i++) {
			String[] data = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				graph[i][j] = Integer.parseInt(data[j]);
			}
		}
		
		int answer = 0;
		int cnt = 0;
		while(true) {
			List<int[]> changeList = new ArrayList<>();
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(graph[i][j] == 1) {
						//치즈 발견 -> dfs로 탐색해서 벽에 닿는지
						int[] start = {i,j};
						visited = new boolean[N][M];
						if(dfs(start)) { // 녹을 놈인지
							changeList.add(start);
						}
						
					}
				}
			}
			if(changeList.isEmpty()) {
				System.out.println(answer);
				System.out.println(cnt);
				break;
			}
			cnt = countCheeze();
			for(int[] node:changeList) {
				graph[node[0]][node[1]] = 0;
			}
			answer++;
		}

		
		
	}
	
	static boolean dfs(int[] start) {
		Stack<int[]> needVisit = new Stack<>();
		needVisit.add(start);
		visited[start[0]][start[1]] = true;
		
		while(!needVisit.isEmpty()) {
			int[] node = needVisit.pop();
			int x = node[0];
			int y = node[1];
			for(int i=0;i<4;i++) {
				int mx = x+dx[i];
				int my = y+dy[i];
				if(mx==0||mx==N-1||my==0||my==M-1) {
					return true;
				}
				if(0<=mx&&mx<N&&0<=my&&my<M)
					if(graph[mx][my]==0&&!visited[mx][my]) {
						int[] mNode = {mx,my};
						visited[mx][my] = true;
						needVisit.add(mNode);
				}
			}
			
		}
		return false;
	}
	
	static int countCheeze() {
		int cnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(graph[i][j]==1) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}