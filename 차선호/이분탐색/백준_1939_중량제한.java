import java.util.*;
import java.io.*;



public class Main{
	
	static int N,M,F1,F2;
	static HashMap<Integer,List<int[]>> GRAPH;
	static boolean[] VISITED;
	static int ANSWER;
	
	
	public static void main(String[] args) throws Exception {
		init();
		
		binarySearch();
		
		System.out.println(ANSWER);
	}
	
	static void binarySearch() {
		int start = 0;
		int end = 1_000_000_000;
		
		while(start<=end) {
			int weight = (start+end)/2;
//			System.out.println(weight);
			if(BFS(weight)) {
//				System.out.println("true");
				start = weight+1;
				ANSWER = Math.max(ANSWER , weight);
			}else {
//				System.out.println("false");
				end = weight-1;
			}
		}
		
	}
	
	static boolean BFS(int weight) {
		
		Arrays.fill(VISITED, false);
		Queue<Integer> queue = new LinkedList<>();
		queue.add(F1);
		VISITED[F1] = true;
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
//			System.out.println(node);
			if(node==F2) return true;
			for(int[] dest:GRAPH.get(node)) {
				if(!VISITED[dest[0]] && dest[1]>=weight) {
					VISITED[dest[0]] = true;
					queue.add(dest[0]);
				}
			}
		}
		
		return false;
	}
	
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		
		GRAPH = new HashMap<>();
		for(int i=1;i<N+1;i++) {
			GRAPH.put(i, new ArrayList<>());
		}
		
		for(int i=0;i<M;i++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int c = Integer.parseInt(input[2]);
			GRAPH.get(a).add(new int[] {b,c});
			GRAPH.get(b).add(new int[] {a,c});
		}
		
		String[] F = br.readLine().split(" ");
		F1 = Integer.parseInt(F[0]);
		F2 = Integer.parseInt(F[1]);
		
		VISITED = new boolean[N+1];
		ANSWER = 0;
	}
	
}