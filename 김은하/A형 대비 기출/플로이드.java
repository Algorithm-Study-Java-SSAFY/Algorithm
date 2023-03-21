import java.io.*;
import java.util.*;

public class Main {
	static int[][] time;
	static final int INF = 10000001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		time = new int[N][N];
		for (int i = 0; i < time.length; i++) {
			for (int j = 0; j < time.length; j++) {
				if (i != j) {
					time[i][j] = INF;
				}
			}
		}

		int from = 0, to = 0, cost = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken()) - 1;
			to = Integer.parseInt(st.nextToken()) - 1;
			cost = Integer.parseInt(st.nextToken());
			time[from][to] = cost < time[from][to] ? cost : time[from][to];
		}

		floydWarshall();
		for (int i = 0; i < time.length; i++) {
			for (int j = 0; j < time.length; j++) {
				if (time[i][j] >= INF) {
					bw.write("0 ");
				} else {
					bw.write(time[i][j] + " ");
				}
			}
			bw.write("\n");
		}

		br.close();
		bw.close();
	}

	public static void floydWarshall() {
		for (int k = 0; k < time.length; k++) {
			for (int i = 0; i < time.length; i++) {
				for (int j = 0; j < time.length; j++) {
					time[i][j] = Math.min(time[i][j], time[i][k] + time[k][j]);
				}
			}
		}
	}
}
