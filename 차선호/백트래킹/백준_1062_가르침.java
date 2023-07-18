import java.util.*;
import java.io.*;



public class Main {
	
	static int N,K,ANSWER;
	static List<String> WORDS;
	static HashSet<Character> SET;
	static boolean[] VISITED;
	
	
	public static void main(String[] args) throws Exception {
		
		init();
		
		if(K<5) {
			System.out.println(0);
			System.exit(0);
		}
		
		solution();
		
		System.out.println(ANSWER);
	}
	
	static void solution() {
		
		SET = new HashSet<>();
		SET.add('a');
		SET.add('n');
		SET.add('t');
		SET.add('i');
		SET.add('c');
		
		VISITED = new boolean[26];
		VISITED['a'-'a'] = true;
		VISITED['n'-'a'] = true;
		VISITED['t'-'a'] = true;
		VISITED['i'-'a'] = true;
		VISITED['c'-'a'] = true;
		
		dfs(0,5);
	}
	
	static void dfs(int cur, int k) {
		
		if(k==K) {
			ANSWER = Math.max(ANSWER, getAnswer());
			return;
		}
		
		for(int i=cur;i<26;i++) {
			char ch = (char)i;
			if(VISITED[i]) continue;
			VISITED[i] = true;
			dfs(i,k+1);
			VISITED[i] = false;
		}
	}
	
	static int getAnswer() {
		
		int result = 0;
		
		for(String str: WORDS) {
			if(check(str)) result++;
		}
		
		return result;
	}
	
	static boolean check(String str) {
		
		for(int i=0;i<str.length();i++) {
			if(!VISITED[(int)str.charAt(i)-'a']) return false;
		}
		
		return true;
	}
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NK = br.readLine().split(" ");
		N = Integer.parseInt(NK[0]);
		K = Integer.parseInt(NK[1]);
		
		WORDS = new ArrayList<>();
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			WORDS.add(str);
		}
	
		
		ANSWER = 0;
	}
}