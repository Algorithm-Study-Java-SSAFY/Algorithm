package 스터디.적록색약_10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class Main {
	
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static int N;
	static char[][] board;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		board = new char[N][N];
		for(int i = 0; i < N; i++) {
			board[i] = in.readLine().toCharArray();
		}
		solution();
	}
	
	public static void solution() {
		int[] ret = new int[2];
		for(int can = 0; can < 2; can++) {
			boolean[][] visited = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						bfs(visited, i, j, can);
						ret[can]++;
					}
				}
			}
		}
		System.out.println(ret[0] + " " + ret[1]);
	}
	
	public static void bfs(boolean[][] visited, int y, int x, int can) {
		Queue<int []> queue = new LinkedList<>();
		queue.add(new int[] {y, x});
		visited[y][x] = true;

		while(!queue.isEmpty()) {
			int[] info = queue.poll();
			int cy = info[0], cx = info[1];
			
			for(int i = 0; i < 4; i++) {
				int ny = cy + dy[i], nx = cx + dx[i];
				if(-1 < ny && ny < N && -1 < nx && nx < N && !visited[ny][nx]) {
					if(can == 0 && !canDivide(board[cy][cx], board[ny][nx])) {
						continue;
					}
					if(can == 1 && !cannotDivide(board[cy][cx], board[ny][nx])) {
						continue;
					}
					visited[ny][nx] = true;
					queue.add(new int[] {ny, nx});
				}
			}
		}
	}
	
	public static boolean canDivide(char e1, char e2) {
		if(e1 == e2) {
			return true;
		}
		return false;
	}
	
	public static boolean cannotDivide(char e1, char e2) {
		if((e1 == 'R' || e1 == 'G') && (e2 == 'R' || e2 == 'G')) {
			return true;
		} 
		if(e1 == 'B' && e2 == 'B') {
			return true;
		}
		return false;
	}
	
}
