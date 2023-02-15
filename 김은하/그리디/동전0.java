import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Integer[] A = new Integer[N];
		for (int i = 0; i < A.length; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(A, Collections.reverseOrder());

		int min_count = 0;

		for (int i = 0; i < A.length; i++) {
			if (K == 0) {
				break;
			}
			int temp = K / A[i];
			if (temp == 0) {
				continue;
			}
			K -= temp*A[i];
			min_count += temp;
		}
		
		System.out.println(min_count);

	}

}
