import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static String[][] board1, board2; // 오리지널 , 색약 전용
	static int[][] visited1, visited2; // 색약 , 색약 아닌 경우
	static int[] DY = { 0, 0, 1, -1 };
	static int[] DX = { 1, -1, 0, 0 };
	static int res1 = 0;
	static int res2 = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		board1 = new String[N][N];
		board2 = new String[N][N];
		visited1 = new int[N][N];
		visited2 = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] s = br.readLine().split("");
			board1[i] = s.clone();
			board2[i] = s.clone();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board2[i][j].equals("G")) { // 색약인 사람
					board2[i][j] = "R"; // G를 R로 통일
				}
			}
		}
		

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited1[i][j] == 0) { // 색약인 사람
					find1(i, j, board2[i][j]);
					res1++;
				}

				if (visited2[i][j] == 0) { // 색약 아닌 사람
					find2(i, j, board1[i][j]);
					res2++;
				}
			}
		}

		System.out.println(res2 + " " + res1);

	}

	// 색약인 사람 R,G이 같은 영역 취급
	static void find1(int y, int x, String color) {
		Deque<int[]> dq = new ArrayDeque<int[]>();
		dq.add(new int[] { y, x });
		visited1[y][x] = 1;

		while (!dq.isEmpty()) {
			int[] yx = dq.removeFirst();
			int py = yx[0];
			int px = yx[1];
			for (int i = 0; i < 4; i++) {
				if (py + DY[i] >= 0 && py + DY[i] < N && px + DX[i] >= 0 && px + DX[i] < N) {
					if (visited1[py + DY[i]][px + DX[i]] == 0) {
						
						if (board2[py + DY[i]][px + DX[i]].equals(color)) {
							visited1[py + DY[i]][px + DX[i]] = 1;
							dq.add(new int[] { py + DY[i], px + DX[i] });
						}

					}
				}
			}
		}

	}

	// 색약이 아닌 사람
	static void find2(int y, int x, String color) {
		Deque<int[]> dq = new ArrayDeque<int[]>();
		dq.add(new int[] { y, x });
		visited2[y][x] = 1;

		while (!dq.isEmpty()) {
			int[] yx = dq.removeFirst();
			int py = yx[0];
			int px = yx[1];
			for (int i = 0; i < 4; i++) {
				if (py + DY[i] >= 0 && py + DY[i] < N && px + DX[i] >= 0 && px + DX[i] < N) {
					if (visited2[py + DY[i]][px + DX[i]] == 0) {
						
						if (board1[py + DY[i]][px + DX[i]].equals(color)) {
							visited2[py + DY[i]][px + DX[i]] = 1;
							dq.add(new int[] { py + DY[i], px + DX[i] });
						}

					}
				}
			}
		}

	}

}