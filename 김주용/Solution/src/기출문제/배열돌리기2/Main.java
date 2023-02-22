package 기출문제.배열돌리기2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int M;
	static int R;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		R = Integer.parseInt(line[2]);

		int[][] board = new int[N][M];
		for (int i = 0; i < N; i++) {
			board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		solution(board);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		out.write(sb.toString())
		;
		in.close();
		out.close();
	}

	public static void solution(int[][] board) {
		int layer = Math.min(N, M) / 2;
		int[] rotateCnt = new int[layer]; 
		for (int i = 0; i < layer; i++) {
			int cnt = 2*(N - 1 - 2*i) + 2*(M - 1 - 2*i);//이만큼 돌리면 원래 상태로 돌아옴 
			rotateCnt[i] = R % cnt;
		}
		// 10000 
		boolean[][] visited = new boolean[N][M];
		
		for (int i = 0; i < layer; i++){
			
			rotate(board, i, rotateCnt[i], i, i);
		}

	}

	public static void rotate(int[][] board, int layer, int rotateCnt, int startY, int startX) {
		int[] dy = { 1, 0, -1, 0 };
		int[] dx = { 0, 1, 0, -1 };
		
		int minX = layer, maxX = M - layer;
		int minY = layer, maxY = N - layer;
		for(int i = 0; i < rotateCnt; i++) {
			int cy = startY, cx = startX;
			int cd = 0;
			int[][] newBoard = clone(board);
			while (true) {
				int ny = cy + dy[cd];
				int nx = cx + dx[cd];
				//System.out.printf("%d: %d, %d\n", cd, ny, nx);
				
				if (!(minY <= ny && ny < maxY && minX <= nx && nx < maxX)) {
					cd = (cd + 1) % 4;
					continue;
				}
				
				board[ny][nx] = newBoard[cy][cx];
				if (ny == startY && nx == startX) {
					board[ny][nx] = newBoard[cy][cx];
					break;
				}
				cy = ny;
				cx = nx;
			}
		}
	}

	public static int[][] clone(int[][] arr) {
		int[][] newArr = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newArr[i][j] = arr[i][j];
			}
		}
		return newArr;
	}
}
