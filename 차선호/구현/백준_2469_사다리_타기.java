import java.util.*;
import java.io.*;



public class Main {
	
	
	static int K, N;
	static char[] START, RESULT;
	static char[][] MAP;
	static int UNKNOWN;
	static String answer;
	
	public static void main(String[] args) throws Exception{
		init();
		solutionTop();
		solutionBottom();
//		System.out.println(Arrays.toString(START));
//		System.out.println(Arrays.toString(RESULT));
		compare();
		System.out.println(answer);
	}
	
	static void compare() {
		for(int i=0;i<K-1;i++) {
			if(START[i]==RESULT[i]) {
				answer += '*';
				continue;
			}
			else {
				if(START[i+1]==RESULT[i] && START[i]==RESULT[i+1]) {
					answer += '-';
					if(i!=K-2) answer += '*';
					i++;
					continue;
				}else {
					answer = "";
					for(int j=0;j<K-1;j++) {
						answer += 'x';
					}
					return;
				}
			}
		}
	}
	
	
	static void solutionTop() {
		int idx = 0;
		while(idx<UNKNOWN) {
			char[] data = MAP[idx];
			for(int i=0;i<K-1;i++) {
				if(data[i]=='-') swap(START, i);
			}
			idx++;
		}
	}
	
	static void solutionBottom() {
		int idx = N-1;
		while(idx>UNKNOWN) {
			char[] data = MAP[idx];
			for(int i=0;i<K-1;i++) {
				if(data[i]=='-') swap(RESULT, i);
			}
			idx--;
		}
	}
	
	static void swap(char[] arr, int idx) {
		char c1 = arr[idx];
		arr[idx] = arr[idx+1];
		arr[idx+1] = c1;
	}
	
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		RESULT = br.readLine().toCharArray();
		START = new char[K];
		for(int i=0;i<K;i++) {
			START[i] =  (char)(i+65);
		}
		MAP = new char[N][K-1];
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<K-1;j++) {
				MAP[i][j] = input.charAt(j);
				if(MAP[i][j] == '?') UNKNOWN = i;
			}
		}
		answer = "";
	}
}