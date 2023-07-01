package 백준.최단거리.끝나지않는파티;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {

	static int N;
	static int M;
	
	static int[][] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = line[0];
		M = line[1];
		
		graph = new int[N][N];
		for(int i = 0; i < N; i++) {
			line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			graph[i] = line;
		}
		getFloyd();
		for(int i = 0; i < M; i++) {
			line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int a = line[0] - 1, b = line[1] - 1;
			if(graph[a][b] <= line[2]) {
				System.out.println("Enjoy other party");
			} else {
				System.out.println("Stay here");
			}
		}
	}
	
	public static void getFloyd() {
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
	}

}
