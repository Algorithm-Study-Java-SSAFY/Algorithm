import java.util.*;
import java.io.*;



public class Main {
	
	// 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
	// 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다. 
	// 가장 처음에 주사위에는 모든 면에 0이 적혀져 있다.
	
	static BufferedReader br; 
	static int N,M,X,Y,K;
	static int[][] board; 
	static int[] direct; // 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
	
	static int[] DY = {1,-1,0,0};
	static int[] DX = {0,0,1,-1};
	
	static int[] dice = {0,0,0,0,0,0}; // 동,서,남,북, 밑면, 윗면  
	
	static String answer ="";
	
	public static void main(String[] args) throws Exception {
		init();
		for(int i=0; i<K; i++) {
			move(direct[i]);	
		}
		System.out.println(answer);
		
	}
	
	
	static void move(int dir) {
		int[] newDice = dice.clone();
		
		if(dir == 1) { // 동 
			if(Y+1<M) {
				newDice[0] = dice[5];
				newDice[4] = dice[0];
				newDice[1] = dice[4];
				newDice[5] = dice[1];
				Y += 1;				
			}else {
				return;
			}

		}
		
		if(dir == 2) { // 서
			if(0<=Y-1) {
				newDice[1] = dice[5];
				newDice[4] = dice[1];
				newDice[0] = dice[4];
				newDice[5] = dice[0];
				Y -= 1;				
			}else {
				return;
			}

		}
		
		if(dir == 4) { // 남
			if(X+1<N) {
				newDice[2] = dice[5];
				newDice[4] = dice[2];
				newDice[3] = dice[4];
				newDice[5] = dice[3];
				X += 1;	
			}else {
				return;
			}

		}
		
		if(dir == 3) { // 북
			if(0 <= X-1) {
				newDice[3] = dice[5];
				newDice[4] = dice[3];
				newDice[2] = dice[4];
				newDice[5] = dice[2];
				X -= 1;
			}else {
				return; 
			}
		}
		
		dice = newDice;

//		System.out.println(dir + " / "+ X+","+Y + " => "+Arrays.toString(dice));
		if(board[X][Y] == 0) {
			board[X][Y] = dice[4];
		}else {
			dice[4] = board[X][Y];
			board[X][Y] = 0;
		}
		
		answer += dice[5]+"\n";
		
	}
	
	
		
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		X = Integer.parseInt(s[2]);
		Y = Integer.parseInt(s[3]);
		K = Integer.parseInt(s[4]);
		
		board = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String[] s2 = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(s2[j]);
			}
		}
	
		direct = new int[K];
		String[] s3 = br.readLine().split(" ");
		for(int i=0; i<K; i++) {
			direct[i] = Integer.parseInt(s3[i]);
		}

	}
		
		
	

}