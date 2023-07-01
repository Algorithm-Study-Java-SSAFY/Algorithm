package 스터디.친구비;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Main {
	
	static int N;
	static int M;
	static int K;
	static int[] costs;
	static boolean[] visited;
	
	static ArrayList<Integer>[] adj;

	static int curCost;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = line[0];
		M = line[1];
		K = line[2];
		costs = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		adj = new ArrayList[N+1];
		visited = new boolean[N+1];
		for(int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i < M; i++) {
			line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			adj[line[0]].add(line[1]);
			adj[line[1]].add(line[0]);
		}
		
//		for(int i = 1; i <= N; i++) {
//			for(int c : adj[i]) {
//				System.out.print(c + " ");
//			}
//			System.out.println("");
//		}
//		System.out.println("");
		System.out.println(solution());
		//System.out.println(answer);
	}
	
	public static String solution() {
		int answer = 0;
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				curCost = costs[i-1];
				dfs(i);
				K -= curCost;
				answer += curCost;
			}
		}
		if(K < 0) {
			return "Oh no";
		}
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				return "Oh no";
			}
		}
		return String.valueOf(answer);
	}
	
	public static void dfs(int cur) {
		if(costs[cur - 1] < curCost) {
			curCost = costs[cur - 1];
		}
		
		//System.out.println(cur);
		for(int next : adj[cur]) {
			if(!visited[next]) {
				visited[next] = true;
				dfs(next);
			}
		}
	}

}
