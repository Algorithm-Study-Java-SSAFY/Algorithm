import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		boolean[][] block = new boolean[H][W];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			int len = Integer.parseInt(st.nextToken());
			for (int j = H - 1; j >= H - len; j--) {
				block[j][i] = true;
			}
		}

		int total_rain = 0;
		Stack<Integer> stack = new Stack<>();
		int cnt = 0;

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (block[i][j]) {
					if (stack.isEmpty()) {
						stack.add(j);
					} else {
						if (stack.peek() != j - 1) {
							cnt += j - stack.pop() - 1;
						}
						stack.add(j);
					}
				}
			}
			total_rain += cnt;
			cnt = 0;
			stack.clear();
		}

		bw.write(String.valueOf(total_rain));

		br.close();
		bw.close();
	}
}
