import java.util.*;
import java.io.*;

public class Main {
	
	static char[] STR, BOOM;
	static Stack<Character> STACK;
	static int STR_LEN, BOOM_LEN;
	static List<Character> SUB_STR;
	static StringBuffer SB;
	
	public static void main(String[] args) throws Exception {
		
		init();
	
		solution();
		
		if(SB.length()==0) System.out.println("FRULA");
		else System.out.println(SB);
	}
	
	static void solution() {
		
		for(int i=0;i<STR_LEN;i++) {
			STACK.add(STR[i]);
			if(STACK.size()>=BOOM_LEN) {
				check();
			}
		}
		
		makeAnswer();
		
	}
	
	static void makeAnswer() {
		SB = new StringBuffer();
		int resultCnt = STACK.size();
		char[] result = new char[resultCnt];
		for(int i=resultCnt-1;i>-1;i--) result[i] = STACK.pop();
		for(int i=0;i<resultCnt;i++) SB.append(result[i]);
	}
	
	static void check() {
		SUB_STR = new ArrayList<>();
		char ch = '.';
		for(int i=BOOM_LEN-1;i>-1;i--) {
			ch = STACK.pop();
			if(ch!=BOOM[i]) break;
			SUB_STR.add(ch);
		}
		int cnt = SUB_STR.size(); //일치한 길이
		if(cnt<BOOM_LEN) { //boom과 모두 일치하지 않았으면 역순으로 다시 넣어준다.
			SUB_STR.add(ch); //마지막에 break 먹어서 추가 안되기 때문에
			for(int i=cnt;i>-1;i--) STACK.add(SUB_STR.get(i));
		}
	}
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		STR_LEN = str.length();
		STR = new char[STR_LEN];
		for(int i=0;i<STR_LEN;i++) STR[i] = str.charAt(i);
		String boom = br.readLine();
		BOOM_LEN = boom.length();
		BOOM = new char[BOOM_LEN];
		for(int i=0;i<BOOM_LEN;i++) BOOM[i] = boom.charAt(i);
		STACK = new Stack<>();
	}
}