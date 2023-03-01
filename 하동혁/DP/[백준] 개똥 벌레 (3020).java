import java.util.*;
import java.io.*;


public class Main {

	static int N,H; // 넓이(짝수), 높이
	static int[] HighTop;
	static int[] HightDown;
	static int[] High; 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] NH = br.readLine().split(" ");
		int N = Integer.parseInt(NH[0]);
		int H = Integer.parseInt(NH[1]);
		High = new int[H];
		HighTop = new int[H];
		HightDown = new int[H];
		
		for(int i=0; i<N; i++) { // i가 홀수일때 아래 / 짝수일때 위 
			int num = Integer.parseInt(br.readLine());
			
			if(i%2 == 0) { // 위 HighUp
				HightDown[num-1] += 1; 
				
			}else { // 아래  HightDown
				HighTop[H-num] += 1;  
			}
		}
		
		
		// 위쪽 더해주기 
		for(int i=0; i<H-1; i++) {
			HighTop[i+1] = HighTop[i] + HighTop[i+1];
			High[i+1] += HighTop[i+1];
		}
		
		// 아래쪽 더해주기 
		for(int i=(H-1); i>0; i--) {
			HightDown[i-1] = HightDown[i] + HightDown[i-1];
			High[i-1] += HightDown[i-1];
		}
		
		
		Arrays.sort(High);
		int minN = High[0];
		int idx = 0; 
		int cnt = 0;
		while(minN == High[idx]) {
			cnt ++; 
			idx ++; 
		}
				

		System.out.println(minN+" "+cnt);
		
	}
	
	

}