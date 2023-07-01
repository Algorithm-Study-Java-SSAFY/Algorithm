package 수업.큐.Contact;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

	static int N;
	static int[][] graph;

	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			String[] line = in.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			int start = Integer.parseInt(line[1]);

			graph = new int[101][101];
			line = in.readLine().split(" ");
			for (int i = 0; i < N; i += 2) {
				int a = Integer.parseInt(line[i]), b = Integer.parseInt(line[i + 1]);
				graph[a][b] = 1;
			}

			int answer = solution(start);
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}

	public static int solution(int start) {
		Queue<Integer> queue = new LinkedList<>();
		int[] visited = new int[101];
		queue.add(start);
		visited[start] = 1;

		int ret = 0;
		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int next = 1; next <= 100; next++) {
				if (graph[cur][next] == 1 && visited[next] == 0) {
					visited[next] = visited[cur] + 1;
					ret = Math.max(ret, visited[next]);
					queue.add(next);
				}
			}
		}
		
		for (int i = 100; i >= 1; i--) {
			if (visited[i] == ret) {
				return i;
			}
		}
		return -1;
	}
}
