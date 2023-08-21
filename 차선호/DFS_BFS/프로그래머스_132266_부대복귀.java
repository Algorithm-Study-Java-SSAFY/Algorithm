import java.io.*;
import java.util.*;

class Solution {
    static int N, DESTINATION;
	static int[][] ROADS;
	static int[] SOURCES, ANSWER;
	static HashMap<Integer, List<Integer>> MAP;
	static int[] DISTANCE;
    
    static void solution() {
		
		makeMAP();
		
		makeDISTANCE();
		
		makeANSWER();
	}
	
	static void makeANSWER() {
		ANSWER = new int[SOURCES.length];
		for(int i=0;i<SOURCES.length;i++) {
			ANSWER[i] = DISTANCE[SOURCES[i]];
		}
	}
	
	static void makeDISTANCE() {
		DISTANCE = new int[N+1];
		Arrays.fill(DISTANCE, -1);
		
		DISTANCE[DESTINATION] = 0;
		Queue<int[]> queue = new LinkedList<>();
		for(int dest: MAP.get(DESTINATION)) {
			queue.add(new int[] {dest,1});
			DISTANCE[dest] = 1;
		}
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			
			for(int dest: MAP.get(current[0])) {
				if(DISTANCE[dest]==-1) {
					DISTANCE[dest] = current[1]+1;
					queue.add(new int[] {dest,current[1]+1});
				}
			}
		}
	}
	
	static void makeMAP() {
		MAP = new HashMap<>();
		for(int i=1;i<=N;i++) {
			MAP.put(i, new ArrayList<>());
		}
		
		for(int[] road: ROADS) {
			int v1 = road[0];
			int v2 = road[1];
			MAP.get(v1).add(v2);
			MAP.get(v2).add(v1);
		}
	}
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = {};
		
		N = n;
		ROADS = roads;
		SOURCES = sources;
		DESTINATION = destination;
        
		solution();
		
		answer = ANSWER;
		return answer;
    }
}