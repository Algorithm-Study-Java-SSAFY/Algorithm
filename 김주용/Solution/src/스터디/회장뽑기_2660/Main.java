package 스터디.회장뽑기_2660;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int N;
	static int[][] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		graph = new int[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) {
					graph[i][j] = 0;
					continue;
				}
				graph[i][j] = (int) 1e9;
			}
		}
		while(true) {
			String[] line = in.readLine().split(" ");
			int from = Integer.parseInt(line[0]);
			int to = Integer.parseInt(line[1]);
			if(from == -1) {
				break;
			}
			graph[from][to] = 1;
			graph[to][from] = 1;
		}
		solution();
	}
	
	public static void solution() {
		// 플루이드 - 와샬 
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
		
		int minScore = (int) 1e9;
		int[] scores = new int[N+1];
		for(int i = 1; i <= N; i++) {
			int score = 0;
			for(int j = 1; j <= N; j++) {
				if(i == j) continue;
				score = Math.max(score, graph[i][j]);
			}
			scores[i] = score;
			minScore = Math.min(minScore, scores[i]);
		}
		
		
		
		int num = 0;
		for(int i = 1; i <= N; i++) {
			if(minScore == scores[i]) {
				num++;
			}
		}
		
		System.out.println(minScore + " " + num);
		for(int i = 1; i <= N; i++) {
			if(minScore == scores[i]) {
				System.out.print(i + " ");
			}
		}
	}
}
