import java.util.*;
import java.io.*;


public class Main {
	
	static int N,M,MAX,ANSWER;
	static PriorityQueue<Integer> PLUS_PQ,MINUS_PQ;
	
	public static void main(String[] args) throws Exception {
		
		init();
		
		solution();
		
		System.out.println(ANSWER-MAX);
	}
	
	static void solution() {
		
		while(!PLUS_PQ.isEmpty()) {
			int distance = PLUS_PQ.peek();
			
			for(int i=0;i<M;i++) {
				if(!PLUS_PQ.isEmpty()) PLUS_PQ.poll();
			}
			
			ANSWER += distance*2;
			
		}
		
		while(!MINUS_PQ.isEmpty()) {
			int distance = Math.abs(MINUS_PQ.peek());
			
			for(int i=0;i<M;i++) {
				if(!MINUS_PQ.isEmpty()) MINUS_PQ.poll();
			}
			
			ANSWER += distance*2;
			
		}
	}
	
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		
		PLUS_PQ = new PriorityQueue<>((o1,o2)-> {
			return o2-o1;
		});
		
		MINUS_PQ = new PriorityQueue<>((o1,o2)-> {
			return o1-o2;
		});
		
		String[] input = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			int num = Integer.parseInt(input[i]);
			if(num>0) PLUS_PQ.add(num);
			else MINUS_PQ.add(num);
		}
		
		if(!PLUS_PQ.isEmpty() && !MINUS_PQ.isEmpty()) MAX = Math.max(Math.abs(MINUS_PQ.peek()), PLUS_PQ.peek());
		else if(PLUS_PQ.isEmpty()) MAX = Math.abs(MINUS_PQ.peek());
		else if(MINUS_PQ.isEmpty()) MAX = PLUS_PQ.peek();
		
		ANSWER = 0;
	}
}