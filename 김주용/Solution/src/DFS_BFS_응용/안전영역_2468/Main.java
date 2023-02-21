package DFS_BFS_응용.안전영역_2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class Main {
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		int[][] board = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			board[i] = line;
		}
		
		int answer = solution(board, N);
		System.out.println(answer);
	}
	
	public static int solution(int[][] board, int N) {
		int ret = 0;
		
		for(int height = 0; height <= 100; height++) {
			boolean[][] waterBoard = getWaterBoard(board, N, height);
			ret = Math.max(ret, getStart(waterBoard, N));
			
		}
		
		
		return ret;
	}
	
	public static boolean[][] getWaterBoard (int[][] board, int N, int height) {
		boolean[][] waterBoard = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(board[i][j] <= height) {
					waterBoard[i][j] = true;
				}
			}
		}
		return waterBoard;
	}
	
	public static int getSafeZone(boolean[][] waterBoard, boolean[][] visited, int N, int y, int x) {
		int ret = 0;
		Queue<int []> queue = new LinkedList<>();
		queue.add(new int[] {y, x});
		visited[y][x] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i], nx = cur[1] + dx[i];
				if(-1 < ny && ny < N && -1 < nx && nx < N) {
					if(!waterBoard[ny][nx] && !visited[ny][nx]) {
						visited[ny][nx] = true;
						queue.add(new int[] {ny, nx});
					}
				}
			}
		
		}
		
		
		return ret;
	}
	
	public static int getStart(boolean[][] waterBoard, int N) {
		int ret = 0;
		boolean[][] visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!waterBoard[i][j] && !visited[i][j]) {
					
					getSafeZone(waterBoard, visited, N, i, j);
					ret++;
				}
			}
		}
		
		return ret;
	}

}
