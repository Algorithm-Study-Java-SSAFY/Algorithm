package codeTest;

import java.util.*;
import java.io.*;



public class Main {
	
	static int N;
	static String exp;
	static int flag = 0; // 0이면 괄호 가능 / 1이면 바로 다음은 괄호 불가
	static List<Integer> result = new ArrayList<>(); //연산 결과 저장
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		exp = br.readLine();
		
		if(N>=3) {
			dfs(exp.substring(0, 3),3);
		}else {
			result.add(Integer.parseInt(String.valueOf(exp.charAt(0))));
		}
		
		Collections.sort(result);
		System.out.println(result.get(result.size()-1));
	}
	
	static void dfs(String subExp, int depth) {
		int i=0;
		char op = '.';
		int num1 = 0;
		int num2 = 0;
		//연산자와 위치 함께 찾기 op가 연산자 i가 위치
		for(i=1;i<subExp.length();i++) {
			if(!isInteger(subExp.charAt(i))) {
				op = subExp.charAt(i);
				break;
			}
		}
		//피연산자 구하기
		num1 = Integer.parseInt(subExp.substring(0, i));
		num2 = Integer.parseInt(subExp.substring(i+1, subExp.length()));
				
		//수식 끝나면
		if(depth==N) {
			result.add(cal(num1,op,num2));
			return;
		}
		
		
		String nextSubExp = "";
		
		
		//다음 연산부터 계산 (괄호 사용 o) ->  3+5가 들어오면 3+ 는 냅두고 (8-7)을 먼저 계산해서 다음 수식으로 3+1을 건네준다.
		if(flag==0) {
			nextSubExp = "";
			char fop = exp.charAt(depth);
			int fnum2 = Integer.parseInt(String.valueOf(exp.charAt(depth+1)));
			nextSubExp += String.valueOf(num1)+String.valueOf(op)+String.valueOf(cal(num2,fop,fnum2));
			flag = 1;
			dfs(nextSubExp,depth+2);
			flag = 0;
		}
		
		//현재 연산 계산 (괄호 x)   ->  3+5가 들어오면 우선 얘를 8로 계산하고 다음 수식으로 8-7을 건네준다.
		nextSubExp = "";
		nextSubExp += String.valueOf(cal(num1,op,num2))+exp.charAt(depth)+exp.charAt(depth+1);
		flag = 0;
		dfs(nextSubExp,depth+2);
		
	}
	
	//계산
	static int cal(int num1,char op, int num2) {
		if(op == '+') {
			return num1+num2;
		}else if(op == '-') {
			return num1-num2;
		}else {
			return num1*num2;
		}
	}
	
	
	// 숫자인지 확인
	static boolean isInteger(char c) {
		try {
			Integer.parseInt(String.valueOf(c));
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
}