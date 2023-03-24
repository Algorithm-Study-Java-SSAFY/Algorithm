import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] bossInfo = new int[N + 1];
		long[] compliment = new long[N + 1];

		st = new StringTokenizer(br.readLine());
		int boss = 0;
		for (int i = 1; i <= N; i++) {
			bossInfo[i] = Integer.parseInt(st.nextToken());
		}

		int type = 0, who = 0, node = 0;
		long w = 0, total = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			type = Integer.parseInt(st.nextToken());
			who = Integer.parseInt(st.nextToken());

			if (type == 1 && who != 1) {

				w = Long.parseLong(st.nextToken());
				compliment[who] += w;

			} else if (type == 2) {

				while (who != 1) {
					total += compliment[who];
					who = bossInfo[who];
				}

				sb.append(total).append("\n");
				total = 0;
			}
		}

		System.out.println(sb);
		br.close();
	}
}
