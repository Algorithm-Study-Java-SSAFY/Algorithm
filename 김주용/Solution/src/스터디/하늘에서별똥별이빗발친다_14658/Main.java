package 스터디.하늘에서별똥별이빗발친다_14658;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static int N;
	static int M;
	static int L;
	static int K;
	
	static int[][] stars;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		L = Integer.parseInt(line[2]);
		K = Integer.parseInt(line[3]);
		
		stars = new int[K][2]; 
		for(int i = 0; i < K; i++) {
			line = in.readLine().split(" ");
			stars[i] = new int[] {Integer.parseInt(line[0]), Integer.parseInt(line[1])};
		}
		solution();
	}
	
	private static void solution() {
		int answer = Integer.MAX_VALUE;
		
		// 500,000 * 500,000 * 100 = 250조
		// 한 별을 무조건 포함 하도록: 100 * L*L * 4 = 100 * 100,000 * 100,000  * 4 = 4조 
		// 
		for(int y = 0; y < M - L; y++) {
			for(int x = 0; x < N - L; x++) {
				answer = Math.min(answer, counting(y, x));
			}
		}
		System.out.println(answer);
	}
	
	public static int counting(int sy, int sx) {
		int ret = 0;
	
		for(int[] star : stars) {
			int cy = star[0], cx = star[1];
			if(!(cy <= sy + L && cy >= sy && cx <= sx + L && cx >= sx)) {
				ret++;
			}
		}
		
		return ret;
	}
}
