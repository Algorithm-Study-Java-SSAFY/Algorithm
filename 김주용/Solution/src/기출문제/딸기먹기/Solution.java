package 기출문제.딸기먹기;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Apple {
	int y;
	int x;
	int num;
	public Apple(int y, int x, int num) {
		super();
		this.y = y;
		this.x = x;
		this.num = num;
	}
	
	
}


public class Solution {
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	static int N;
	static int[][] board;
	static boolean[][][] visited;
	
	static int apple;
	static Apple[] apples;
	
	static int cd;
	
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new java.io.FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
	
		for(int test_case = 1; test_case <= 1; test_case++) {
			N = Integer.parseInt(in.readLine());
			board = new int[N][N];
			visited = new boolean[N][N][4];
			
			apple = 0;
			for(int i = 0; i < N; i++) {
				String[] line = in.readLine().split(" ");
				for(int j = 0; j < N; j++) {
					int num = Integer.parseInt(line[j]);
					board[i][j] = num;
					apple = Math.max(apple, num);
				}
			}
			apples = new Apple[apple + 1];
			apples[0] = new Apple(0, 0, 0);
			int idx = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(board[i][j] > 0) {
						apples[board[i][j]] = new Apple(i, j, board[i][j]);
					}
				}
			}
			
			int ret = solution();
			System.out.printf("#%d %d\n", test_case, ret);
		}
	}
	
	public static int solution() {
		int ret = 0;
		cd = 0;
		for(int i = 0; i < apple; i++) {
			Apple cur = apples[i];
			Apple dest = apples[i+1];
			
			answer = Integer.MAX_VALUE;
			for(int k = 0; k < 2; k++) {
				if(i == 0 && k == 1) {
					continue;
				}
				int d = (cd + k) % 4;
				visited[cur.y][cur.x][d] = true;
				dfs(cur.y, cur.x, cd, 0, dest.num);
				visited[cur.y][cur.x][d] = false;
			}
			board[dest.y][dest.x] = 0;
			System.out.println(answer);
			init();
			ret += answer;
		}
		return ret;
	}

	public static void dfs(int y, int x, int d, int r, int num) {
		if(board[y][x] == num) {
			if(answer > r) {
				answer = r;
				cd = d;
			}
			return;
		}
	
		//System.out.println(y + " " + x);
		for(int i = 0; i < 2; i++) {
			int nd = (d + i) % 4;
			int ny = y + dy[nd], nx = x + dx[nd];
			if(-1 < ny && ny < N && -1 < nx && nx < N) {
				if(visited[ny][nx][nd]) {
					continue;
				}
				visited[ny][nx][nd] = true;
				if(i == 0) {
					dfs(ny, nx, nd, r, num);
				}
				if(i == 1) {
					dfs(ny, nx, nd, r + 1, num);
				}
				visited[ny][nx][nd] = false;
			}
		}
	}
	
	public static void debug() {
		System.out.println(" ");
	}
	
	public static void init() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < 2; k++)
					visited[i][j][k] = false;
			}
		}
	}
	
}

