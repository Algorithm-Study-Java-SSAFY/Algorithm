package 스터디.단어수학_1339;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

class Alphabet implements Comparable<Alphabet> {
	char value;
	int weight;

	public Alphabet(char value, int weight) {
		super();
		this.value = value;
		this.weight = weight;
	}

	@Override
	public int compareTo(Alphabet o) {
		return o.weight - this.weight;
	}

	@Override
	public String toString() {
		return "Alphabet [value=" + value + ", weight=" + weight + "]";
	}

}

public class Main {

	static int N;
	static String[] numbers;
	static int L = 0;

	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		numbers = new String[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = in.readLine();
			L = Math.max(L, numbers[i].length());
		}

		solution();
	}

	// 자리수가 제일 큰게 큰 수가 되어야 한다.
	public static void solution() {
		// 문자열 길이 통일
		for (int i = 0; i < N; i++) {
			if (L == numbers[i].length()) {
				continue;
			}
			String temp = "";
			for (int j = 0; j < L - numbers[i].length(); j++) {
				temp += "-";
			}
			numbers[i] = temp + numbers[i];
		}
		// 높은 자릿수에 더 많이 포진한 알파벳 가중치 구하기
		int[] alphas = new int[26];
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < N; j++) {
				String cur = numbers[j];
				if (cur.charAt(i) == '-') {
					continue;
				}
				alphas[cur.charAt(i) - 'A'] += Math.pow(10, (L - i - 1));
			}
		}
		//System.out.println(Arrays.toString(alphas));
		PriorityQueue<Alphabet> queue = new PriorityQueue<Alphabet>();
		for (int i = 0; i < 26; i++) {
			if (alphas[i] > 0) {
				queue.add(new Alphabet((char) (i + 'A'), alphas[i]));
			}
		}
		// 높은 자릿수에 많을 수록 큰 수 주기
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int num = 9;
		while (!queue.isEmpty()) {
			Alphabet cur = queue.poll();
			map.put(cur.value, num--);
		}
		// 결과
		int answer = 0;
		for (int i = 0; i < N; i++) {
			String cur = numbers[i];
			String ret = "";
			for (int j = 0; j < cur.length(); j++) {
				if (cur.charAt(j) == '-') {
					continue;
				}
				ret += String.valueOf(map.get(cur.charAt(j)));
			}
			answer += Integer.parseInt(ret);
		}
		System.out.println(answer);
	}

}
