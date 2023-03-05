import java.util.*;
import java.io.*;


public class Main {

	/*
	 * A: 주사위 아랫면  / B: 주사위가 있는 칸에 있는 정수 
	 * A > B : 주사위 이동 방향을 시계방향 90도 회전 
	 * A < B : 주사위 이동 방향을 반시계방향 90도 회전
	 * A = B : 주사위 이동 방향 회전 없음 
	 * 
	 * 주사위를 현재 이동 방향에 맞게 굴린다. 
	 * 주사위가 이동한 칸의 숫자가 상하좌우로 똑같은 숫자가 존재하면 이동 (이때 칸 수를 카운트)
	 */
	
	static int N, M, K; // K는 이동횟수 
	static int[][] board, visited; 
	static int[][] dice; 
	static char direct = 'R'; // 주사위초기 방향 
	static int y = 0;
	static int x = 0;
	
	static int sum = 0; 
	static int[] DY = {1,-1,0,0};
	static int[] DX = {0,0,1,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		K = Integer.parseInt(s[2]);
		
		board = new int[N][M];
		for(int i=0; i<N; i++) {
			String[] s2 = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(s2[j]); 
			}
		}
		
		dice = new int[4][3];
		dice[0][1] = 2; 
		dice[1][0] = 4; 
		dice[1][1] = 1; 
		dice[1][2] = 3; 
		dice[2][1] = 5; 
		dice[3][1] = 6; 
		
		
		for(int i=0; i<K; i++) {
			throwDice(); 
		}
		

		System.out.println(sum);
		
	}
	
	
	// 주사위 굴리기 
	static void throwDice() {
		if(direct == 'R') {
			x = x + 1; 
			if(x<M) RightDice();
			else {
				direct = 'L';
				x = x - 2;
				LeftDice();
			}
		}
		else if(direct == 'L') {
			x = x - 1;
			if(0<=x) LeftDice();
			else {
				direct = 'R';
				x = x + 2;
				RightDice();
			}
		}
		else if(direct == 'D') {
			y = y + 1; 
			if(y<N) DownDice();
			else {
				direct = 'U';
				y = y - 2; 
				UpDice();
			}
		}
		else { // U
			y = y - 1;
			if(0<=y) UpDice();
			else {
				direct = 'D';
				y = y + 2;
				DownDice();
			}
		}
		// 주사위를 굴린 후 위치한 y,x좌표에 따른 점수를 계산 
		int n =BFS();
		sum += n;
		
		// 주사위를 굴린 후 자신의 위치(y,x)의 값과 주사위 바닥을 check하여 주사위 방향을 결정 
		directChange();
		
	}
	
	static void directChange() {
		int diceFloor = dice[3][1]; // 주사위 바닥
		int point = board[y][x]; // 현재 위치한 바닥의 숫자 
		
		if(diceFloor > point) { // 시계방향 90도 회전 
			if(direct == 'R') direct = 'D';
			else if(direct == 'D') direct = 'L';
			else if(direct == 'L') direct = 'U';
			else direct = 'R';
		}else if(diceFloor < point) { // 반시계방향 90도 회전 
			if(direct == 'R') direct = 'U';
			else if(direct == 'D') direct = 'R';
			else if(direct == 'L') direct = 'D';
			else direct = 'L';
		}
	}
	
	// 주사위가 위치한 곳의 숫자를 BFS로 탐색 
	static int BFS() {
		Deque<int[]> dq = new ArrayDeque<int[]>(); 
		visited = new int[N][M];
		visited[y][x] = 1; 
		int checkNum = board[y][x];
		int sumN = 0; 
		dq.add(new int[] {y,x});
		
		while(!dq.isEmpty()){
			sumN += checkNum;
			int[] out =  dq.removeFirst();
			for(int i=0; i<4; i++) {
				int dy = out[0] + DY[i];
				int dx = out[1] + DX[i];
				
				if(0<=dy && dy<N && 0<=dx && dx<M && visited[dy][dx]==0) {
					if(board[dy][dx] == checkNum) {
						dq.add(new int[] {dy,dx});
						visited[dy][dx] = 1; 
					}
				}
			}
		}
		

		return sumN;
	}
	
	// 주사위 동쪽으로 굴리기 
	static void RightDice() {
		int[][] newDice = new int[4][3];
		newDice[0][1] = dice[0][1]; 
		newDice[1][0] = dice[3][1]; 
		newDice[1][1] = dice[1][0]; 
		newDice[1][2] = dice[1][1]; 
		newDice[2][1] = dice[2][1]; 
		newDice[3][1] = dice[1][2]; 
		
		dice = newDice;
	}
	
	// 주사위 서쪽으로 굴리기
	static void LeftDice() {
		int[][] newDice = new int[4][3];
		newDice[0][1] = dice[0][1]; 
		newDice[1][0] = dice[1][1]; 
		newDice[1][1] = dice[1][2]; 
		newDice[1][2] = dice[3][1]; 
		newDice[2][1] = dice[2][1]; 
		newDice[3][1] = dice[1][0]; 
		
		dice = newDice;
	}
	
	// 주사위 남쪽으로 굴리기
	static void DownDice() {
		int[][] newDice = new int[4][3];
		newDice[0][1] = dice[3][1]; 
		newDice[1][0] = dice[1][0]; 
		newDice[1][1] = dice[0][1]; 
		newDice[1][2] = dice[1][2]; 
		newDice[2][1] = dice[1][1]; 
		newDice[3][1] = dice[2][1]; 
	
		dice = newDice;
	}
	
	// 주사위 북쪽으로 굴리기 
	static void UpDice() {
		int[][] newDice = new int[4][3];
		newDice[0][1] = dice[1][1]; 
		newDice[1][0] = dice[1][0]; 
		newDice[1][1] = dice[2][1]; 
		newDice[1][2] = dice[1][2]; 
		newDice[2][1] = dice[3][1]; 
		newDice[3][1] = dice[0][1]; 
	
		dice = newDice;
	}


}