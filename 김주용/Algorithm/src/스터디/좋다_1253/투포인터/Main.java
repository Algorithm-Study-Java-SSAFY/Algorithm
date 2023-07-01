package 스터디.좋다_1253.투포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	static int N;
	static long[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		nums = new long[N];
		String[] line = in.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(line[i]);
		}
		solution();
	}

	public static void solution() {
		int answer = 0;
		Arrays.sort(nums);
		for (int i = 0; i < N; i++) {
			long cur = nums[i];

			int left = 0;
			int right = N - 1;
			long sum = 0;
			while (left < right) {
				// 현재 찾는 수랑 같은 idx 예외 처리
				if (left == i) {
					left++;
					continue;
				}
				if (right == i) {
					right--;
					continue;
				}
				
				sum = nums[left] + nums[right];
				if (sum == cur) {
					answer++;
					break;
				}
				
				if (sum < cur) {
					left++;
				} else {
					right--;
				}
			}
		}
		System.out.println(answer);
	}
}