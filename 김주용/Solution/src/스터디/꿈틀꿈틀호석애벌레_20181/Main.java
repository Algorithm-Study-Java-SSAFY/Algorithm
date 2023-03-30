package 스터디.꿈틀꿈틀호석애벌레_20181;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

class Feed {
	int start;
	int end;
	long satisfy;

	public Feed(int start, int end, long satisfy) {
		this.start = start;
		this.end = end;
		this.satisfy = satisfy;
	}

	public long getEnergy(long K) {
		return this.satisfy - K;
	}

	@Override
	public String toString() {
		return "Feed [start=" + start + ", end=" + end + ", satisfy=" + satisfy + "] ";
	}

}

public class Main {

	static int N;
	static long K;
	static long[] feeds;
	static ArrayList<Feed>[] eatInfo;

	static long[] dp;
	static long answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		K = Long.parseLong(line[1]);
		feeds = Stream.of(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		eatInfo = new ArrayList[N];
		dp = new long[N];
		for (int i = 0; i < N; i++) {
			eatInfo[i] = new ArrayList<Feed>();
		}
		solution();
	}

	// DP[i] = DP[i]를 안먹는 경우 vs DP[i]를 먹는 경우
	public static void solution() {
		getEatInfo();
		dp[0] = feeds[0] >= K ? feeds[0] - K : 0; // 처음 먹이가 K 이상이면 dp에 저장 
		for (int i = 1; i < N; i++) {
			for (Feed info : eatInfo[i]) {
				if (info.start == 0) {
					dp[i] = Math.max(0 + info.satisfy, dp[i]); // 시작점이 0 idx인 경우 예외 처리 
				} else { // 시작점 - 1 까지 최댓값 + 자기 만족도를 더한 값 중 최댓값 뽑기 
					dp[i] = Math.max(dp[info.start - 1] + info.satisfy, dp[i]);
				}
			}
			// 뽑은 최댓값과 이전 dp에 저장된 최댓값 비교 후 저장 
			dp[i] = Math.max(dp[i], dp[i - 1]); 
		}
		//System.out.println(Arrays.toString(dp));
		System.out.println(dp[N - 1]);
	}

	// 투포인터 
	// 먹는게 끝나는 idx에 [시작 Idx, 끝 Idx, 축적 에너지] 저장 
	public static void getEatInfo() {
		int right = 1;
		long sum = feeds[0];
		for (int left = 0; left < N; left++) {
			while (right < N && sum < K) {
				sum += feeds[right++];
			} 
			eatInfo[right - 1].add(new Feed(left, right - 1, sum - K));
			sum -= feeds[left];
		}
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < eatInfo[i].size(); j++) {
//				System.out.print(eatInfo[i].get(j));
//			}
//			System.out.println(" ");
//		}
	}

}