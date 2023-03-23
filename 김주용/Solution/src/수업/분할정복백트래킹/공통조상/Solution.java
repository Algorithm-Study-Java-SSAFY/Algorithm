package 수업.분할정복백트래킹.공통조상;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Solution {

	static int V;
	static int E;
	static int nodeA;
	static int nodeB;

	static int[] topGraph;

	static int[] visitedA;
	static int[] visitedB;

	static ArrayList[] downGraph;
	static boolean[] visited;

	static int root;
	static int subSize;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/sample_input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			V = line[0];
			E = line[1];
			nodeA = line[2];
			nodeB = line[3];
			topGraph = new int[V + 1];
			visitedA = new int[V + 1];
			visitedB = new int[V + 1];

			downGraph = new ArrayList[V + 1];
			for(int i = 1; i <= V; i++) {
				downGraph[i] = new ArrayList<>();
			}
			visited = new boolean[V + 1];

			line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int i = 0; i < 2 * E; i += 2) {
				topGraph[line[i + 1]] = line[i];
				downGraph[line[i]].add(line[i + 1]);
			}
			subSize = 1;
			solution();
			System.out.printf("#%d %d %d\n", test_case, root, subSize);
		}
	}

	public static void solution() {
		findParent(visitedA, nodeA, 0);
		findParent(visitedB, nodeB, 0);
		root = getCommonNode();
		dfs(root, 1);
	}

	// 가장 가까운 공통 조상
	public static int getCommonNode() {
		int root = 0;
		int depth = Integer.MAX_VALUE;
		for (int i = 1; i <= V; i++) {
			if (visitedA[i] > 0 && visitedB[i] > 0) {
				if (visitedA[i] + visitedB[i] < depth) {
					root = i;
					depth = visitedA[i] + visitedB[i];
				}
			}
		}

		return root;
	}

	// 조상 노드 찾아가기
	public static void findParent(int[] visited, int cur, int depth) {
		visited[cur] = depth;
		if (topGraph[cur] != 0) {
			findParent(visited, topGraph[cur], depth + 1);
		}
	}

	// 서브트리 크기 구하기
	public static void dfs(int cur, int size) {
		ArrayList<Integer> children = (ArrayList<Integer>) downGraph[cur];
		for(int i : children) {
			if (!visited[i]) {
				visited[i] = true;
				subSize += 1;
				dfs(i, size + 1);
				visited[i] = false;
			}
		}
	}

}
