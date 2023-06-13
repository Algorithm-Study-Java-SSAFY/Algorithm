import java.util.*;
import java.io.*;



public class Main{
	
	static BufferedReader br;
	static int N;
	static int[] HOUSE, FESTIVAL;
	static List<int[]> MARTS;
	static HashMap<Integer, int[]> MAP;
	static HashMap<Integer, List<Integer>> GRAPH; //0번 집, 1~N번 편의점, N+1번 festival
	
	
	
	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int i=0;i<t;i++) {
			init();
			
			makeGraph();
			
			bfs();
		}
		
	}
	
	static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+2];
		queue.add(0);
		visited[0] = true;
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			
			for(int dest:GRAPH.get(node)) {
				if(dest==N+1) {
					System.out.println("happy");
					return;
				}
				if(!visited[dest]) {
					queue.add(dest);
					visited[dest] = true;
				}
			}
		}
		System.out.println("sad");
	}
	
	static void makeGraph() {
		//먼저 house랑 나머지 다
		for(int i=1;i<N+1;i++) {
			int[] mart = MAP.get(i);
			if(check(HOUSE,mart)) GRAPH.get(0).add(i);
		}
		if(check(HOUSE,FESTIVAL)) GRAPH.get(0).add(N+1);
		
		
		//편의점이랑 나머지 다
		for(int i=1;i<N+1;i++) {
			int[] currentMart = MAP.get(i);
			if(check(currentMart,HOUSE)) GRAPH.get(i).add(0);
			for(int j=1;j<N+1;j++) {
				if(i!=j) {
					if(check(currentMart,MAP.get(j))) GRAPH.get(i).add(j);
				}
			}
			if(check(currentMart,FESTIVAL)) GRAPH.get(i).add(N+1);
		}
		
		
		//festival이랑 나머지 다
		if(check(HOUSE,FESTIVAL)) GRAPH.get(N+1).add(0);
		for(int i=1;i<N+1;i++) {
			int[] mart = MAP.get(i);
			if(check(FESTIVAL,mart)) GRAPH.get(N+1).add(i);
		}
		
	}
	
	static boolean check(int[] start, int[] dest) {//거리가 1000이하인지
		int distance = Math.abs(start[0]-dest[0])+Math.abs(start[1]-dest[1]);
		if(distance<=1000) return true;
		else return false;
	}
	
	static void init() throws Exception{
		N = Integer.parseInt(br.readLine());
		HOUSE = new int[2];
		
		String[] house = br.readLine().split(" ");
		HOUSE[0] = Integer.parseInt(house[0]);
		HOUSE[1] = Integer.parseInt(house[1]);
		
		MARTS = new ArrayList<>();
		MAP = new HashMap<>();
		for(int i=1;i<N+1;i++) {
			String[] input = br.readLine().split(" ");
			MARTS.add(new int[] {Integer.parseInt(input[0]),Integer.parseInt(input[1])});
			MAP.put(i, new int[] {Integer.parseInt(input[0]),Integer.parseInt(input[1])});
		}
		
		FESTIVAL = new int[2];
		String[] festival = br.readLine().split(" ");
		FESTIVAL[0] = Integer.parseInt(festival[0]);
		FESTIVAL[1] = Integer.parseInt(festival[1]);
		
		GRAPH = new HashMap<>();
		for(int i=0;i<N+2;i++) {
			GRAPH.put(i, new ArrayList<>());
		}
	}
	
}