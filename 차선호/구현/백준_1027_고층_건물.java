import java.util.*;
import java.io.*;


public class Main {
	
	static int N, ANSWER;
	static double[] BUILDINGS;
	
	public static void main(String[] args) throws Exception {
		
		init();
		
		solution();
		
		System.out.println(ANSWER);
	}
	
	static void solution() {
		for(int i=0;i<N;i++) {
			int cnt = 0;
			if(i!=0) cnt += leftSearch(i);
//			System.out.println(i + "---> left : " + cnt);
			if(i!=N-1) cnt += rightSearch(i);
//			System.out.println(i + "---> total : " + cnt);
			ANSWER = Math.max(ANSWER, cnt);
		}
	}
	
	static int leftSearch(int idx) {
		int result = 1;
		double minIncline = getIncline(idx, idx-1);  
		
		for(int i=idx-2;i>=0;i--) {
			double currentIncline = getIncline(idx,i);
			if(currentIncline < minIncline) {
				result++;
				minIncline = currentIncline;
			}
		}
		
		return result;
	}
	
	static int rightSearch(int idx) {
		int result = 1;
		double maxIncline = getIncline(idx, idx+1);  
		
		for(int i=idx+2;i<N;i++) {
			double currentIncline = getIncline(idx,i);
			if(currentIncline > maxIncline) {
				result++;
				maxIncline = currentIncline;
			}
		}
		
		return result;
	}
	
	static double getIncline(int idx1, int idx2) {
		return (BUILDINGS[idx2]-BUILDINGS[idx1]) / (idx2 - idx1); 
	}
	
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		BUILDINGS = new double[N];
		String[] input = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			BUILDINGS[i] = Double.parseDouble(input[i]);
		}
		
		ANSWER = 0;
	}
}