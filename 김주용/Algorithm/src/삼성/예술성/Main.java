package 삼성.예술성;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

class Group {
	int name;
	int value;
	int num;

	public Group(int name, int value, int num) {
		this.name = name;
		this.value = value;
		this.num = num;
	}

	@Override
	public String toString() {
		return "Group [name=" + name + ", value=" + value + ", num=" + num + "]";
	}

}

public class Main {
	static int N;
	static int[][] board;
	static int[][] visited;
	static boolean[][] visited2;
	static int[][] adj;
	
	static int name;

	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		board = new int[N][N];
		visited = new int[N][N];
		for (int i = 0; i < N; i++) {
			board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		solution();
	}

	public static void solution() {
		int answer = 0;
		for (int i = 0; i < 4; i++) {
			init();
			ArrayList<Group> groups = getGroup();
			int ret = getScore(groups);
			answer += ret;
			rotate1();
			rotate2();
		}
		System.out.println(answer);
	}

	public static void rotate1() {
		int[] row = new int[N];
		int[] col = new int[N];
		for (int i = 0; i < N; i++) {
			col[i] = board[N / 2][N - i - 1];
			row[i] = board[i][N / 2];
		}
		for (int i = 0; i < N; i++) {
			board[N / 2][i] = row[i];
			board[i][N / 2] = col[i];
		}
	}

	public static void rotate2() {
		int sy = 0, sx = 0;
		int len = N / 2;
		int[] dy = { 0, N / 2 + 1, N / 2 + 1 };
		int[] dx = { N / 2 + 1, 0, N / 2 + 1 };
		for (int k = 0; k < 4; k++) {
			int[][] part = new int[len][len];
			for (int i = sy; i < sy + len; i++) {
				for (int j = sx; j < sx + len; j++) {
					part[j-sx][len - (i - sy) - 1] = board[i][j];
				}
			}
			for (int i = 0; i < len; i++) {
				for (int j = 0; j < len; j++) {
					board[i + sy][j + sx] = part[i][j];
				}
			}
			if(k > 2) {
				continue;
			}
			sy = dy[k];
			sx = dx[k];
		}
	}

	public static int getScore(ArrayList<Group> groups) {
		int ret = 0;
		int n = groups.size();
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				Group a = groups.get(i);
				Group b = groups.get(j);
				int r = ((a.num + b.num) * a.value * b.value * adj[a.name][b.name]);
				ret += r;
			}
		}
		return ret;
	}

	public static ArrayList<Group> getGroup() {
		ArrayList<Group> groups = new ArrayList<>();
		name = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == -1) {
					int score = bfs(i, j, board[i][j], name);
					Group cur = new Group(name++, board[i][j], score);
					groups.add(cur);
				}
			}
		}
		adj = new int[name][name];
		visited2 = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited2[i][j]) {
					bfs2(i, j, visited[i][j]);
				}
			}
		}
		return groups;
	}

	public static int bfs(int y, int x, int group, int name) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { y, x });
		visited[y][x] = name;
		int num = 1;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i], nx = cur[1] + dx[i];
				if (-1 < ny && ny < N && -1 < nx && nx < N) {
					if (visited[ny][nx] == -1 && board[ny][nx] == group) {
						queue.add(new int[] { ny, nx });
						visited[ny][nx] = name;
						num++;
					} 
				}
			}
		}
		
		return num;
	}
	
	public static void bfs2(int y, int x, int name) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { y, x });
		visited2[y][x] = true;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i], nx = cur[1] + dx[i];
				if (-1 < ny && ny < N && -1 < nx && nx < N) {
					if (!visited2[ny][nx] && visited[ny][nx] == name) {
						queue.add(new int[] { ny, nx });
						visited2[ny][nx] = true;
						
					}  else if (visited[ny][nx] != name) {
						adj[name][visited[ny][nx]]++;
					}
				}
			}
		}
	}
	
	public static void init() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				visited[i][j] = -1;
			}
		}
		
	}

	public static void debug() {
		for (int[] b : board) {
			System.out.println(Arrays.toString(b));
		}
	}
}
