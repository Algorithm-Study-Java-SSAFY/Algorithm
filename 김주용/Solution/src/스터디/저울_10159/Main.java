package 스터디.저울_10159;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N;
	static int M;
	static boolean[][] biggerGraph;
	static boolean[][] smallerGraph;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		
		biggerGraph = new boolean[N+1][N+1];
		smallerGraph = new boolean[N+1][N+1];
		visited = new boolean[N+1];
		for(int i = 0 ; i < M; i++) {
			String[] line = in.readLine().split(" ");
			int to = Integer.parseInt(line[0]);
			int from = Integer.parseInt(line[1]);
			biggerGraph[from][to] = true;
			smallerGraph[to][from] = true;
		}
		solution();
	}
	
	public static void solution() {
		for(int i = 1; i <= N; i++) {
			bfs(biggerGraph, i);
			bfs(smallerGraph, i);
			
			int ret = 0;
			for(int j = 1; j <= N; j++) {
				if(!visited[j]) {
					ret++;
				}
				visited[j] = false;
			}
			System.out.println(ret);
		}

	}
	
	public static void bfs(boolean[][] graph, int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int next = 1; next <= N; next++) {
				if(!visited[next] && graph[cur][next]) {
					visited[next] = true;
					queue.add(next);
				}
			}
		}
	}
}
