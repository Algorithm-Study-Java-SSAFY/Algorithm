package 문자열.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case = 1; test_case <= 10; test_case++) {
			String t = in.readLine();
			String key = in.readLine();
			String str = in.readLine();
			
			int answer = solution(str, key);
			System.out.printf("#%d %d\n", test_case, answer);
		}
			
	}
		
	public static int solution(String str, String key) {
		int ret = 0; 
		while(true) {
			int idx = str.indexOf(key);
			if(idx == -1) {
				break;
			}
			ret++;
			str = str.substring(idx + key.length());
		}
		return ret;
	}
}
