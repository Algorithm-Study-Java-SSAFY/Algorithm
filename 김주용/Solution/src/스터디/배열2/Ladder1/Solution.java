package 스터디.배열2.Ladder1;

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
		int ret = 0;

		for (int x = 0; x < 100; x++) {
			if(board[99][x] == 2) {
				return(go(board, x, 99));
			}
		}
	
		return ret;

	}
	
	public static int go(int[][] board, int x, int y) {
		int[] dy = { 0, 0, -1 };
		int[] dx = { 1, -1, 0 };
		boolean[][] visited = new boolean[100][100];
			
		while (true) {
			boolean flag = false;
			for (int i = 0; i < 3; i++) {
				int ny = y + dy[i], nx = x + dx[i];
				if (-1 < ny && ny < 100 && -1 < nx && nx < 100 && board[ny][nx] == 1 && !visited[ny][nx]) {
					visited[ny][nx] = true;
					y = ny;
					x = nx;
					flag = true;
					break;
				}
			}
			
			if(!flag) {
				return x;
			}
		}
		
		
	}
}
