package 수업.문제풀이6.보호필름;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Solution {
	static int D;
	static int W;
	static int K;
	static int[][] cells;
	static int[][] copyCells;

	static int answer;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			D = line[0];
			W = line[1];
			K = line[2];
			cells = new int[D][W];
			copyCells = new int[D][W];
			int[] visited = new int[D];
			for (int i = 0; i < D; i++) {
				visited[i] = -1;
				cells[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			// System.out.println(check());
			answer = Integer.MAX_VALUE;
			if(K == 1) {
				answer = 0;
			} else {
				solution(visited, 0, 0);
			}
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}

	public static void solution(int[] visited, int idx, int cnt) {
		inputDrug(visited);
		if (check()) {
			answer = Math.min(answer, cnt);
		}
		
		if (idx == D) {
			return;
		}

		for (int i = idx; i < D; i++) {
			if (visited[i] == -1 && cnt < answer) {
				visited[i] = 1;
				solution(visited, i + 1, cnt + 1);

				visited[i] = 0;
				solution(visited, i + 1, cnt + 1);

				visited[i] = -1;
			}
		}
	}

	public static void inputDrug(int[] visited) {
		copy();
		for (int i = 0; i < D; i++) {
			if (visited[i] == -1) {
				continue;
			}
			for (int j = 0; j < W; j++) {
				copyCells[i][j] = visited[i];
			}
		}
	}

	public static boolean check() {
		for (int i = 0; i < W; i++) {
			int cnt = 1, flag = 0;
			for (int j = 0; j < D - 1; j++) {
				if (copyCells[j][i] == copyCells[j + 1][i]) {
					cnt++;
				} else {
					cnt = 1;
				}
				if (cnt >= K) {
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				return false;
			}
		}
		return true;
	}

	public static void copy() {
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				copyCells[i][j] = cells[i][j];
			}
		}
	}
}
