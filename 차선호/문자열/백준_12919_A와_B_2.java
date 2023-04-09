import java.util.*;
import java.io.*;


public class Main {
	
	static String S, T;
	static HashSet<String> set;
	static int len, answer, acnt, bcnt;
	
	
	public static void main(String[] args) throws Exception{
		init();
		backtracking(T);
		System.out.println(answer);
	}
	
	static void backtracking(String s) {
		
//		System.out.println(s);
		
		if(s.length()==len) {
			if(s.equals(S)) answer = 1;
			return;
		}
		
		if(s.charAt(s.length()-1)=='B') {
			if(s.charAt(0)=='A') return;
			backtracking(reverseStr(s.substring(1)));
		}else {
			backtracking(s.substring(0,s.length()-1));
			if(s.charAt(0)=='B') backtracking(reverseStr(s.substring(1)));
		}
		
		
	}
	
	static String reverseStr(String s) {
		String result = "";
		for(int i=s.length()-1;i>-1;i--) {
			result += s.charAt(i);
		}
		return result;
	}
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		T = br.readLine();
		set = new HashSet<>();
		len = S.length();
		answer = 0;
	}
}