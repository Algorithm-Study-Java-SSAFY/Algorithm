package com.study01;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bj10799 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String data = br.readLine();
		System.out.println(Solution(data));
	}
	
	private static int Solution(String data) {
		int stickCnt = 1;
		int answer = 0;
		
		for(int i=1;i<data.length();i++) {
			if(data.charAt(i)=='(') { // '(' 만나면 
				stickCnt ++; //쇠막대기 개수 추가
			}else { // ')' 만나면
				if(data.charAt(i-1) == '(') {// 레이저라면
					stickCnt --; //직전에 '('가 막대기가 아니라서
					answer += stickCnt; // 현재 막대기만큼 answer에 추가
				}else { // 막대기가 끝나는거면
					answer ++; // answer에  1 추가
					stickCnt --; //막대기 개수 1 감소
				}
			}
		}
		return answer;
	}

}
