import java.util.*;
import java.io.*;


public class Main {
	
	static int N, M;
	static HashMap<Integer, List<int[]>> GRAPH;
	static StringBuffer sb;
	
	public static void main(String[] args) throws Exception{
		init();
		for(int i=1;i<=N;i++) {
//			System.out.println("<---"+i+"--->");
			BFS(i);
//			System.out.println();
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb);
	}
	
	static void BFS(int n) {
		Queue<int[]> needVisit = new LinkedList<>();
		needVisit.add(new int[] {n,-1});
		needVisit.add(new int[] {n,1});
		boolean[] visited = new boolean[N+1];
		visited[n] = true;
		int cnt = N-1;
		while(!needVisit.isEmpty()){
			int[] data = needVisit.poll();
//			System.out.println(Arrays.toString(data));
			int start = data[0];
			int compare = data[1];
			for(int[] dest:GRAPH.get(start)) {
				if(visited[dest[0]]) continue;
				if(compare != dest[1]) continue;
				visited[dest[0]] = true;
				needVisit.add(dest);
				cnt--;
			}
		}
		sb.append(cnt+"\n");
	}
	
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		GRAPH = new HashMap<>();
		for(int i=1;i<=N;i++) {
			GRAPH.put(i, new ArrayList<>());
		}
		for(int i=0;i<M;i++) {
			String[] input = br.readLine().split(" ");
			int big = Integer.parseInt(input[0]);
			int small = Integer.parseInt(input[1]);
			GRAPH.get(big).add(new int[] {small,-1});
			GRAPH.get(small).add(new int[] {big,1});
		}
		sb = new StringBuffer();
	}
}