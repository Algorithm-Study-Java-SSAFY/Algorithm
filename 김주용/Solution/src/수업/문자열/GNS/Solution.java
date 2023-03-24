package 수업.문자열.GNS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;

class Str implements Comparable<Str> {
	String value;
	int num;

	public Str(String value, int num) {
		this.value = value;
		this.num = num;
	}

	@Override
	public int compareTo(Str o) {
		return this.num - o.num;
	}
}

public class Solution {

	static String[] strings;
	static HashMap<String, Integer> map = new HashMap<String, Integer>() {
		{
			put("ZRO", 0);
			put("ONE", 1);
			put("TWO", 2);
			put("THR", 3);
			put("FOR", 4);
			put("FIV", 5);
			put("SIX", 6);
			put("SVN", 7);
			put("EGT", 8);
			put("NIN", 9);
		}
	};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= 1; test_case++) {
			String t = in.readLine();

			PriorityQueue<Str> queue = new PriorityQueue<>();
			String[] line = in.readLine().split(" ");
			for (int i = 0; i < line.length; i++) {
				queue.add(new Str(line[i], map.get(line[i])));
			}
			System.out.println("#" + test_case);
			while (!queue.isEmpty()) {
				Str cur = queue.poll();
				System.out.print(cur.value + " ");
			}
			System.out.println(" ");
		}
	}
}
