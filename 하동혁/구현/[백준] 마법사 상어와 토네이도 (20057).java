package codingTest2;


import java.util.*;
import java.io.*;

/*
 *   토네이도 시작 -> 격자의 가운데 칸부터 이동이 시작 
 *   N은 언제나 홀수 
 *   범위 체크 중요!!!
 *   
 */

public class Main {
	
	static int N; // N*N
	static int[][] board;
	static int[][] rcBoard;
	static int[] start, end; 
	static int[][] upPoint;  // 해당 좌표를 밟으면 dis를 +1 해준다. 
	static int dis = 1; 
	static int py, px; 
	static Double[] spread = new Double[] {5.0, 10.0, 10.0, 7.0, 7.0, 2.0, 2.0, 1.0, 1.0, 0.0};
	static int[][] leftMove = new int[][] {{0,-2},{-1,-1},{1,-1},{-1,0},{1,0},{-2,0},{2,0},{-1,1},{1,1},{0,-1}};
	static int[][] rightMove = new int[][] {{0,2},{-1,1},{1,1},{-1,0},{1,0},{-2,0},{2,0},{-1,-1},{1,-1},{0,1}};
	static int[][] upMove = new int[][] {{-2,0},{-1,-1},{-1,1},{0,-1},{0,1},{0,-2},{0,2},{1,-1},{1,1},{-1,0}}; 
	static int[][] downMove = new int[][] {{2,0},{1,-1},{1,1},{0,-1},{0,1},{0,-2},{0,2},{-1,-1},{-1,1},{1,0}};
	
	static int outSand = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		rcBoard = new int[N][N];
		rcBoard[N/2][N/2] = 1; 
		
		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(s[j]); 
			}
		}
	//------------------------------------------------------------------------
		start = new int[] {N/2, N/2}; 
		end = new int[] {1,1};
		upPoint = new int[N/2][2];
		for(int i=1; i<N/2; i++) { 
			upPoint[i] = new int[] {i,i};  
		}
		
		// py, px 는 현재 위치이다. 
		py = start[0]; 
		px = start[1];
		moveTornado();
		

		System.out.println(outSand);
		
	}
	
	// 토네이도 회전
	static void moveTornado() {
		int cnt = 2; 
		out:
		while(true) {
			// 왼쪽
			while(true) {
				px--;
				if(0 <= py && Math.abs(px-start[1]) <= dis) {
					spreadSand('L', py, px);
					rcBoard[py][px] = cnt;
					cnt++;
				}else break; 

				if(py==0 && px ==0) break out; 
			} 
			px ++; 

			// 아래
			while(true) {
				py++;
				if(py < N && Math.abs(py-start[0]) <= dis) {
					spreadSand('D', py, px);
					rcBoard[py][px] = cnt;
					cnt++;
				}else break;

			} 
			py --; 

			// 오른쪽
			while(true) {
				px++;
				if(px <N && Math.abs(px-start[1]) <= dis) {
					spreadSand('R', py, px);
					rcBoard[py][px] = cnt;
					cnt++;
				}else break; 
			} 
			px --;

			// 위쪽 
			while(true) {
				py--;
				if( 0 <= py && Math.abs(py-start[0]) <= dis) {
					spreadSand('U', py, px);
					rcBoard[py][px] = cnt;
					cnt++;
				}else break; 
			} 
			py ++;


		
			dis++; 
		}
	}
	
	// 상하좌우 상태 / y, x 좌표 
	static void spreadSand(char status, int y, int x) {

		// 방향 선택
		int[][] direct;
		if(status == 'L') direct = leftMove;
		else if(status == 'R') direct = rightMove;
		else if(status == 'U') direct = upMove;
		else direct = downMove;
		
		int totalSand = board[y][x];
		int alpaSand = totalSand;

		for(int i=0; i<=9; i++) {
			int sy = y+direct[i][0];
			int sx = x+direct[i][1];
			int spSand = (int) Math.floor((totalSand/100.0) * spread[i]);
			
			alpaSand -= spSand;
			if(0<=sy && sy<N && 0<=sx && sx<N) {
				if(i==9) board[sy][sx] += alpaSand; // 알파 모레 위치
				else board[sy][sx] += spSand;
			}
			else if(i==9) {
				outSand += alpaSand;
			}
			else { // 모래가 격자 밖으로 나가는 경우 
				outSand += spSand;
			}
			
		}
		board[y][x] = 0;
		

	}



}