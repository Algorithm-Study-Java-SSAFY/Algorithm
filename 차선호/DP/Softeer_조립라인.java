import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[] A,B,A_TO_B,B_TO_A;
	static int[][] DP;
	
	public static void main(String[] args) throws Exception {
		
		init();
	
		solution();
		
		System.out.println(Math.min(DP[N][0], DP[N][1]));
		
	}
	
	static void solution() {
		makeDp();
		
//		for(int i=1;i<N+1;i++) {
//			System.out.println(Arrays.toString(DP[i]));
//		}
	}
	
	static void makeDp() {
		DP = new int[N+1][2];
		DP[1][0] = A[1];
		DP[1][1] = B[1];
		
		for(int i=2;i<N+1;i++) {
			DP[i][0] = Math.min(DP[i-1][0], DP[i-1][1]+B_TO_A[i-1])+A[i];
			DP[i][1] = Math.min(DP[i-1][0]+A_TO_B[i-1], DP[i-1][1])+B[i];
		}
	}
	
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N+1];
		B = new int[N+1];
		A_TO_B = new int[N+1];
		B_TO_A = new int[N+1];
		
		for(int i=1;i<N+1;i++) {
			String[] abmove = br.readLine().split(" ");
			A[i] = Integer.parseInt(abmove[0]);
			B[i] = Integer.parseInt(abmove[1]);
			if(i==N) break;
			A_TO_B[i] = Integer.parseInt(abmove[2]);
			B_TO_A[i] = Integer.parseInt(abmove[3]);
		}
	}
}