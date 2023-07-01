package 스터디.동전1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static int N;
	static int K;
	static int[] coins;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		K = Integer.parseInt(line[1]);
		
		coins = new int[N];
		dp = new int[K + 1];
		for(int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(in.readLine());
		}
		solution();
	}

	/*
	 * 1: 1
	 * 2: 1 1, 2
	 * 3: 1 1 1, 1 2
	 * 4: 1 1 1 1, 1 1 2, 2 2
	 * 5: 1 1 1 1 1, 1 1 1 2, 1 2 2, 5
	 */
	public static void solution() {
		dp[0] = 1; // 아무것도 사용 x
		for(int i = 0; i < N; i++) {
			// 동전 크기를 K원 까지 1 씩 늘리면서 갱신 
			for(int j = coins[i]; j <= K; j++) {
				dp[j] += dp[j - coins[i]];
			}
		}
		System.out.println(dp[K]);
	}
}
