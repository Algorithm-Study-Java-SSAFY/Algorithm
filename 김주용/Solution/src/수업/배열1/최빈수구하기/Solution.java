package 수업.배열1.최빈수구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T;
		T = Integer.parseInt(in.readLine());
	

		for(int test_case = 1; test_case <= T; test_case++) {
			int N =  Integer.parseInt(in.readLine());
			int[] numArr = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int answer = solution(numArr);
			
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}
	
	public static int solution(int[] numArr) {
		int maxCnt = 0;
		int[] countArr = new int[1001];
		for(int i = 0; i < 1000; i++) {
			countArr[numArr[i]] += 1;
			maxCnt = Math.max(maxCnt, countArr[numArr[i]]);
		}
		Arrays.sort(numArr);
		for(int i = 1000 - 1; i >= 0; i--) {
			if(countArr[numArr[i]] == maxCnt) {
				return numArr[i];
			}
		}
		return -1;
		
	}

}
