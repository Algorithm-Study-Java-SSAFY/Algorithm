package 스터디.부분합_1806;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int S;

	static int[] nums;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		S = Integer.parseInt(line[1]);

		nums = new int[N + 1];
		line = in.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(line[i]);
		}

		System.out.println(solution());
	}

	public static int solution() {
		int left = 0;
		int right = 0;
		int sum = 0;
		int length = 0;
		while (right <= N) {
			if (sum < S) { // 조건 만족할 때 까지 오른쪽 증가
				sum += nums[right];
				right += 1;
			} else { // 조건 만족하면 왼쪽 포인터 증가
				sum -= nums[left];
				length = right - left;
				left += 1;
				answer = Math.min(answer, length);
			}
		}
		if (answer == Integer.MAX_VALUE) {
			return 0;
		}

		return answer;
	}
}
