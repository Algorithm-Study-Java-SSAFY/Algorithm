import java.util.*;
import java.io.*;



public class Main{
	
	static int N;
	static PriorityQueue<Integer> LEFT_PQ;
	static PriorityQueue<Integer> RIGHT_PQ;
	static StringBuffer sb;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(sb);
	}
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		LEFT_PQ = new PriorityQueue<>((o1,o2)-> {
			return o2-o1;
		});
		
		RIGHT_PQ = new PriorityQueue<>((o1,o2)-> {
			return o1-o2;
		});
		sb = new StringBuffer();
		
		int mid = Integer.parseInt(br.readLine());
		sb.append(mid+"\n");
		for(int i=1;i<N;i++) {
			int num = Integer.parseInt(br.readLine());
//			System.out.println(LEFT_PQ.peek() +"//"+mid+"//"+RIGHT_PQ.peek()+"--->"+num);
			if(LEFT_PQ.size()==RIGHT_PQ.size()) {
				if(num<=mid) {
					LEFT_PQ.add(num);
					RIGHT_PQ.add(mid);
					mid = LEFT_PQ.poll();
				}else {
					RIGHT_PQ.add(num);
				}
			}else {
				if(num<=mid) {
					LEFT_PQ.add(num);
				}else {
					LEFT_PQ.add(mid);
					RIGHT_PQ.add(num);
					mid = RIGHT_PQ.poll();
				}
			}
			
			sb.append(mid+"\n");
		}
	}
	
}