package 스터디.하늘에서별똥별이빗발친다_14658;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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

		findMinShootingStar();
		bw.write(String.valueOf(K - maxOut));

		br.close();
		bw.close();
	}

	public static void findMinShootingStar() {
		int LX = 0;
		int LY = 0;
		int PX = 0;
		int PY = 0;

		for (int k = 0; k < fallingPoint.size(); k++) {
			PX = fallingPoint.get(k)[0];
			PY = fallingPoint.get(k)[1];

			for (int x = 0; x <= L; x++) {
				// 윗면
				LX = PX - x;
				LY = PY;
				calculTram(LX, LY);

				// 아랫면
				LY = PY - L;
				calculTram(LX, LY);
			}

			for (int y = 0; y <= L; y++) {
				// 왼쪽면
				LX = PX;
				LY = PY - y;
				calculTram(LX, LY);

				// 오른쪽면
				LX = PX - L;
				calculTram(LX, LY);
			}

		}

	}

	public static void calculTram(int LX, int LY) {

		int tempX = 0;
		int tempY = 0;
		int count = 0;

		if (LX >= 0 && LX + L <= N && LY >= 0 && LY + L <= M) {
			for (int k2 = 0; k2 < fallingPoint.size(); k2++) {
				tempX = fallingPoint.get(k2)[0];
				tempY = fallingPoint.get(k2)[1];

				if (tempX >= LX && tempX <= LX + L && tempY >= LY && tempY <= LY + L) {
					count++;
				}
			}
		}
		maxOut = Math.max(maxOut, count);
	}
}
