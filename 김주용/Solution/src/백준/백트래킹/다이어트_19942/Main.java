package 백준.백트래킹.다이어트_19942;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.stream.Stream;

class Info {
	int p;
	int f;
	int s;
	int v;
	int c;
	
	public Info(int p, int f, int s, int v, int c) {
		super();
		this.p = p;
		this.f = f;
		this.s = s;
		this.v = v;
		this.c = c;
	}

}


public class Main {
	
	static int N;
	static Info[] info;

	static Info minInfo;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		info = new Info[N+1];
		int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		minInfo = new Info(line[0], line[1], line[2], line[3], 0);
		
		for(int i = 1; i <= N; i++) {
			line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			info[i+1] = new Info(line[0], line[1], line[2], line[3], line[4]);
		}
	}
	

	
}
