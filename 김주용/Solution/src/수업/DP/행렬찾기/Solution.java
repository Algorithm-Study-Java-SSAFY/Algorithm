package 수업.DP.행렬찾기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.stream.Stream;

class Square implements Comparable<Square> {
	int width;
	int height;
	int size;

	public Square(int width, int height) {
		this.width = width;
		this.height = height;
		this.size = width * height;
	}

	@Override
	public int compareTo(Square o) {
		if (this.size < o.size) {
			return -1;
		} else if (this.size == o.size) {
			if (this.height < o.height) {
				return -1;
			}
		}
		return 1;
	}

	@Override
	public String toString() {
		return height + " " + width + " ";
	}

}

public class Solution {

	static int N;
	static int[][] board;
	static boolean[][] visited;

	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(in.readLine());
			board = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			System.out.printf("#%d ", test_case);
			solution();
		}
	}

	public static void solution() {
		PriorityQueue<Square> queue = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] > 0 && !visited[i][j]) {
					queue.add(getSquare(i, j));
				}
			}
		}

		System.out.print(queue.size() + " ");
		while (!queue.isEmpty()) {
			System.out.print(queue.poll());
		}
		System.out.println(" ");
	}

	public static Square getSquare(int sy, int sx) {
		int height = sy, width = sx;
		while (height < N && board[height][sx] > 0) {
			visited[height][sx] = true;
			height++;
		}

		while (width < N && board[sy][width] > 0) {
			visited[sy][width] = true;
			for (int i = sy; i < height; i++) {
				visited[i][width] = true;
			}
			width++;
		}
		// System.out.println(sy + " " + sx + " " + height + " " + width);
		return new Square(width - sx, height - sy);
	}
}
