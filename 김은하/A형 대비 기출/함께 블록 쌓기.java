import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> blockInfo = new ArrayList<>();
	static int N, M, H;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			blockInfo.add(new ArrayList<>());
			while (st.hasMoreTokens()) {
				blockInfo.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}

		bw.write(String.valueOf(findCase() % 10007));

		br.close();
		bw.close();
	}

	public static int findCase() {
		int[][] dp = new int[N + 1][H + 1];

		for (int i = 1; i <= N; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= H; j++) {
				dp[i][j] += dp[i - 1][j];
				for (int block : blockInfo.get(i - 1)) {
					if (block == j) {
						dp[i][j] += 1;
					} else if (block < j) {
						dp[i][j] += dp[i - 1][j - block];
					}
				}
				dp[i][j] %=10007;
			}
		}

		return dp[N][H];
	}
}
