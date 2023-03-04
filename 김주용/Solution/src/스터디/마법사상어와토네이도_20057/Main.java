package 스터디.마법사상어와토네이도_20057;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	// 왼 아래 오 위
	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { -1, 0, 1, 0 };

	static int[][][] rateBoards;
	static int[][] leftRateBoard = { 
			{ 0, 0, 2, 0, 0 }, 
			{ 0, 10, 7, 1, 0 }, 
			{ 5, 0, 0, 0, 0 }, 
			{ 0, 10, 7, 1, 0 },
			{ 0, 0, 2, 0, 0 } 
			};
	
	
	static int N;
	static int[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		solution();

	}
	
	public static void solution() {
		rateBoards = new int[5][5][4];	// 왼 -> 아래 -> 오 -> 위 순으로 
		rateBoards[0] = leftRateBoard;
		for(int i = 1; i < 4; i++) {
			rateBoards[i] = rotate90(rateBoards[i-1]);
		}
		tornado();
	}

	// 토네이도 진행: 1 1 -> 2 2 -> 3 3 -> 4 4 -> 6 6 - > 7  진행 칸 수가 2번 반복 후 1 증가  
	public static void tornado() {
		int initSand = getSand();
		int y = N / 2, x = N / 2;
		int canGO = 0;
		int step = 0, curD = 0;

		while (step < 2 * N - 1) { // 마지막은 한번만
			if (step % 2 == 0) { // 현재 방향에서 진행하는 칸 수
				canGO++;
			}
			for (int i = 0; i < canGO; i++) { // 칸 수 만큼 현재 방향으로 이동
				int ny = y + dy[curD], nx = x + dx[curD];
				if (-1 < ny && ny < N && -1 < nx && ny < N) {
					moveSand(ny, nx, curD, board[ny][nx]);
					y = ny;
					x = nx;
				}
			}
			curD = (curD + 1) % 4; // 방향 전환
			step++;
		}
		
		int finalSand = getSand();
		int ret = initSand - finalSand;
		System.out.println(ret);
	}

	// 모래 이동: (y의 좌표,현재 방향, y의 모래양)  
	public static void moveSand(int yY, int yX, int curD, int sand) {
		int remain = sand;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(rateBoards[curD][i][j] <= 0) continue;
				int ty = i - 2 + yY, tx = j - 2 + yX;	// 5x5 고정된 사이즈로 비율을 저장중인 2차원 배열을 현재 진행중인 2차원 배열 좌표에 맞게 변환 
				int move = (int) Math.floor(sand * rateBoards[curD][i][j] * 0.01);
				if(-1 < ty && ty < N && -1 < tx && tx < N) {
					board[ty][tx] += move;
				} 
				remain -= move;
			}
		}
		int aY = yY + dy[curD], aX = yX + dx[curD];
		if(-1 < aY && aY < N && -1 < aX && aX < N) {
			board[aY][aX] += remain;
		}
		board[yY][yX] = 0;
	}
	
	// 반 시계 방향 2차원 배열 돌리기 
	public static int[][] rotate90(int[][] originBoard) {
		int[][] ret = new int[5][5];
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++ ) {
				ret[i][j] = originBoard[j][4-i];
			}
		}
		return ret;
	}

	public static int getSand() {
		int ret = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ret += board[i][j];
			}
		}
		return ret;
	}
}
