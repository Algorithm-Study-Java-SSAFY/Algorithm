import java.util.*;
import java.io.*;



public class Main {
	
	static int N,M;
	static int answer;
	static List<Integer> trueList;
	static HashSet<Integer> SET;
	static HashMap<Integer, List<Integer>> GRAPH;
	static HashMap<Integer, List<Integer>> PMAP;
	
	public static void main(String[] args) throws Exception{
		init();
		if(!trueList.isEmpty()) search();
//		for(int key:GRAPH.keySet()) {
//			System.out.println(key+"-->"+GRAPH.get(key));
//		}
//		for(int a:SET)System.out.println(a);
		getAnswer();
		System.out.println(answer);
	}
	
	static void getAnswer() {
		Loop:
			for(int i=1;i<M+1;i++) {
				for(int data:PMAP.get(i)) {
					if(SET.contains(data)) continue Loop;
				}
				answer++;
			}
	}
	
	static void search() {
		Queue<Integer> needVisit = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		needVisit.add(trueList.get(0));
		visited[trueList.get(0)] = true;
		SET.add(trueList.get(0));
		while(!needVisit.isEmpty()) {
			int data = needVisit.poll();
			for(int dest:GRAPH.get(data)) {
				if(!visited[dest]) {
					needVisit.add(dest);
					visited[dest] = true;
					SET.add(dest);
				}
			}
		}
	}
	
	static void makeGraph(List<Integer> list) {
		for(int i=0;i<list.size();i++) {
			for(int j=0;j<list.size();j++) {
				if(i!=j) GRAPH.get(list.get(i)).add(list.get(j));
			}
		}
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
		String[] tinput = br.readLine().split(" ");
		trueList = new ArrayList<>();
		for(int i=0;i<Integer.parseInt(tinput[0]);i++) {
			trueList.add(Integer.parseInt(tinput[i+1]));
		}
		makeGraph(trueList);
		PMAP = new HashMap<>();
		for(int i=1;i<M+1;i++) {
			PMAP.put(i, new ArrayList<>());
			List<Integer> partyList = new ArrayList<>();
			String[] input = br.readLine().split(" ");
			for(int j=0;j<Integer.parseInt(input[0]);j++) {
				partyList.add(Integer.parseInt(input[j+1]));
				PMAP.get(i).add(Integer.parseInt(input[j+1]));
			}
			makeGraph(partyList);
		}
		SET = new HashSet<>();
		answer = 0;
	}
}