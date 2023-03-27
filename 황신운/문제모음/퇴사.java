package testBaek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 퇴사 {
	public static int N, maxCost = 0;
	public static int[] T;
	public static int[] P;
	public static boolean[] possible;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		T = new int[N];
		P = new int[N];
		possible = new boolean[N];

		for (int i = 0; i < N; i++) {
			String[] tmp = br.readLine().split(" ");
			T[i] = Integer.parseInt(tmp[0]);
			P[i] = Integer.parseInt(tmp[1]);
		}

		findPossible(0);
		System.out.println(maxCost);
	}

	public static void findPossible(int idx) {
		if (idx >= N) {
			getMaxMoney();
			return;
		}
		for (int i = idx; i < N; i++) {
			possible[i] = true;
			findPossible(i + 1);
			possible[i] = false;
		}
	}

	public static void getMaxMoney() {
		int cntCost = 0;
		for (int i = 0; i < N; i++) {
			if (possible[i] && T[i] - 1 + i < N) {
				cntCost += P[i];
				i += T[i]-1;
			}
		}
		maxCost = Math.max(maxCost, cntCost);
	}

}
