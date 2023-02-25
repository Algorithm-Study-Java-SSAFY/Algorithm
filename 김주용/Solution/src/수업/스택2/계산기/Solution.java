package 수업.스택2.계산기;

import java.util.Scanner;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
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
		String answer = "";
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
