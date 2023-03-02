import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
	static int N, H;
	static int min = Integer.MAX_VALUE;
	static List<Integer> section = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		List<Integer> down = new ArrayList<>();
		List<Integer> up = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			if (i % 2 == 1)
				down.add(Integer.parseInt(br.readLine()));
			else
				up.add(Integer.parseInt(br.readLine()));

		}

		Collections.sort(down);
		Collections.sort(up);

		for (int i = 1; i <= H; i++) {
			findMinimumDestroy(down, up, i);
		}

		bw.write(min + " " + section.size());
		br.close();
		bw.close();
	}

	public static void findMinimumDestroy(List<Integer> down, List<Integer> up, int s) {
		int dx = searchCnt(down, s);
		int ux = searchCnt(up, H - s + 1);
		int cnt = N - dx - ux;
		dx = down.size() - dx;
		ux = up.size() - ux;

		if (min == cnt) {
			section.add(s);
		} else if (min > cnt) {
			section.clear();
			min = cnt;
			section.add(s);
		}
	}

	public static int searchCnt(List<Integer> list, int find) {
		int low = 0;
		int high = N / 2 - 1;
		int mid = 0;

		while (low <= high) {
			mid = (low + high) / 2;
			if (list.get(mid) < find)
				low = mid + 1;
			else if (list.get(mid) > find)
				high = mid - 1;
			else
				break;

		}

		if (list.get(mid) != find) {
			if (list.get(mid) < find) {
				mid += 1;
			}
		} else {
			while (mid != 0 && list.get(mid - 1) == find)
				mid -= 1;
		}

		return mid;
	}
}
