package 스터디.함께블록쌓기_18427;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

	static int N;
	static int M;
	static int H;

	static int[][] blocks;
	static boolean[][] visited;

	static int answer = 0;

	static int divisor = 10007;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = line[0];
		M = line[1];
		H = line[2];

		blocks = new int[N][];
		visited = new boolean[N][];
		for (int i = 0; i < N; i++) {
			int[] init = { 0 };
			line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] block = new int[line.length + 1];
			System.arraycopy(init, 0, block, 0, 1);
			System.arraycopy(line, 0, block, 1, line.length);

			blocks[i] = block;
			visited[i] = new boolean[blocks[i].length + 1];
		}

		solution();
	}

	private static void solution() {
		long[][] dp = new long[N][H + 1];
		for(int block : blocks[0]) {
			dp[0][block] = 1;
		}
		
		for (int i = 1; i < N; i++) {
			for(int h = 1; h <= H; h++) {	
				dp[i][h] += (dp[i-1][h] % divisor);	// 1. 이전 까지 학생들이 만든 경우 
			}
			for(int cur : blocks[i]) {	
				dp[i][cur] += 1; // 2. 현재 학생이 가진 블럭으로 만드는 경우
				if(cur == 0) { // 0으로 더하는 경우는 2.에 포함됨 
					continue;
				}
				for(int h = 1; h < H; h++) { // 3. 현재 학생이 가진 블럭 높이와 이때까지 만들 수 있던 높이를 더해서 만들어지는 결과  
					if(dp[i-1][h] > 0 && cur + h <= H) {
						dp[i][cur + h] += (dp[i-1][h] % divisor);
					}
				}
				//System.out.println("");
			}
		}
		for(long[] h : dp) {
			System.out.println(Arrays.toString(h));
		}
		System.out.println(dp[N-1][H] % divisor);
	}

}
