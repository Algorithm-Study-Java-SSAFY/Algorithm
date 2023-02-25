package 수업.스택1.괄호짝짓기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(in.readLine());
			String line = in.readLine();
			
			int answer = solution(line, N);
			System.out.printf("#%d %d\n",test_case,answer);
		}
	}
	
	public static int solution(String line, int N) {
		int ret = 0;
		Stack<Character> stack = new Stack<>();
		stack.add(line.charAt(0));
		for(int i = 1; i < N; i++) {
			char cur = line.charAt(i);
			//System.out.println(cur);
			if(cur == '{' || cur == '(' || cur == '[' || cur == '<') {
				stack.add(cur);
				continue;
			}
			//System.out.println(stack);
			char top = stack.peek();
			if(isPair(top, cur)) {
				stack.pop();
				continue;
			}
			return 0;
		}
		return 1;
	}
	
	public static boolean isPair(char e1, char e2) {
		if(e1 == '{' && e2 == '}') {
			return true;
		}
		if(e1 == '(' && e2 == ')') {
			return true;
		}
		if(e1 == '[' && e2 == ']') {
			return true;
		}
		if(e1 == '<' && e2 == '>') {
			return true;
		}
		
		return false;
	}
}
