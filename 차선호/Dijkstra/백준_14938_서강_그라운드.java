import java.util.*;
import java.io.*;


public class Main {
	
	static int N,M,R;
	static HashMap<Integer, List<int []>> GRAPH;
	static int[] ITEM;
	static int ANSWER;
	
	public static void main(String[] args) throws Exception{
		init();
		for(int i=1;i<N+1;i++) {
			BFS(i);
		}
		System.out.println(ANSWER);
	}
	
	static void BFS(int start) {
		PriorityQueue<int[]> needVisit = new PriorityQueue<>((o1,o2)-> {
			return o1[1]-o2[1];
		});
		int[] distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		needVisit.add(new int[] {start,0});
		int item = ITEM[start];
		while(!needVisit.isEmpty()) {
			int[] data = needVisit.poll();
			for(int[] dest:GRAPH.get(data[0])) {
				if(data[1]+dest[1]<=M && data[1]+dest[1]<distance[dest[0]]) {
					needVisit.add(new int[] {dest[0],data[1]+dest[1]});
					if(distance[dest[0]]==Integer.MAX_VALUE) item+= ITEM[dest[0]];
					distance[dest[0]] = data[1]+dest[1];
				}
			}
		}
		ANSWER = Math.max(ANSWER, item);
	}
	
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NMR = br.readLine().split(" ");
		N = Integer.parseInt(NMR[0]);
		M = Integer.parseInt(NMR[1]);
		R = Integer.parseInt(NMR[2]);
		ITEM = new int[N+1];
		String[] itemInput = br.readLine().split(" ");
		GRAPH = new HashMap<>();
		for(int i=0;i<N;i++) {
			ITEM[i+1] = Integer.parseInt(itemInput[i]);
			GRAPH.put(i+1, new ArrayList<>());
		}
		for(int i=0;i<R;i++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int cost = Integer.parseInt(input[2]);
			GRAPH.get(a).add(new int[] {b,cost});
			GRAPH.get(b).add(new int[] {a,cost});
		}
		ANSWER = 0;
	}
}