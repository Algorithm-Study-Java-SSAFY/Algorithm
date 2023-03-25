import java.util.*;
import java.io.*;

public class Main {

	/*
	 * N : 1층 ~ N층
	 * K: 최대 K자리 수 
	 * P: 최대 반전 개수 
	 * X: 멈추는 층 
	 */

	static int N ,K, P;
	static List<Integer> X;
	static int[][] numK;  
	static int intX;
	
	static int[][] number = {
				{0,4,3,3,4,3,2,3,1,2},
				{4,0,5,3,2,5,6,1,5,4},
				{3,5,0,2,5,4,3,4,2,3},
				{3,3,2,0,3,2,3,2,2,1},
				{4,2,5,3,0,3,4,3,3,2},
				{3,5,4,2,3,0,1,4,2,1},
				{2,6,3,3,4,1,0,5,1,2},
				{3,1,4,2,3,4,5,0,4,3},
				{1,5,2,2,3,2,1,4,0,1},
				{2,4,3,1,2,1,2,3,1,0}
			}; 
	
	static int resCnt; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] data = br.readLine().split(" "); 
		N = Integer.parseInt(data[0]);
		K = Integer.parseInt(data[1]);
		P = Integer.parseInt(data[2]);
		
		String[] sX = data[3].split("");
		intX = Integer.parseInt(data[3]); 
		
		// 앞자리 0 채우기
		X = new ArrayList<>();
		for(int i=0; i<K-sX.length; i++) {
			X.add(0);
		}
		
		for(int i=0; i<sX.length; i++) {
			X.add(Integer.parseInt(sX[i]));
		}
		
		System.out.println("X:" + X);

		combination("",0,0);

		System.out.println(resCnt);
		
		
	}
	
	//48 2 6 5
	static void combination(String numS ,int cnt ,int deep) {  // deep의 최대 깊이는 K
		
		if(!numS.equals("")) {
			if( cnt > P || Integer.parseInt(numS) > N) {
				return; // 반전 개수를 넘으면 끝 || 최대 층 수를 넘어도 끝 || 멈추는 층과 똑같으면 
			}
		}

		
		if(deep == K) { 
			int num = Integer.parseInt(numS);
			if(1 <= num && num <=N && Integer.parseInt(numS) != intX) { // 0층은 제외 
				System.out.println(numS);
				resCnt++;
			}
			return; 
		}
		
		for(int i=0; i<10; i++) {
			int c = number[X.get(deep)][i];
	
			combination(numS+i , cnt+c ,deep+1);
		}
	}



}