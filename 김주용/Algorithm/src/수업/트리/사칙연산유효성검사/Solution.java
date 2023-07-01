package 수업.트리.사칙연산유효성검사;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(in.readLine());
			int answer = 1;
			for(int i = 0; i < N; i++) {
				String[] info = in.readLine().split(" ");
				if(info.length == 2 && info[1].charAt(0) < '0') {
					answer = 0;
				} 
				if(info.length >= 3 && info[1].charAt(0) >= '0') {
					answer = 0;
				} 
			}
			System.out.printf("#%d %d\n", test_case, answer);
			
		}
	}

}
