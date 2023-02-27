package 수업.큐.암호생성기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution {
	static Queue<Integer> queue;

	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		queue = new LinkedList<>();
		for (int test_case = 1; test_case <= T; test_case++) {
			String t = in.readLine();
			String[] line = in.readLine().split(" ");
			for (int i = 0; i < 8; i++) {
				queue.add(Integer.parseInt(line[i]));
			}

			solution();

			System.out.printf("#%s ", t);
			while (!queue.isEmpty()) {
				System.out.printf("%d ", queue.poll());
			}
			System.out.println("");
		}
	}

	public static void solution() {
		loop: while (true) {
			for (int i = 1; i <= 5; i++) {
				int cur = queue.poll();
				if (cur - i <= 0) {
					queue.add(0);
					break loop;
				}
				queue.add(cur - i);
			}

		}
	}

}