package 수업.그래프.하나로;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Island {
	long y;
	long x;

	public Island(long y, long x) {
		this.y = y;
		this.x = x;
	}
}

public class Solution {

	static int N;
	static Island[] islands;
	static int[][] board;
	static int[][] visited;

	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(in.readLine());
			islands = new Island[N];
			for(int i = 0; i < N; i++) {
				String[] line = in.readLine().split(" ");
				islands[i] = new Island(Long.parseLong(line[0]), Long.parseLong(line[1]));
				
			}
		}
	}

}
