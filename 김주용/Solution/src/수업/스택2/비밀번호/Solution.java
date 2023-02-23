package 수업.스택2.비밀번호;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Stack;

public class Solution {
	
	static int N;
	static String str;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			String[] line = in.readLine().split(" "); 
			N = Integer.parseInt(line[0]);
			str = line[1];
			
			System.out.printf("#%d %s\n", test_case, solution());
		}

	}
	
	public static String solution() {
		String ret = "";
		Stack<Character> stack = new Stack<>();

		for(int i = 0; i < N; i++) {
			char cur = str.charAt(i);
			if(!stack.isEmpty() && cur == stack.peek()) {
				stack.pop();
				continue;
			}
			stack.add(cur);
		}
		Iterator iter = stack.iterator();
		while(iter.hasNext()) {
			ret += iter.next();
		}
		return ret;
	}

}
