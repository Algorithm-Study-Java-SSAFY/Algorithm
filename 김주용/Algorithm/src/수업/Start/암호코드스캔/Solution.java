package 수업.Start.암호코드스캔;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Solution {

	static int N;
	static int M;
	static String[] board;

	static HashMap<Character, String> binaryMap = new HashMap<Character, String>() {{
		put('0', "0000");
		put('1', "0001");
		put('2', "0010");
		put('3', "0011");
		put('4', "0100");
		put('5', "0101");
		put('6', "0110");
		put('7', "0111");
		put('8', "1000");
		put('9', "1001");
		put('A', "1010");
		put('B', "1011");
		put('C', "1100");
		put('D', "1101");
		put('E', "1110");
		put('F', "1111");
	}};
	
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
		System.setIn(new FileInputStream("res/input.txt"));
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
			solution();
			int answer = 0;
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}
	
	public static void solution() {
		toBinary();
	}
	
	public static void toBinary() {
		String[] newBoard = new String[N];
		for(int i = 0; i < N; i++) {
			String line = "";
			for(int j = 0; j < M; j++) {
				char cur = board[i].charAt(j);
				line += binaryMap.get(cur);
			}
			System.out.println(line);
			newBoard[i] = line;
		}
	}
	
	public static String getPwdCode() {
		int y = 0, x = 0;
		loop: for (int i = 0; i < N; i++) {
			for (int j = M - 1; j >= 0; j--) {
				if (board[i].charAt(j) == '1') {
					
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
	
	public static void name(int sy, int sx) {
		// 길이 구하기 
		while(true) {
			
		}
	}
	
}
