package 수업.문제풀이1.수영장;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Solution {
	
	static int[] prices;
	static int[] plan;
	
	static int answer;
	
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			prices = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			plan = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			solution();
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}
	
	public static void solution() {
		answer = prices[3];
		dfs(0, 0);
	}
	
	public static void dfs(int month, int sum) {
		if(month >= 12) {
			answer = Math.min(answer, sum);
			return;
		}
	
		
		if(plan[month] == 0) {
			dfs(month + 1, sum);
		} else {
			dfs(month + 1, sum + plan[month] * prices[0]);
			dfs(month + 1, sum + prices[1]);
			dfs(month + 3, sum + prices[2]);
		}
	}
	
}