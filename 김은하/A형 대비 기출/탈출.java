import java.io.*;
import java.util.*;

public class Main {
	static final int[] DX = { 0, 1, 0, -1 };
	static final int[] DY = { 1, 0, -1, 0 };
	static int R, C;
	static char[][] forest;
	static List<int[]> water = new ArrayList<>();
	static boolean[][] visited;
	static int time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		forest = new char[R][C];
		visited = new boolean[R][C];
		int[] start = new int[2];

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				forest[i][j] = line.charAt(j);
				if (forest[i][j] == 'S') {
					start[0] = i;
					start[1] = j;
					forest[i][j] = '.';
				}
				if (forest[i][j] == '*') {
					water.add(new int[] { i, j });
				}
			}
		}

		findWay(start);

		if (time < 1) {
			bw.write("KAKTUS");
		} else {
			bw.write(String.valueOf(time));
		}

		br.close();
		bw.close();
	}

	public static void findWay(int[] start) {
		Queue<int[]> q = new LinkedList<>();
		HashMap<Integer, List<int[]>> depthList = new HashMap<>();
		boolean[] spreaded = new boolean[R * C];
		int depth = 0, dx = 0, dy = 0;
		int[] now = new int[2];
		visited[start[0]][start[1]] = true;
		q.add(start);

		depthList.put(0, new ArrayList<>(Arrays.asList(start)));

		while (!q.isEmpty()) {
			now = q.poll();
			if (forest[now[0]][now[1]] == '*') {
				continue;
			}
			for (int k = 0; k < depthList.size(); k++) {
				if (depthList.getOrDefault(k, new ArrayList<>()).contains(now)) {
					depth = k + 1;
					break;
				}
			}
			if (!spreaded[depth - 1]) {
				spreadWater();
				spreaded[depth - 1] = true;
			}

			for (int i = 0; i < 4; i++) {
				dx = now[0] + DX[i];
				dy = now[1] + DY[i];
				if (dx >= 0 && dx < R && dy >= 0 && dy < C && !visited[dx][dy] && forest[dx][dy] == '.') {
					int[] temp = { dx, dy };
					q.add(temp);
					depthList.put(depth, depthList.getOrDefault(depth, new ArrayList<>()));
					depthList.get(depth).add(temp);
					visited[dx][dy] = true;
				}
				if (dx >= 0 && dx < R && dy >= 0 && dy < C && !visited[dx][dy] && forest[dx][dy] == 'D') {
					for (int k = 0; k < depthList.size(); k++) {
						if (depthList.getOrDefault(k, new ArrayList<>()).contains(now)) {
							time = k + 1;
							return;
						}
					}
				}
			}
		}
	}

	public static void spreadWater() {
		int wx = 0, wy = 0, dx = 0, dy = 0;
		int size = water.size();

		for (int i = 0; i < size; i++) {
			wx = water.get(i)[0];
			wy = water.get(i)[1];

			for (int j = 0; j < 4; j++) {
				dx = wx + DX[j];
				dy = wy + DY[j];
				if (dx >= 0 && dx < R && dy >= 0 && dy < C && forest[dx][dy] == '.') {
					forest[dx][dy] = '*';
					water.add(new int[] { dx, dy });
				}
			}
		}

		for (int i = 0; i < size; i++) {
			water.remove(0);
		}
	}
}
