import java.util.*;
import java.io.*;

public class Main {
	static int N, K;
	static long[] food;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		food = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			food[i] = Long.parseLong(st.nextToken());
		}

		sb.append(calEnergy());

		System.out.println(sb);
		br.close();
	}

	public static long calEnergy() {

		for (int i = 1; i < N; i++) {
			food[i] += food[i - 1];
		}

		int start = 0, end = 0;
		long value = 0;
		long[] dp = new long[N];

		while (end < N) {

			value = start > 0 ? food[end] - food[start - 1] - (long) K : food[end] - (long) K;

			if (value < 0) {
				dp[end] = Math.max(dp[end], end > 0 ? dp[end - 1] : dp[0]);
				end++;
				continue;
			}
			if (value >= 0) {
				dp[end] = Math.max(start > 0 ? dp[start - 1] + value : dp[0] + value, dp[end]);

				if (start == end)
					end++;
				start++;
			}

		}
		return dp[N - 1];
	}
}
