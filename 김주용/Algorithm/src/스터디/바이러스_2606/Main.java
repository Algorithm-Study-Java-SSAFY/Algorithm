package 스터디.바이러스_2606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;


public class Main {
	static int N;
	static int[][] graph;
	static boolean[] visited;
	
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		int edgeNum = Integer.parseInt(in.readLine());
		graph = new int[N+1][N+1];
		visited = new boolean[N+1];
		for(int i = 0; i < edgeNum; i++) {
			int[] info = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			graph[info[0]][info[1]] = 1;
			graph[info[1]][info[0]] = 1;
		}
		solution(1);
		System.out.println(--answer);
	}
	
	public static void solution(int cur) {
		visited[cur] = true;
		answer++;
		
		for(int next = 1; next < N+1; next++) {
			if(graph[cur][next] == 1 && !visited[next]) {
				solution(next);
			}
		}
	}
}
