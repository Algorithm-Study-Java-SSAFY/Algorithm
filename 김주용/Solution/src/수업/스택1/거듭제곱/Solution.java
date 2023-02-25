package 수업.스택1.거듭제곱;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {

	static int answer;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			int t = Integer.parseInt(in.readLine());
			String[] line = in.readLine().split(" ");
			int N = Integer.parseInt(line[0]), M = Integer.parseInt(line[1]);
			answer = 0;
			solution(N, M, N, 1);
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}

	public static void solution(int N, int M, int cur, int depth) {
		if (depth == M) {
			answer = cur;
			return;
		}
		
		solution(N, M, cur * N, depth + 1);
	}
}
