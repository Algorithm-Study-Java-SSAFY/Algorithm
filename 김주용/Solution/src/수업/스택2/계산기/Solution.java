package 수업.스택2.계산기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;


class Solution {
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(in.readLine());
			String line = in.readLine();
			
			int answer = solution(line, N);
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}

	public static int solution(String line, int N) {
		StringBuilder ret = new StringBuilder();
		Stack<Character> stack = new Stack<Character>();
		
		for(int i = 0; i < N; i++) {
			char cur = line.charAt(i);
			if(cur == '(') {
				stack.push(cur);
				continue;
			} else if (cur >= '0' && cur <= '9') {
				ret.append(cur);
				continue;
			} 
			
			while(!stack.empty() && stack.peek() != '(' && getPrior(stack.peek()) >= getPrior(cur)) {
				ret.append(stack.pop());
			}
			
			if(cur == ')') {
				stack.pop();
			} else {
				stack.push(cur);
			}
			
		}
		return calculate(ret);
	}

	public static int calculate(StringBuilder line) {
		Stack<Integer> calc = new Stack<Integer>();
		for (int i = 0; i < line.length(); i++) {
			char cur = line.charAt(i);
			if (cur == '+') {
				calc.push(calc.pop() + calc.pop());
			} else if (cur == '*') {
				calc.push(calc.pop() * calc.pop());
			} else {
				calc.push(cur - '0');
			}
		}
		return calc.pop();
	}

	public static int getPrior(char cur) {
		if (cur == '(' || cur == ')') {
			return 0;
		}
		if (cur == '+') {
			return 1;
		}
		if (cur == '*') {
			return 2;
		}
		return -1;
	}
}
