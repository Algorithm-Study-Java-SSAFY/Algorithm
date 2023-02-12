import java.io.*;
import java.io.IOException;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<Integer> money = new ArrayList<>(N);
		List<Integer> calcul = new ArrayList<>(N);
		int total = 0;
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(br.readLine());
			money.add(temp);
			calcul.add(temp);
			total += temp;
		}

		Collections.sort(calcul, Collections.reverseOrder());
		int min = calcul.get(0);
		int answer = 0;

		while (min <= total) {
			int mid = (min + total) / 2;

			int temp = mid;
			int cnt = M - 1;
			for (int i = 0; i < money.size(); i++) {
				if (temp > money.get(i)) {
					temp -= money.get(i);
				} else {
					cnt--;
					temp = mid;
					temp -= money.get(i);
				}
			}
			if (cnt >= 0) {
				total = mid - 1;
			} else {
				min = mid + 1;
			}
			answer = mid;
		}

		System.out.println(answer);
	}
}
