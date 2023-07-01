package 수업.스택2.작업순서;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
	static int V;
	static int E;
	static int[][] graph;
	static int[] table;

	public static void main(String args[]) throws Exception {

		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			String[] line = in.readLine().split(" ");
			V = Integer.parseInt(line[0]);
			E = Integer.parseInt(line[1]);
			graph = new int[V + 1][V + 1];
			table = new int[V + 1];
			line = in.readLine().split(" ");
			for (int i = 0; i < 2 * E; i += 2) {
				int a = Integer.parseInt(line[i]), b = Integer.parseInt(line[i + 1]);
				graph[a][b] = 1;
				table[b] += 1;
			}
			String answer = solution();
			out.append("#" + test_case + " " + answer);
		}
		in.close();
		out.close();
	}

	public static String solution() {
		StringBuilder ret = new StringBuilder();
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int node = 1; node <= V; node++) {
			if (table[node] == 0) {
				queue.add(node);
			}
		}

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			ret.append(cur + " ");
			for (int next = 1; next <= V; next++) {
				if (graph[cur][next] == 1) {
					table[next] -= 1;
					if (table[next] == 0) {
						queue.add(next);
					}
				}
			}
		}
		return ret.toString();
	}

}