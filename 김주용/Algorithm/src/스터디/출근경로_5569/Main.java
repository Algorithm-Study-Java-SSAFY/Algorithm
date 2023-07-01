package 스터디.출근경로_5569;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Point {
	int fromUp;
	int fromRight;
	
	public Point(int fromUp, int fromRight) {
		super();
		this.fromUp = fromUp;
		this.fromRight = fromRight;
	}
}

public class Main {
	static int W;
	static int H;
	static int[][] board;
	static boolean[][] visited;

	static int[] dy = { 1, 0 };
	static int[] dx = { 0, 1 };

	static Point[][] dp;
	static long answer = 0;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		W = Integer.parseInt(line[0]);
		H = Integer.parseInt(line[1]);
		dp = new Point[H][W];
		solution();
	}

//	// 2 ^ 10000 -> 브루투 포스 x 
//	public static void solution(int y, int x, int d, boolean k) {
//		if(y == H-1 && x == W-1) {
//			answer += 1;
//			return;
//		}
//		
//		// i : 갈 방향 / d: 이전 방향 / k: 이전에 돌은건지 
//		for (int i = 0; i < 2; i++) {
//			int ny = y + dy[i], nx = x + dx[i];
//			if (-1 < ny && ny < H && -1 < nx && nx < W && !visited[ny][nx]) {
//				if(d != -1 && i != d && k) {
//					continue;
//				}
//				visited[ny][nx] = true;
//				boolean kk = (i != d && d != -1) ? true : false;
//				solution(ny, nx, i, kk);
//				visited[ny][nx] = false;
//			}
//		}
//	}
	
	// dp[i][j] 에서 오른쪽으로 갈 수 있는 경우 : dp[i][j-1]에서 오른쪽으로 온 경우 + dp[i-1][j-1]에서 위로 온 경우 
	// dp[i][j] 에서 위로 갈 수 있는 경우 : dp[i-1][j]에서 위로 온 경우 + dp[i-1][j-1]에서 오른쪽으로 온 경우
	public static void solution() {
		for(int i = 0; i < H; i++) {
			dp[i][0] = new Point(1, 0);
		}
		for(int i = 0; i < W; i++) {
			dp[0][i] = new Point(0, 1);
		}
		dp[0][0] = new Point(1, 1); // 시작점은 방향 상관없이 1 
	
		for(int i = 1; i < H; i++) {
			for(int j = 1; j < W; j++) {
				int fromRight = (dp[i][j-1].fromRight + dp[i-1][j-1].fromUp) % 100000;
				int fromDown = (dp[i-1][j].fromUp + dp[i-1][j-1].fromRight) % 100000;
				dp[i][j] = new Point(fromDown, fromRight);
			}
		}
		int answer = (dp[H-1][W-1].fromUp + dp[H-1][W-1].fromRight) % 100000;
		System.out.println(answer);
	}
}
