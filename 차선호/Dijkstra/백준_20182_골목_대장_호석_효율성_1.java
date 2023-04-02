import java.util.*;
import java.io.*;


public class Main {

	static int N,M,A,B,C;
	static HashMap<Integer, List<int[]>> graph;
	static PriorityQueue<int[]> pq;
	static int[] sumArr;
	static int[] costArr;
	static int answer;
	
	public static void main(String[] args) throws Exception{
		init();
		dijkstra();
		System.out.println(answer);
	}
	
	static void dijkstra() {
		for(int[] node:graph.get(A)) { //첫 출발 노드에 대한 정보 pq에 저장
			sumArr[node[0]] = node[1];
			costArr[node[0]] = node[1];
			if(node[0]==B) {
				answer = Math.min(answer, node[1]);
			}
			pq.add(new int[] {node[0],node[1],node[1]});
		}
		
		while(!pq.isEmpty()) {
			int[] node = pq.poll();
			int sum = node[1]; //현재까지 합
			int cost = node[2]; //현재 최대 수치심
			for(int[] dest:graph.get(node[0])) {
				if(sum+dest[1]>C) continue; //현재까지의 합이 C 넘으면 continue
				if(dest[1]<sumArr[dest[0]] || Math.max(cost, dest[1])<costArr[dest[0]]) { //현재까지의 합이 제일 최소거나 현재까지의 최대 수치심이 제일 최소이면 넣어라
					pq.add(new int[] {dest[0],sum+dest[1],Math.max(dest[1], cost)});
					if(dest[1]<sumArr[dest[0]]) sumArr[dest[0]] = dest[1];
					if(Math.max(cost, dest[1])<costArr[dest[0]]) costArr[dest[0]] = Math.max(cost, dest[1]);
				}
				if(dest[0]==B) {
					answer = Math.min(answer, Math.max(dest[1], cost));
				}
			}
		}
		if(answer == Integer.MAX_VALUE) answer = -1;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NMABC = br.readLine().split(" ");
		N = Integer.parseInt(NMABC[0]);
		M = Integer.parseInt(NMABC[1]);
		A = Integer.parseInt(NMABC[2]);
		B = Integer.parseInt(NMABC[3]);
		C = Integer.parseInt(NMABC[4]);
		graph = new HashMap<>();
		for(int i=1;i<N+1;i++) {
			graph.put(i, new ArrayList<>());
		}
		for(int i=0;i<M;i++) {
			String[] input = br.readLine().split(" ");
			int start = Integer.parseInt(input[0]);
			int dest = Integer.parseInt(input[1]);
			int cost = Integer.parseInt(input[2]);
			graph.get(start).add(new int[] {dest,cost});
			graph.get(dest).add(new int[] {start,cost});
		}
		pq = new PriorityQueue<>((o1,o2)-> { //최대 수치심이 제일 작은 놈이 맨 앞에
			return o1[2]-o2[2];
		});
		answer = Integer.MAX_VALUE;
		sumArr = new int[N+1]; //현재 노드까지의 최소 합 저장
		costArr = new int[N+1]; //현재 노드까지의 최소 최대 수치심 저장
		for(int i=1;i<N+1;i++) {
			sumArr[i] = Integer.MAX_VALUE;
			costArr[i] = Integer.MAX_VALUE;
		}
	}
}