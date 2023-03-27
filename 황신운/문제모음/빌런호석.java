package testBaek;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 빌런호석 {
	public static int N, K, P, X, digitMaxNum, answer;
	public static int[] stopNum, newNum;
	public static int[][] numChange;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		K = Integer.parseInt(tmp[1]);
		P = Integer.parseInt(tmp[2]);
		X = Integer.parseInt(tmp[3]);
		
		digitMaxNum = Integer.parseInt("" + tmp[0].charAt(0));	// 층 N의 최대자리 수 N = 48이라면 4가 저장
		stopNum = new int[K];	// X 값을 배열에 자릿수에 따라 저장
		for(int i=K-1, j=tmp[3].length()-1; j>=0; j--, i--)
			stopNum[i] = Integer.parseInt("" + tmp[3].charAt(j));

		solve();
		System.out.println(answer);
	}
	
	public static void solve() {
		numChange = new int[10][10];
		setNumChange();	// A->B 일 때 바뀌는 횟수 미리 저장 
		
		newNum = new int[K];
		answer = 0;
		getNewNum(0);
	}
	public static void getNewNum(int level) {	// 자릿수 K에 대해 나올 수 있는 수의 경우 모두 구하기 
		if(level == K) {
			boolean check = checkValiable();	// 해당 경우가 유효한지 확인 후 유효하면 반전 경우 수 + 1
			if(check) answer++;
			return;
		}
		int maxNum = 9;
		if(level == 0) maxNum = digitMaxNum;
		for(int i=0; i<=maxNum; i++) {
			newNum[level] = i;
			getNewNum(level+1);
		}
	}
	
	public static boolean checkValiable() {
		if(!checkNum()) return false;	// 수가 N보다 큰지, X랑 같은 값인지 확인 
		int changedCnt = 0;
		for(int i=0; i<K; i++) {
			changedCnt += numChange[stopNum[i]][newNum[i]];
		}
		// 반전된 횟수 합이 P보다 작으면 참 
		if(changedCnt<=P) return true;
		return false;
	}
	
	// 수가 N보다 큰지, X랑 같은 값인지 확인
	public static boolean checkNum() {
		int number = 0;
		for(int i=0; i<K; i++) {
			number += newNum[i] * Math.pow(10, K-1-i);
		}

		if(number == X || number == 0) return false;
		if(number <= N) return true;
		return false;
	}
	public static void setNumChange() {
		int[][] ledNum = {{1,1,1,1,1,1,0}
						, {0,1,1,0,0,0,0}
						, {1,1,0,1,1,0,1}
						, {1,1,1,1,0,0,1}
						, {0,1,1,0,0,1,1}
						, {1,0,1,1,0,1,1}
						, {1,0,1,1,1,1,1}
						, {1,1,1,0,0,0,0}
						, {1,1,1,1,1,1,1}
						, {1,1,1,1,0,1,1}};
		
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(i==j) numChange[i][j] = 0;
				else {
					int cnt=0;
					for(int h=0; h<7; h++) {
						if(ledNum[i][h] != ledNum[j][h]) cnt++;
					}
					numChange[i][j] = cnt;
				}
			}
		}
	}
}
