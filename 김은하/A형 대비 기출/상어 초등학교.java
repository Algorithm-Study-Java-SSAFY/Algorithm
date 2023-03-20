import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int seat[][];
	static HashMap<Integer, List<Integer>> info = new HashMap<>();
	static List<Integer> order = new ArrayList<>();
	static final int[] DX = { 0, 1, 0, -1 };
	static final int[] DY = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		seat = new int[N][N];

		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int l1 = Integer.parseInt(st.nextToken());
			int l2 = Integer.parseInt(st.nextToken());
			int l3 = Integer.parseInt(st.nextToken());
			int l4 = Integer.parseInt(st.nextToken());
			order.add(s);
			info.put(s, Arrays.asList(l1, l2, l3, l4));
		}

		chooseSeat();

		bw.write(String.valueOf(calculSatisfaction()));

		br.close();
		bw.close();
	}

	public static void chooseSeat() {
		int student = 0;
		List<Integer> like = new ArrayList<>();
		boolean start = true;
		int cnt_like = 0;
		int cnt_empty = 0;
		int temp_like = 0;
		int temp_empty = 0;
		int dx = -1;
		int dy = -1;
		int[] target = new int[2];

		for (int i = 0; i < order.size(); i++) {
			student = order.get(i);
			like = info.get(student);

			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < N; j2++) {
					if (seat[j][j2] == 0) {
						if (start) {
							start = false;
							target[0] = j;
							target[1] = j2;
						}
						for (int k = 0; k < 4; k++) {
							dx = j + DX[k];
							dy = j2 + DY[k];
							if (dx >= 0 && dy >= 0 && dx <= N - 1 && dy <= N - 1 && like.contains(seat[dx][dy]))
								temp_like++;
							if (dx >= 0 && dy >= 0 && dx <= N - 1 && dy <= N - 1 && seat[dx][dy] == 0)
								temp_empty++;
						}
						if (temp_like > cnt_like) {
							cnt_like = temp_like;
							target[0] = j;
							target[1] = j2;
							cnt_empty = temp_empty;
						} else if (temp_like == cnt_like) {
							if (temp_empty > cnt_empty) {
								cnt_empty = temp_empty;
								target[0] = j;
								target[1] = j2;
							}
						} 

						temp_like = 0;
						temp_empty = 0;
					}
				}
			}

			seat[target[0]][target[1]] = student;
			start = true;
			cnt_like = 0;
			cnt_empty = 0;
		}
	}

	public static int calculSatisfaction() {
		int result = 0;
		int dx = 0;
		int dy = 0;
		int student = 0;
		List<Integer> like;
		int cnt = 0;
		int temp = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				student = seat[i][j];
				like = info.get(student);
				for (int k = 0; k < 4; k++) {
					dx = i + DX[k];
					dy = j + DY[k];
					if (dx >= 0 && dy >= 0 && dx <= N - 1 && dy <= N - 1 && like.contains(seat[dx][dy])) {
						cnt++;
					}
				}
				for (int t = 0; t < cnt; t++) {
					if (temp == 0)
						temp = 1;
					else
						temp *= 10;
				}
				result += temp;
				cnt = 0;
				temp = 0;
			}
		}

		return result;
	}
}
