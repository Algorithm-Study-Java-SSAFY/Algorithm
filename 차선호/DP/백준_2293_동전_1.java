import java.util.*;
import java.io.*;



public class Main {
	
	static int N,K;
	static int[] COINS;
	static int[][] DP;
	
	public static void main(String[] args) throws Exception{
		init();
		makeDP();
//		for(int[] a: DP) {
//			System.out.println(Arrays.toString(a));
//		}
		System.out.println(DP[N][K]);
	}
	
	static void makeDP() {
		int coin = COINS[0];
		for(int j=1;j<K+1;j++) {
			DP[1][j] = DP[0][j];
			if(j%coin==0) DP[1][j]++;
		}
		for(int i=2;i<N+1;i++) {
			coin = COINS[i-1];
			for(int j=1;j<K+1;j++) {
				if(j>=coin) DP[i][j] = DP[i][j-coin]+DP[i-1][j];
				else DP[i][j] = DP[i-1][j];
				if(j==coin) DP[i][j]++;
			}
		}
	}
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String[] NK = br.readLine().split(" ");
	    N = Integer.parseInt(NK[0]);
	    K = Integer.parseInt(NK[1]);
	    COINS = new int[N];
	    for(int i=0;i<N;i++) {
	    	COINS[i] = Integer.parseInt(br.readLine());
	    }
	    DP = new int[N+1][K+1];
	}
}