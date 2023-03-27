package testBaek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class 부분합 {
	public static int N, S;
	public static int[] num;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		S = Integer.parseInt(tmp[1]);

		num = new int[N];
		tmp = br.readLine().split(" ");
		for (int i = 0; i < N; i++)
			num[i] = Integer.parseInt(tmp[i]);

		int result = solve();
		if(result != 100001)
			System.out.println(result);
		else
			System.out.println(0);
	}

	public static int solve() {
		int minLen = 100001, sum = 0, end = 0;

		for (int start = 0; start < N; start++) {
			while (sum < S && end < N) {
				sum += num[end++];
			}
			if (sum >= S) {
//				System.out.println("start " + start + " end " + end);
				minLen = Math.min(minLen, end - start);
			}
			sum -= num[start];
		}
		return minLen;
	}
}