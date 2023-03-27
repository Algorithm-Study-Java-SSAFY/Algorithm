package testBaek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class 게리맨더링2 {
	public static int N, minPop = 40000;
	public static int[][] map;
	public static boolean[][] areaMap;
	public static List<Integer[]> five;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			String[] tmp = br.readLine().split(" ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(tmp[j - 1]);
			}
		}

		solve();
		System.out.println(minPop);
	}

	public static void solve() {
		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				for (int d1 = 1; d1 <= N; d1++) {
					for (int d2 = 1; d2 <= N; d2++) {
						if (x + d1 + d2 <= N && y - d1 >= 1 && y + d2 <= N) {
							areaMap = new boolean[N + 1][N + 1];
							checkFourArea(x, y, d1, d2);
						}
					}
				}
			}
		}
	}

	public static void checkFourArea(int x, int y, int d1, int d2) {
		int area1 = 0, area2 = 0, area3 = 0, area4 = 0;
		int area1P = 0, area2P = 0, area3P = 0, area4P = 0, area5P = 0;
		int maxPopulation = 0, minPopulation = 40000;

		getArea5(x, y, d1, d2);
		area5P = getInside();
		maxPopulation = Math.max(maxPopulation, area5P);
		minPopulation = Math.min(minPopulation, area5P);

		for (int j = 1; j < y; j++) {
			for (int i = 1; i <= x + d1; i++) {
				if (!areaMap[j][i]) {
					areaMap[j][i] = true;
					area1++;
					area1P += map[j][i];
				}
			}
		}
		maxPopulation = Math.max(maxPopulation, area1P);
		minPopulation = Math.min(minPopulation, area1P);

		for (int j = y; j <= N; j++) {
			for (int i = 1; i < x + d2; i++) {
				if (!areaMap[j][i]) {
					areaMap[j][i] = true;
					area2++;
					area2P += map[j][i];
				}
			}
		}
		maxPopulation = Math.max(maxPopulation, area2P);
		minPopulation = Math.min(minPopulation, area2P);

		for (int j = 1; j <= y - d1 + d2; j++) {
			for (int i = x + d1; i <= N; i++) {
				if (!areaMap[j][i]) {
					areaMap[j][i] = true;
					area3++;
					area3P += map[j][i];
				}
			}
		}
		maxPopulation = Math.max(maxPopulation, area3P);
		minPopulation = Math.min(minPopulation, area3P);

		for (int j = N; j >= y - d1 + d2; j--) {
			for (int i = x + d2; i <= N; i++) {
				if (!areaMap[j][i]) {
					areaMap[j][i] = true;
					area4++;
					area4P += map[j][i];
				}
			}
		}
		maxPopulation = Math.max(maxPopulation, area4P);
		minPopulation = Math.min(minPopulation, area4P);

		if (area1 != 0 && area2 != 0 && area3 != 0 && area4 != 0) {
			minPop = Math.min(minPop, maxPopulation - minPopulation);
		}
	}

	public static void getArea5(int x, int y, int d1, int d2) {

		for (int i = x, j = y; i <= x + d1; i++, j--) {
			areaMap[j][i] = true;
		}

		for (int i = x, j = y; i <= x + d2; i++, j++) {
			areaMap[j][i] = true;
		}

		for (int i = x + d1, j = y - d1; i <= x + d1 + d2; i++, j++) {
			areaMap[j][i] = true;
		}

		for (int i = x + d2, j = y + d2; i <= x + d2 + d1; i++, j--) {
			areaMap[j][i] = true;
		}
	}

	public static int getInside() {
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			int start = -1, end = -1;
			for (int j = 1; j <= N; j++) {
				if (areaMap[i][j]) {
					if (start == -1)
						start = j;
					else
						end = j;
				}
				if (start != -1 && end != -1) {
					for (int s = start + 1; s < end; s++) {
						areaMap[i][s] = true;
					}
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (areaMap[i][j]) {
					cnt += map[i][j];
				}
			}
		}
		return cnt;
	}
}
