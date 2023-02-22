package 배열2.Sum;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Solution {
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			int t = Integer.parseInt(in.readLine());
			int[][] board = new int[100][100];
			for (int i = 0; i < 100; i++) {
				board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}

			int answer = solution(board);
			System.out.printf("#%d %d\n",test_case, answer);
		}
	}
	
	public static int solution(int[][] board) {
		int[] rowSum = new int[100];
		int[] colSum = new int[100];
		int rcSum = 0;
		int lcSum = 0;
		for(int i = 0; i < 100; i++) {
			rcSum += board[i][i];
			lcSum += board[i][99-i];
			for(int j = 0; j < 100; j++) {
				rowSum[i] += board[i][j];
				colSum[i] += board[j][i];
			}
		}
		int max = Math.max(rcSum, lcSum);
		for(int i = 0; i < 100; i++) {
			max = Math.max(max, rowSum[i]);
			max = Math.max(max, colSum[i]);
		}
		
		return max;
	}
}
