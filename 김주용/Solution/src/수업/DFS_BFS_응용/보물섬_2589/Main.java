package DFS_BFS_응용.보물섬_2589;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] line = in.readLine().split(" ");
		int N = Integer.parseInt(line[0]), M = Integer.parseInt(line[1]);
		char[][] board = new char[N][M];
		for (int i = 0; i < N; i++) {
			board[i] = in.readLine().toCharArray();
		}

		int answer = solution(board, N, M);

		System.out.println(answer);

	}

	public static int solution(char[][] board, int N, int M) {
		int answer = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 'L') {
					int ret = bfs(board, N, M, i, j);
					answer = Math.max(answer, ret);
				}
			}
		}
		return answer;
	}

	public static int bfs(char[][] board, int N, int M, int y, int x) {
		int[] dy = { 1, -1, 0, 0 };
		int[] dx = { 0, 0, 1, -1 };
		int ret = 0;
		Queue<int[]> queue = new LinkedList<int[]>();
		int[][] visited = new int[N][M];
		queue.add(new int[] { y, x });
		visited[y][x] = 1;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i], nx = cur[1] + dx[i];
				if (-1 < ny && ny < N && -1 < nx && nx < M) {
					if (board[ny][nx] == 'L' && visited[ny][nx] == 0) {
						visited[ny][nx] = visited[cur[0]][cur[1]] + 1;
						ret = Math.max(ret, visited[ny][nx] - 1);
						queue.add(new int[] { ny, nx });
					}
				}
			}
		}

		return ret;

	}
}
