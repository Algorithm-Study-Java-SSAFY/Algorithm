package 수업.문제풀이5.프로세서연결하기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Core {
	int y;
	int x;
	boolean[] connect;

	public Core(int y, int x) {
		this.y = y;
		this.x = x;
		this.connect = new boolean[4];
	}
}

public class Solution {

	static int N;
	static int[][] board;
	static ArrayList<Core> cores;
	static int M;

	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	static int coreNum;
	static int minLength;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(in.readLine());
			board = new int[N][N];
			cores = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				String[] line = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(line[j]);
					if (board[i][j] == 1 && j > 0 && i > 0 && i < N - 1 && j < N - 1) {
						cores.add(new Core(i, j));
					}
				}
			}
			M = cores.size();
			coreNum = 0;
			minLength = Integer.MAX_VALUE;
			solution(0, 0, 0);
			System.out.println(coreNum);
			System.out.printf("#%d %d\n", test_case, minLength);
		}
	}

	public static void solution(int idx, int cnt, int k) {
		if (idx == M || cnt == M) {
			if (cnt >= coreNum) {
				if (cnt == coreNum && k > minLength) {
					return;
				}
				coreNum = cnt;
				minLength = k;
			}
			return;
		}
		
		for(int [] l : board) {
			System.out.println(Arrays.toString(l));
		}
		System.out.println(" "
				+ "");

		for (int i = idx; i < M; i++) {
			Core cur = cores.get(i);
			for (int d = 0; d < 4; d++) {
				if (!cur.connect[d]) {
					cur.connect[d] = true;
					if (canConnection(cur.y, cur.x, d)) { // 연결 가능한지 확인 
						int len = connection(cur.y, cur.x, d, 2); // 연결
						solution(i + 1, cnt + 1, k + len);
						connection(cur.y, cur.x, d, 0); // 해제
					}
					cur.connect[d] = false;
				}
			}
		}
	}

	public static boolean canConnection(int y, int x, int d) {
		int ny = y, nx = x;
		while (true) {
			ny = ny + dy[d];
			nx = nx + dx[d];
			if (0 > ny || ny >= N || 0 > nx || nx >= N) {
				break;
			}
			if (board[ny][nx] != 0) {
				return false;
			}
		}
		return true;
	}

	public static int connection(int y, int x, int d, int value) {
		int ny = y, nx = x;
		int length = 0;
		while (true) {
			ny = ny + dy[d];
			nx = nx + dx[d];
			if (0 > ny || ny >= N || 0 > nx || nx >= N) {
				break;
			}
			board[ny][nx] = value;
			length++;
		}
		return length;
	}
}
