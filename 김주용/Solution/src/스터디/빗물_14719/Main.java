package 스터디.빗물_14719;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int M;
	static int[][] board;
	
	static int[] heights;
	static ArrayList<Integer> idxList = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		board = new int[N+1][M+1];
		line = in.readLine().split(" ");
		heights = Stream.of(line).mapToInt(Integer::parseInt).toArray();
		for(int x = 0; x < M; x++) {
			for(int y = 0; y <= heights[x]; y++) {
				board[y][x] = 1;
			}
		}
		solution();
	}
	
	public static void solution() {

	}
}
