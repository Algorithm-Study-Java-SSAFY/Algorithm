import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
	static int N, K;
	static int[][] T;
	static int minTime = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		T = new int [N][N];
		
		for (int i = 0; i < T.length; i++) {
			String[] line = br.readLine().split(" ");
			T[i] = Stream.of(line).mapToInt(Integer::parseInt).toArray();
		}
		
		for (int k = 0; k < T.length; k++) {
			for (int i = 0; i < T.length; i++) {
				for (int j = 0; j < T.length; j++) {
					T[i][j] = Math.min(T[i][j], T[i][k] + T[k][j]);
				}
			}
		}
		
		boolean[] visited = new boolean[N];
		visited[K] = true;
		dfs(1, visited, 0, K);
		

		bw.write(String.valueOf(minTime));

		br.close();
		bw.close();
	}
	
	public static void dfs (int cnt, boolean[] visited, int wayTime, int start) {
		if (cnt == N) {
			minTime = minTime>wayTime ? wayTime : minTime;
			return;
		}
		
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(cnt+1, visited, wayTime + T[start][i], i);
				visited[i] = false;
			}
		}
	}
}
