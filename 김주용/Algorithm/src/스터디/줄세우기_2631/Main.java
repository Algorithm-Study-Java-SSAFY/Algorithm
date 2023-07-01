package 스터디.줄세우기_2631;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int[] nums;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		nums = new int[N];
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(in.readLine());
		}
		solution();
	}
	
	public static void solution() {
		int[] dp = new int[N];
		int LIS = 0;
		for(int i = 0; i < N; i++) {
			dp[i] = 1;
			for(int j = 0; j < i; j++) {
				if(nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
					LIS = Math.max(dp[i], LIS);
				}
			}
		}
		
		System.out.println(N - LIS);
	}
}
