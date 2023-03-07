package testBaek;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 무선충전 {
	public static int M, A;
	public static int[] AWay, BWay;
	public static int[][] BC;
	public static int maxNum;
	public static int[] dx = { 0, 0, 1, 0, -1 };
	public static int[] dy = { 0, -1, 0, 1, 0 };

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			M = sc.nextInt();
			A = sc.nextInt();

			AWay = new int[M];
			BWay = new int[M];
			BC = new int[A][4];

			for (int i = 0; i < M; i++)
				AWay[i] = sc.nextInt();
			for (int i = 0; i < M; i++)
				BWay[i] = sc.nextInt();
			for (int i = 0; i < A; i++) {
				for (int j = 0; j < 4; j++) {
					BC[i][j] = sc.nextInt();
				}
			}
			
			maxNum = 0;
			solve(1, 1, 10, 10);
			System.out.println("#" + test_case + " " + maxNum);
		}
	}

	public static void solve(int ax, int ay, int bx, int by) {
		List<Integer> AList = new ArrayList<>();
		List<Integer> BList = new ArrayList<>();

		for (int i = 0; i <= M; i++) {
			AList = possibleCharge(ax, ay);
			BList = possibleCharge(bx, by);

			getMaxCharge(AList, BList);
			if (i == M)
				break;
			ax += dx[AWay[i]];
			ay += dy[AWay[i]];
			bx += dx[BWay[i]];
			by += dy[BWay[i]];
		}
	}

	public static List<Integer> possibleCharge(int x, int y) {
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < A; i++) {
			int bcX = BC[i][0];
			int bcY = BC[i][1];
			int C = BC[i][2];

			int distance = Math.abs(x - bcX) + Math.abs(y - bcY);
			if (distance <= C)
				list.add(i);
		}

		return list;
	}

	public static void getMaxCharge(List<Integer> AList, List<Integer> BList) {
		int maxAdd = 0;
		if (BList.size() == 0) {
			for (int i = 0; i < AList.size(); i++) {
				maxAdd = Math.max(maxAdd, BC[AList.get(i)][3]);
			}
		} else if (AList.size() == 0) {
			for (int i = 0; i < BList.size(); i++) {
				maxAdd = Math.max(maxAdd, BC[BList.get(i)][3]);				
			}
		} else if (AList.size() == 1 && BList.size() == 1 && (AList.get(0) == BList.get(0))) {
			maxAdd = BC[AList.get(0)][3];
		} else {
			for (int i = 0; i < AList.size(); i++) {
				for (int j = 0; j < BList.size(); j++) {
					if (AList.get(i) != BList.get(j))
						maxAdd = Math.max(maxAdd, BC[AList.get(i)][3] + BC[BList.get(j)][3]);
				}
			}
		}
		maxNum += maxAdd;
	}
}