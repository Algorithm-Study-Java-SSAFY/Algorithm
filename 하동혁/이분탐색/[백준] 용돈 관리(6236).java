import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

	static int[] data;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);

		data = new int[n];
		int end = 0;
		int start = 0;
		int mid = 0;
		for (int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(br.readLine());
			end += data[i];
			if (start < data[i])
				start = data[i];
		}

		// 이분 탐색 start ~ end
		int res = 0;
		while (start <= end) {
			mid = (start + end) / 2;
			int cnt = useMoney(mid); // res: 출금 횟수

			if (cnt > m) { // 출금 횟수가 기준치보다 더 많은 경우
				start = mid + 1;
			} else if (cnt <= m) { // 출금 횟수가 기준치보다 더 적은 경우
				end = mid - 1;
				res = mid;
			}
		}
		System.out.println(res);

	}

	static int useMoney(int m) {
		int useM = m;
		int cnt = 1;
		for (int i : data) {
			if (i <= useM) {
				useM -= i;
			} else if (i > useM) {
				cnt += 1;
				useM = m - i;
			}
		}
		return cnt;
	}

}