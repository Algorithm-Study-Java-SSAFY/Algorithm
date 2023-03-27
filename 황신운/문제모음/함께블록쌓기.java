package testBaek;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 함께블록쌓기{
	public static int N, M, H, validate = 0;
	public static int[][] info;
	public static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);
		H = Integer.parseInt(tmp[2]);
		
		info = new int[N][];
		for(int i=0; i<N; i++) {
			tmp = br.readLine().split(" ");
			info[i] = new int[tmp.length+1];
			for(int j=0; j<tmp.length; j++) {
				info[i][j+1] = Integer.parseInt(tmp[j]);
			}
		}
		
		solve();
		System.out.println(validate%10007);
	}
	
	public static void solve() {
		
	}
	
	
}
