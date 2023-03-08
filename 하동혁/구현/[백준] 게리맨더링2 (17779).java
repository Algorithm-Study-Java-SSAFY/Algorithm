import java.util.*;
import java.io.*;

public class Main {

	static int N; 
	static int[][] board;
	static int[][] divBoard;
	static int fy,fx,d1,d2;
	
	static int[][] startPoint;
	static int[][] range;
	static int[] score;
	static int[] DY = {-1,1,1,-1};
	static int[] DX = {1,1,1,1};
	static int[] RY = {1,-1,0,0};
	static int[] RX = {0,0,1,-1}; 
	
	static List<Integer> answer = new ArrayList<Integer>();

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); 
		board = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<N; j++) board[i][j] = Integer.parseInt(s[j]);
		}
		
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				start(i,j);
			}
		}
		
		Collections.sort(answer);
		System.out.println(answer.get(0));
		
	}
	
	static void start(int y,int x) {
		// d1, d2를 하나씩 증가시키며 5번 구역 생성이 가능한지 먼저 확인  (d1, d2 ≥ 0, 0)
		for(int i=1; i<N-1; i++) { // d1
			for(int j=1; j<N-1;j++) { // d2
				if(0<=x-i && x+j<N && y+i+j<N) {
					fy = y;
					fx = x;
					d1 = i; 
					d2 = j; 
					divBoard = new int[N][N];
					score = new int[5];
					startPoint = new int[][] {{0,0},{0,N-1},{N-1,0},{N-1,N-1},{fy,fx}}; // 1,2,3,4,5번 선거구 

					area5();
					for(int k=0; k<4; k++) divArea(k);
					fill_5();
					
					Arrays.sort(score);
					answer.add(score[4]-score[0]);
					
					
				}
			}
		}
	}
	
	static void area5() {
		divBoard[fy][fx] = 5;
		for(int i=1; i<=d1; i++) divBoard[fy+i][fx-i] = 5;
		for(int i=1; i<=d2; i++) divBoard[fy+i][fx+i] = 5;
		for(int i=1; i<=d2; i++) divBoard[fy+d1+i][fx-d1+i] = 5; 
		for(int i=1; i<=d1; i++) divBoard[fy+d2+i][fx+d2-i] = 5; 
	}		
	
	// BFS
	static void divArea(int idx) {
		int y = startPoint[idx][0];
		int x = startPoint[idx][1];
		Deque<int[]> dq = new ArrayDeque<int[]>(); 
		dq.add(startPoint[idx]);
		divBoard[y][x] = idx+1;
		score[idx] += board[y][x];

		
		while(!dq.isEmpty()) {
			int[] out = dq.removeFirst();
			for(int i=0; i<4; i++) {
				int dy = out[0]+RY[i];
				int dx = out[1]+RX[i];
				if(0<=dy && dy<N && 0<=dx && dx<N  && divBoard[dy][dx] == 0) {
					if(idx==0 && dy<fy+d1 && dx<=fx) {
						divBoard[dy][dx] = idx+1;
						dq.add(new int[] {dy,dx});
						score[idx] += board[dy][dx];
					}
					else if(idx==1 && dy<=fy+d2 && fx<dx) {
						divBoard[dy][dx] = idx+1; 
						dq.add(new int[] {dy,dx});
						score[idx] += board[dy][dx];
					}
					else if(idx==2 && fy+d1<=dy && dx<fx-d1+d2) {
						divBoard[dy][dx] = idx+1; 
						dq.add(new int[] {dy,dx});
						score[idx] += board[dy][dx];
					}
					else if(idx==3 && fy+d2<dy && fx-d1+d2<=dx) {
						divBoard[dy][dx] = idx+1;
						dq.add(new int[] {dy,dx});
						score[idx] += board[dy][dx];
					}
				}
			}
		}
		
	}
	
	static void fill_5() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(divBoard[i][j]==0 || divBoard[i][j]==5) {
					divBoard[i][j] = 5;
					score[4] += board[i][j];
				}
			}
		}
	}
	
    
}