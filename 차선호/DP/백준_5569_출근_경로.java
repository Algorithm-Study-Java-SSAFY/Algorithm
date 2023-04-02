import java.util.*;
import java.io.*;


public class Main {

	static int W,H;
	static int[][][] DP;
	
	public static void main(String[] args) throws Exception{
		init();
		initDP();
		makeDP();
		System.out.println((DP[W-1][H-2][0]+DP[W-2][H-1][1])%100000);
//		System.out.println(DP[H-1][W-2][1]+DP[H-2][W-1][0]);
		
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
//				System.out.println(j+"/"+i);
//				System.out.println(j+", "+i+"--->"+Arrays.toString(DP[j][i]));
			}
		}
	}
	
	static void makeDP() {
		for(int i=1;i<H;i++) {
			for(int j=1;j<W;j++) {
				if(j>1 && i>1) {
					DP[j][i][0] = (DP[j][i-1][0]+DP[j-2][i][1])%100000;
					DP[j][i][1] = (DP[j-1][i][1]+DP[j][i-2][0])%100000;
				}else if(i==1&&j>1) {
					DP[j][i][0] = (DP[j-2][i][1]+1)%100000;
					DP[j][i][1] = 1;
				}else if(j==1&&i>1) {
					DP[j][i][0] = 1;
					DP[j][i][1] = (DP[j][i-1][0]+1)%100000;
				}
			}
		}
	}
	
	static void initDP() {
		for(int i=0;i<H;i++) {
			DP[0][i][0] = 1;
			DP[0][i][1] = 1;
		}
		for(int i=0;i<W;i++) {
			DP[i][0][0] = 1;
			DP[i][0][1] = 1;
		}
		DP[1][1][0] = 1;
		DP[1][1][1] = 1;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] WH = br.readLine().split(" ");
		W = Integer.parseInt(WH[0]);
		H = Integer.parseInt(WH[1]);
		DP = new int[W][H][2];
	}
}