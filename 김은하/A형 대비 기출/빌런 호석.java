import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		int[][] needCnt = { { 0, 4, 3, 3, 4, 3, 2, 3, 1, 2 }, 
							{ 4, 0, 5, 3, 2, 5, 6, 1, 5, 4 },
							{ 3, 5, 0, 2, 5, 4, 3, 4, 2, 3 }, 
							{ 3, 3, 2, 0, 3, 2, 3, 2, 2, 1 }, 
							{ 4, 2, 5, 3, 0, 3, 4, 3, 3, 2 },
							{ 3, 5, 4, 2, 3, 0, 1, 4, 2, 1 }, 
							{ 2, 6, 3, 3, 4, 1, 0, 5, 1, 2 }, 
							{ 3, 1, 4, 2, 3, 4, 5, 0, 4, 3 },
							{ 1, 5, 2, 2, 3, 2, 1, 4, 0, 1 }, 
							{ 2, 4, 3, 1, 2, 1, 2, 3, 1, 0 } };

		int caseCnt = 0, now = 0, needChange = 0;
		int[] changeFloor = new int[K], xFloor = new int[K];

		now = X;
		for (int i = K - 1; i >= 0; i--) {
			xFloor[i] = now % 10;
			now /= 10;
		}

		for (int i = 1; i <= N; i++) {
			now = i;

			for (int k = K - 1; k >= 0; k--) {
				changeFloor[k] = now % 10;
				now /= 10;
				needChange += needCnt[xFloor[k]][changeFloor[k]];
			}

			if (P >= needChange && i != X)
				caseCnt += 1;
			
			needChange = 0;
		}

		bw.write(String.valueOf(caseCnt));

		br.close();
		bw.close();
	}
}
