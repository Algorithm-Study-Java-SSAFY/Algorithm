import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
	static int N, S;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		String[] line = br.readLine().split(" ");
		arr = Stream.of(line).mapToInt(Integer::parseInt).toArray();

		for (int i = 1; i < line.length; i++)
			arr[i] += arr[i - 1];

		int answer = Integer.MAX_VALUE;
		int sum = 0;
		int start = 0;
		int end = 0;

		while (end < N) {
			sum = start > 0 ? arr[end] - arr[start - 1] : arr[end];
			if (sum < S) {
				end++;
			}
			if (sum >= S) {
				while (sum >= S) {
					if (answer > end - start + 1) {
						answer = end - start + 1;
					}
					start++;
					sum = start > 0 ? arr[end] - arr[start - 1] : arr[end];
				}

			}
		}
		
		if (answer == Integer.MAX_VALUE) {
			answer = 0;
		}

		bw.write(String.valueOf(answer));

		br.close();
		bw.close();
	}
}
