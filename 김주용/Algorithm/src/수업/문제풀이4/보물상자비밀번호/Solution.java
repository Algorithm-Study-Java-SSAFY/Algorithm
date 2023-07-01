package 수업.문제풀이4.보물상자비밀번호;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Solution {

	static int N;
	static int K;
	static String num;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
	
		for (int test_case = 1; test_case <= T; test_case++) {
			String[] line = in.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			K = Integer.parseInt(line[1]);
			num = in.readLine();
			int answer = solution();
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}
	
	public static int solution() {
		int R = N / 4;
		HashSet<String> set = new HashSet<>();
		ArrayList<Integer> ret = new ArrayList<>();
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < N; j += R) {
				String newNum = num.substring(j, j + R);
				if(set.contains(newNum)) {
					continue;
				}
				set.add(newNum);
				ret.add(Integer.parseInt(newNum, 16));
			}
			num = num.charAt(N-1) + num.substring(0, N-1);
		}
		Collections.sort(ret, Collections.reverseOrder());
		
		return ret.get(K-1);
	}

}
