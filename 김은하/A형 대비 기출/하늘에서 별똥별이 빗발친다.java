import java.io.*;
import java.util.*;

// 63%에서 틀림

public class Main {
	static int N, M, L, K;
	static List<int[]> fallingPoint = new ArrayList<>();
	static int maxOut = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			fallingPoint.add(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
		}

		if (L >= N && L >= M) {
			maxOut = K;
		} else {
			findMinShootingStar();
		}
		bw.write(String.valueOf(K - maxOut));

		br.close();
		bw.close();
	}

	public static void findMinShootingStar() {
		int PX = 0, PY = 0, tempX = 0, tempY = 0, count = 0;

		for (int k1 = 0; k1 < fallingPoint.size(); k1++) {
			for (int k2 = 0; k2 < fallingPoint.size(); k2++) {
				PX = fallingPoint.get(k1)[0];
				PY = fallingPoint.get(k2)[1];
				for (int f = 0; f < fallingPoint.size(); f++) {
					tempX = fallingPoint.get(f)[0];
					tempY = fallingPoint.get(f)[1];

					if (tempX >= PX && tempX <= PX + L && tempY >= PY && tempY <= PY + L) {
						count++;
					}
					
				}
				maxOut = Math.max(maxOut, count);
				count = 0;
			}
		}

	}
}하늘에서 별똥별이 빗발친다
