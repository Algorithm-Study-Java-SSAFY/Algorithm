package testBaek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 개똥벌레 {
	public static int N;
	public static int H;
	public static int min_num;
	public static int cnt;
	public static int[] top;
	public static int[] bottom;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		H = Integer.parseInt(tmp[1]);
		top = new int[N / 2];
		bottom = new int[N / 2];

		for (int i = 0, bIdx = 0, tIdx = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if (i % 2 == 0)
				bottom[bIdx++] = num;
			else
				top[tIdx++] = num;
		}

		Arrays.sort(top);
		Arrays.sort(bottom);

		min_num = 200000;
		cnt = 0;

		getWay();
		System.out.println(min_num + " " + cnt);
	}

	public static void getWay() {
		int topCnt = 0;
		int bottomCnt = 0;

		for (int i = 1; i <= H; i++) {
			topCnt = N / 2 - getIdx(H + 1 - i, top);
			bottomCnt = N / 2 - getIdx(i, bottom);
//			System.out.println("topCnt " + topCnt + " bcnt " + bottomCnt);

			if (min_num > topCnt + bottomCnt) {
				min_num = topCnt + bottomCnt;
				cnt = 1;
			} else if (min_num == topCnt + bottomCnt)
				cnt++;
//			
//			System.out.println("i " + i + "  " + "min_num "  + min_num + "cnt " + cnt);
//			System.out.println("____________");
		}
	}

	public static int getIdx(int height, int[] arr) {
		int start = 0;
		int end = N / 2;

		while (start < end) {
			int mid = (start + end) / 2;
			if (height <= arr[mid]) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}

//		System.out.println(type + " idx " + end);
		return end;
	}
}
