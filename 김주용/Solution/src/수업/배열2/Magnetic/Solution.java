package 수업.배열2.Magnetic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

class Solution {
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(in.readLine());

			int[][] board = new int[N][N];
			// 2는 위로 // 1은 아래로
			for (int i = 0; i < N; i++) {
				board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}

			int answer = solution(board, N);
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}

	public static int solution(int[][] board, int N) {
		int ret = 0;
		
		for(int x = 0; x < N; x++) {
			for(int y = 0; y < N-1; y++) {
				if(board[y][x] == 1) {
					for(int dy = 1; dy < N - y; dy++) {
						if (board[y + dy][x] == 2) {
							ret += 1;
							y = y + dy;
							break;
						}
					}
				}
			}
		}
	
		return ret;
	}
}
