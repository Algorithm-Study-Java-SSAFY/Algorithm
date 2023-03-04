package 스터디.괄호추가하기_16637;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N;
	static char[] operators;
	static boolean[] visited;
	static int[] nums;
	static String[] expression;
	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		int n = N / 2 + 1;
		nums = new int[N];
		operators = new char[N / 2 + 2];
		visited = new boolean[N / 2 + 2];
		expression = new String[N];
		String line = in.readLine();
		for (int i = 0; i < N; i++) {
			expression[i] = String.valueOf(line.charAt(i));
			if (i % 2 == 1) {
				operators[i / 2 + 1] = line.charAt(i);
			}
		}
		solution(1);
		System.out.println(answer);
	}

	public static void solution(int cur) {
		int ret = caculate();
		answer = Math.max(answer, ret);

		for (int i = cur; i < N / 2 + 1; i++) {
			if (!visited[i - 1] && !visited[i + 1]) {
				visited[i] = true;
				solution(i + 1);
				visited[i] = false;
			}
		}
	}

	public static int caculate() {
		String[] newExpression = expression.clone();
		// 괄호 우선순위 먼저 계산 -> 계산에 쓰인 operator랑 수는 지워주고 연산 결과만 저장
		for (int i = 0; i < N; i++) {
			int opIdx = i / 2 + 1;
			if (i % 2 == 1 && !visited[opIdx]) {
				continue;
			}
			if (i % 2 == 0) {
				continue;
			}
			String oper = expression[i];
			int left = Integer.parseInt(expression[i - 1]);
			int right = Integer.parseInt(expression[i + 1]);
			int ret = getCalculate(left, right, oper);

			newExpression[i - 1] = String.valueOf(ret);
			newExpression[i + 1] = ".";
			newExpression[i] = ".";

		}
		// 나머지 계산 [24, ., ., +, 5, ... , ] -> '.' 지워주고 계산하기
		Queue<String> queue = new LinkedList<String>();
		int ret = Integer.parseInt(newExpression[0]);
		String oper = "";
		for (int i = 1; i < N; i++) {
			if (newExpression[i] == ".") {
				continue;
			}
			queue.add(newExpression[i]);
		}

		while (!queue.isEmpty()) {
			String cur = queue.poll();
			if ("+-*".contains(cur)) {
				oper = cur;
			} else {
				ret = getCalculate(ret, Integer.parseInt(cur), oper);
			}
		}
		return ret;
	}

	public static int getCalculate(int a, int b, String oper) {
		if (oper.equals("+")) {
			return a + b;
		}
		if (oper.equals("-")) {
			return a - b;
		}
		if (oper.equals("*")) {
			return a * b;
		}
		return a / b;
	}
}
