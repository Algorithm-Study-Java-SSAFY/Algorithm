package 스터디.연결요소의개수_11724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int M;
	
	static int[][] graph;
	static boolean[] visited; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		graph = new int[N+1][N+1];
		visited = new boolean[N+1];
		for(int i = 0; i < M; i++) {
			int[] info = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			graph[info[0]][info[1]] = 1;
			graph[info[1]][info[0]] = 1;
		}
		
		int answer = 0;
		for(int node = 1; node < N+1; node++) {
			if(!visited[node]) {
				solution(node);
				answer++;
			}
		}
		System.out.println(answer);
	}

	public static void solution(int cur) {
		visited[cur] = true;
		for(int next = 1; next < N+1; next++) {
			if(graph[cur][next] == 1 && !visited[next]) {
				solution(next);
			}
		}
	}
}
