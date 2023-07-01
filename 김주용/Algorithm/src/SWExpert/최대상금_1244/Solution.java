package SWExpert.최대상금_1244;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Solution {
	static int answer;
	static char[] numStr;
	static int change;
	static int N;
	static int maxRet;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			String[] line = in.readLine().split(" ");
			numStr = line[0].toCharArray(); 
			change = Integer.parseInt(line[1]);
			answer = 0;
			N = numStr.length;
			getMaxRet();
			solution(0, getClone(), 0);
			
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}
	
	/*
	 * 완전 탐색하는데 만약 최댓값이랑 똑같은 수를 발견하면 바로 단축 
	 */
	public static void solution(int idx, char[] ret, int cnt) {

		if(cnt > change) {
			return;
		}
		
		System.out.println(Integer.parseInt(String.valueOf(ret)));
		int retNum = Integer.parseInt(String.valueOf(ret));
		if(cnt == change) {	
			answer = Math.max(answer, retNum);
		}
		
		if(retNum == maxRet) {	// 최대 구할 수 있는 거랑 같으면  
			change = (change - cnt) % 2;
			if(change == 0) {	// swap이 짝수 회 만큼 남았으면 그대로 종료
				answer = maxRet;
				return;
			} else {	// 홀수 회 만큼 남았으면 
				if(isDuplicate(ret)) {	// 중복된 숫자가 없으면 마지막 두자리 swap
					ret = swap(ret, N-1, N-2);
				} 
				answer = Integer.parseInt(String.valueOf(ret));	// 있으면 그대로
				return;
			}
		}
		
		for(int i = idx + 1; i < N; i++) {
			if(numStr[i] - '0' >= numStr[idx] - '0') {	// 자기보다 크거나 같으면 swap 재귀 
				char[] swapNum = swap(ret.clone(), idx, i);
				solution(idx + 1, swapNum, cnt + 1);
			} else {	// 아니면 인덱스만 증가 시키기 
				solution(idx + 1, ret.clone(), cnt);
			}
		}
	}
	
	public static char[] swap(char[] newNum, int idx1, int idx2) {
		char temp = newNum[idx1];
		newNum[idx1] = newNum[idx2];
		newNum[idx2] = temp;
		return newNum;
	}
	
	public static char[] getClone() {
		char[] newNum = new char[N];
		for(int i = 0; i < N; i++) {
			newNum[i] = numStr[i];
		}
		return newNum;
	}
	
	
	public static void getMaxRet() {
		char[] maxNum = new char[N];
		for(int i = 0; i < N; i++) {
			maxNum[i] = numStr[i];
		}
		char[] ret = new char[N];
		Arrays.sort(maxNum);
		for(int i = 0; i < N; i++) {
			ret[i] = maxNum[N - i - 1];
		}
		maxRet = Integer.parseInt(String.valueOf(ret));
	}
	
	public static boolean isDuplicate(char[] ret) {
		HashSet<Character> set = new HashSet<>();
		for(char e : ret) {
			set.add(e);
		}
		return set.size() == N;
	}
}

