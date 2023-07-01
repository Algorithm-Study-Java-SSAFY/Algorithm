package 스터디.암호만들기_1759;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

	static int L;
	static int C;

	static String[] alpha;
	static boolean[] visited;

	static ArrayList<String> passwords = new ArrayList<String>();

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		L = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		alpha = in.readLine().split(" ");
		visited = new boolean[C];

		solution(0, L, 0, 0);
		Collections.sort(passwords);
		for (String pass : passwords) {
			System.out.println(pass);
		}
	}


	public static void solution(int cur, int r, int vowel, int notVowel) {
		if (r == 0 && vowel >= 1 && notVowel >= 2) {
			String pass = "";
			for (int i = 0; i < C; i++) {
				if (visited[i]) {
					pass += alpha[i];
				}
			}
			char[] newPass = pass.toCharArray();
			Arrays.sort(newPass);
			pass = new String(newPass);
			passwords.add(pass);
		}

		for (int i = cur; i < C; i++) {
			if (!visited[i]) {
				visited[i] = true;
				if ("aeiou".contains(alpha[i])) {
					solution(i + 1, r - 1, vowel + 1, notVowel);
				} else {
					solution(i + 1, r - 1, vowel, notVowel + 1);
				}
				visited[i] = false;
			}
		}
	}

}
