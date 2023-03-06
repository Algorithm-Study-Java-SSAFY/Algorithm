import java.io.*;
import java.util.*;

public class Main {
	static HashMap<Integer, List<String>> puyo = new HashMap<>();
	static HashMap<Integer, List<Integer>> bomb = new HashMap<>();
	static boolean[][] visited;
	static boolean done = true;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		Stack<String> input = new Stack<>();
		for (int i = 0; i < 12; i++) {
			input.add(br.readLine());
		}
		while (!input.isEmpty()) {
			String[] str = input.pop().split("");
			for (int i = 0; i < 6; i++) {
				puyo.put(i, puyo.getOrDefault(i, new ArrayList<>()));
				puyo.get(i).add(str[i]);
			}
		}

		int total = 0;
		while (done) {
			int[] deleted = new int[6];
			visited = new boolean[6][12];
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 12 && !puyo.get(i).get(j).equals("."); j++) {
					bfs(i, j);
				}
			}

			if (bomb.isEmpty()) {
				done = false;
			} else {

					Set<Integer> k_list = bomb.keySet();
					for (int k : k_list) {
						List<Integer> value = bomb.get(k);
						Collections.sort(value);
						int x = k;
						int y = 0;
						for (int i = 0; i < value.size(); i++) {
							y = value.get(i);
							y = y - deleted[x] <= 0 ? 0 : y - deleted[x];
							deleted[x]++;
							puyo.get(x).remove(y);
							puyo.get(x).add(".");
						}
					}
				
				total++;
				bomb.clear();
			}

		}

		bw.write(String.valueOf(total));

		br.close();
		bw.close();
	}

	public static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> temp = new LinkedList<>();

		q.add(new int[] { i, j });
		temp.add(new int[] { i, j });
		visited[i][j] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			String target = puyo.get(x).get(y);

			if (y != 11 && puyo.get(x).get(y + 1).equals(target) && !visited[x][y + 1]) {
				q.add(new int[] { x, y + 1 });
				temp.add(new int[] { x, y + 1 });
				visited[x][y + 1] = true;
			}
			if (y != 0 && puyo.get(x).get(y - 1).equals(target) && !visited[x][y - 1]) {
				q.add(new int[] { x, y - 1 });
				temp.add(new int[] { x, y - 1 });
				visited[x][y - 1] = true;
			}
			if (x != 5 && puyo.get(x + 1).get(y).equals(target) && !visited[x + 1][y]) {
				q.add(new int[] { x + 1, y });
				temp.add(new int[] { x + 1, y });
				visited[x + 1][y] = true;
			}
			if (x != 0 && puyo.get(x - 1).get(y).equals(target) && !visited[x - 1][y]) {
				q.add(new int[] { x - 1, y });
				temp.add(new int[] { x - 1, y });
				visited[x - 1][y] = true;
			}
		}

		if (temp.size() >= 4) {
			while (!temp.isEmpty()) {
				int[] b = temp.poll();
				bomb.put(b[0], bomb.getOrDefault(b[0], new ArrayList<>()));
				bomb.get(b[0]).add(b[1]);
			}
		}

	}
}
