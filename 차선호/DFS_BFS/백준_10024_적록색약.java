package codeTest;

import java.util.*;


import java.io.*;

public class Main {
	
	static int N;
	static char[][] graph;
	static boolean[][] visited;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new char[N][N];
		
		for(int i=0;i<N;i++) {
			String data = br.readLine();
			for(int j=0;j<N;j++) {
				graph[i][j] = data.charAt(j);
			}
		}
		
		visited = new boolean[N][N];
		int normal = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j]) {
					int[] start = {i,j};
					bfs(start);
					normal++;
				}
			}
		}
		System.out.println(normal);
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(graph[i][j]=='G') {
					graph[i][j] = 'R';
				}
			}
		}
		
		visited = new boolean[N][N];
		int special = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j]) {
					int[] start = {i,j};
					bfs(start);
					special++;
				}
			}
		}
		
		System.out.println(special);
		
	}
	
	static void bfs(int[] start) {
		Queue<int[]> needVisit = new LinkedList<>();
		needVisit.add(start);
		visited[start[0]][start[1]] = true;
		char ch = graph[start[0]][start[1]];
		
		while(!needVisit.isEmpty()) {
			int[] node = needVisit.poll();
			int x = node[0];
			int y = node[1];
			for(int i=0;i<4;i++) {
				int mx = x+dx[i];
				int my = y+dy[i];
				int[] mNode = {mx,my};
				if(0<=mx&&mx<N&&0<=my&&my<N)
					if(!visited[mx][my]&&graph[mx][my]==ch) {
						needVisit.add(mNode);
						visited[mx][my] = true;
				}
			}
		}
	}
}