import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static HashMap<Integer, List<Integer>> graph = new HashMap<>();
	static List<Integer> order = new ArrayList<>();
	static String answer = "YES";
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					graph.put(i, graph.getOrDefault(i, new ArrayList<>()));
					graph.get(i).add(j);
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			order.add(Integer.parseInt(st.nextToken()));
		}
		
		for (int i = 0; i < order.size() -1; i++) {
			int start = order.get(i);
			int next = order.get(i+1);
			if (!checkWay(start, next)) {
				answer = "NO";
				break;
			}
		}
		
		bw.write(answer);
		
		br.close();
		bw.close();
	}
    
    public static boolean checkWay(int start, int next) {
    	boolean[] visited = new boolean[N+1];
    	Queue<Integer> q = new LinkedList<>();
    	q.add(start);
    	visited[start] = true;
    	
    	while (!q.isEmpty()) {
    		int cur = q.poll();
    		if (cur == next) {
    			return true;
    		}
    		List<Integer> list = graph.getOrDefault(cur, new ArrayList<>());
    		
    		for (int i : list) {
    			if (visited[i] == false) {
    				q.add(i);
    				visited[i] = true;
    			}
    		}
    	}
    	
    	return false;
    }
}
