package 수업.Start.단순2진암호코드;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Solution {

	static int N;
	static int M;
	static String[] board;

	static HashMap<String, Integer> pattern = new HashMap<String, Integer>() {
		{
			put("0001101", 0);
			put("0011001", 1);
			put("0010011", 2);
			put("0111101", 3);
			put("0100011", 4);
			put("0110001", 5);
			put("0101111", 6);
			put("0111011", 7);
			put("0110111", 8);
			put("0001011", 9);
		}
	};

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("res/sample_input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			String[] line = in.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			M = Integer.parseInt(line[1]);
			board = new String[N];
			for (int i = 0; i < N; i++) {
				board[i] = in.readLine();
			}
			int answer = solution();
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}

	public static int solution() {
		String pwdCode = getPwdCode();
		int answer = getAnswer(pwdCode);
		return answer;
	}

	public static String getPwdCode() {
		int y = 0, x = 0;
		loop: for (int i = 0; i < N; i++) {
			for (int j = M - 1; j >= 0; j--) {
				if (board[i].charAt(j) == '1') {
					y = i;
					x = j;
					break loop;
				}
			}
		}

		String[] ret = new String[8];
		int idx = 0;
		for (int i = x - 55; i <= x; i += 7) {

			ret[idx++] = board[y].substring(i, i + 7);
		}

		String pwdCode = "";
		for (String code : ret) {
			pwdCode += pattern.get(code);
		}

		return pwdCode;
	}

	public static int getAnswer(String pwdCode) {
		int oddSum = 0;
		int evenSum = 0;
		for (int i = 0; i < 8; i++) {
			int cur = pwdCode.charAt(i) - '0';
			if (i % 2 == 0) {
				oddSum += cur;
			} else {
				evenSum += cur;
			}
		}

		if ((oddSum * 3 + evenSum) % 10 == 0) {
			return oddSum + evenSum;
		}
		return 0;
	}
}
