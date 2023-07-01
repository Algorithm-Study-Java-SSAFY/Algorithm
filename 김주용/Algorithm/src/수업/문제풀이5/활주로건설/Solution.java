package 수업.문제풀이5.활주로건설;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	
	static int N;
	static int X;
	static int[][] board;
	static int[][] colBoard;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			String[] line = in.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			X = Integer.parseInt(line[1]);
			board = new int[N][N];
			colBoard = new int[N][N];
			for(int i = 0; i < N; i++) {
				line = in.readLine().split(" ");
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(line[j]);
					colBoard[j][i] = Integer.parseInt(line[j]);
				}
			}
			int answer = solution();
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}
	
	public static int solution() {
		int answer = 0;
		for(int i = 0; i < N; i++) {
			int[] row = board[i];
			int[] col = colBoard[i];
			if(go(col)) {
				answer++;
			}
			if(go(row)) {
				answer++;
			}
		}
		return answer;
	}
	
	public static boolean go(int[] heights) {
		int idx = 0;
		int sameCnt = 1;
		while(idx < N - 1) {
			if(heights[idx] == heights[idx+1]) {	// 같을 때 
				sameCnt++;
			} else if(heights[idx] + 1 == heights[idx+1] && sameCnt >= X) { // 1 클때  
				sameCnt = 1;
			} else if (heights[idx] - 1 == heights[idx + 1]) { // 1 작을 때 
				for(int i = 1; i <= X; i++) {
					if(idx + i >= N) {
						return false;
					}
					if(heights[idx + i] != heights[idx] - 1) {
						return false;
					}
				}
				sameCnt = 0;
				idx += (X-1);
			} else {
				return false;
			}
			idx++;
			//System.out.println(idx);
		}
		return true;
	}
}
