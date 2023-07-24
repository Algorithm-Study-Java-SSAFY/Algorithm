import java.util.*;
import java.io.*;



public class Main {
	
	static String STR;
	static int[][] DP;
	
	public static void main(String[] args) throws Exception {
		
		init();
		
		
		solution();
		
		System.out.println(DP[0][STR.length()-1]);
		
	}
	
	static void solution() {
		makeDp();
	}
	
	static void makeDp() {
		
		DP = new int[STR.length()][STR.length()];
		
		for(int i=1;i<STR.length();i++) {
			for(int j=0;j+i<STR.length();j++) {
				int end=j+i;
				// 어떤 X가 KOI 유전자라면, aXt와 gXc도 KOI 유전자이다.
				if((STR.charAt(j)=='a'&&STR.charAt(end)=='t')|| (STR.charAt(j)=='g'&&STR.charAt(end)=='c')) {
					DP[j][end]=DP[j+1][end-1]+2;
				}
				// 어떤 X와 Y가 KOI 유전자라면, 이 둘을 연결한 XY도 KOI 유전자이다.
				for(int mid=j;mid<end;mid++) {
					DP[j][end]=Math.max(DP[j][end], DP[j][mid]+DP[mid+1][end]);
				}
			}
		}
	}
	
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		STR = br.readLine();
	
	}
}