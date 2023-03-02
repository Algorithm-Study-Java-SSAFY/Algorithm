package 수업.문제풀이1.숫자만들기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.stream.Stream;

public class Solution {

	static int N;
	static int[] operators;
	static int[] nums;

	static Stack<Integer> stack = new Stack<Integer>();

	static int retMax;
	static int retMin;

	public static void main(String args[]) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= 10; test_case++) {
			N = Integer.parseInt(in.readLine());
			operators = new int[4];
			nums = new int[N];

			operators = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			nums = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			int answer = solution();
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}

	public static int solution() {
		retMax = Integer.MIN_VALUE;
		retMin = Integer.MAX_VALUE;
		stack = new Stack<Integer>();
		dfs(0);

		return retMax - retMin;
	}

	public static void dfs(int cur) {

		if (cur == N - 1) {
			int ret = caculate();
			retMin = Math.min(retMin, ret);
			retMax = Math.max(retMax, ret);
		}

		for (int i = 0; i < 4; i++) {
			if (operators[i] > 0) {
				operators[i] -= 1;
				stack.push(i);
				dfs(cur + 1);
				stack.pop();
				operators[i] += 1;
			}
		}
	}

	public static int caculate() {
		int ret = nums[0];

		for (int i = 0; i < N - 1; i++) {
			int cur = stack.get(i);
			ret = getCaculate(ret, nums[i + 1], cur);
		}
		return ret;
	}

	public static int getCaculate(int a, int b, int operator) {
		if (operator == 0) {
			return a + b;
		}
		if (operator == 1) {
			return a - b;
		}
		if (operator == 2) {
			return a * b;
		}
		return a / b;
	}
}
