import java.util.*;
import java.io.*;


public class Main {
	
	static int N, ANSWER;
	static PriorityQueue<int[]> RAMENS;
	static int[] PARENT;
	
	public static void main(String[] args) throws Exception {
		
		init();
		
		solution();
		
		System.out.println(ANSWER);
		
	}
	
	static void solution() {
		
		while(!RAMENS.isEmpty()) {
			int[] ramen = RAMENS.poll();
			int day = ramen[0];
			int cnt = ramen[1];
			
//			System.out.println(Arrays.toString(PARENT));
			if(PARENT[day]==day) {
//				System.out.println("day : "+day+"// cnt : "+cnt);
				ANSWER += cnt;
//				union(day,day-1);
				PARENT[day] = PARENT[day-1];
			}else {
				int parentDay = find(day);
				if(parentDay==0) continue;
				if(PARENT[parentDay]==parentDay) {
//					System.out.println("day : "+day+"// cnt : "+cnt);
					ANSWER += cnt;
//					union(parentDay, parentDay-1);
					PARENT[parentDay] = PARENT[parentDay-1];
				}
			}
		}
		
	}
	
	
	static void union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		if(p1<p2) PARENT[p2] = p1;
		else PARENT[p1] = p2;
	}
	
	static int find(int v) {
		if(PARENT[v]==v) return v;
		
		return PARENT[v] = find(PARENT[v]);
	}
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		PARENT = new int[N+1];
		for(int i=1;i<N+1;i++) PARENT[i] = i;
		
		
		RAMENS = new PriorityQueue<>((o1,o2)-> {
			if(o1[1]!=o2[1]) return o2[1]-o1[1];
			else return o1[0]-o2[0];
		});
		
		for(int i=0;i<N;i++) {
			String[] input = br.readLine().split(" ");
			RAMENS.add(new int[] {Integer.parseInt(input[0]),Integer.parseInt(input[1])});
		}
		
		ANSWER = 0;
	}
}