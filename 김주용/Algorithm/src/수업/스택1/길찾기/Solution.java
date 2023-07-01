package 수업.스택1.길찾기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
	static int[][] graph;
	static boolean[] visited;
	
	static int answer;

	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			graph = new int[100][100];
			visited = new boolean[100];
			String[] line = in.readLine().split(" ");
			int N = Integer.parseInt(line[1]);
			line = in.readLine().split(" ");
			answer = 0;
			for (int i = 0; i < N * 2; i += 2) {
				int a = Integer.parseInt(line[i]), b = Integer.parseInt(line[i + 1]);
				graph[a][b] = 1;
			}
			solution(0);
			System.out.printf("#%d %d\n",test_case,answer);
		}
		
	}
	
	public static void solution(int cur) {
		if(cur == 99) {
			answer = 1;
			return;
		}
		
		visited[cur] = true;
		
		for(int next = 0; next < 100; next++) {
			if(!visited[next] && graph[cur][next] == 1) {
				solution(next);
			}
		}
	}
}
