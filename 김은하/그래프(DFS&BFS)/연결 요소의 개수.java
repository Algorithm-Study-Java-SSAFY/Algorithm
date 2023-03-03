import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
	static boolean[] visited;
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			graph.put(i, new ArrayList<>());
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int idx1 = Integer.parseInt(st.nextToken());
			int idx2 = Integer.parseInt(st.nextToken());
			
				graph.get(idx1).add(idx2);
				graph.get(idx2).add(idx1);
		}
		visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			if (visited[i] == false) {
				dfs(i);
				cnt += 1;
			}
		}

		System.out.println(cnt);

		br.close();
	}

	public static void dfs(int i) {
		visited[i] = true;

		List<Integer> temp = graph.get(i);
		for (int j = 0; j < temp.size(); j++) {
			int next = temp.get(j);
			if (visited[next] == false)
				dfs(next);
		}
	}
}
