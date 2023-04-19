import java.util.*; 
import java.io.*;

public class Main {
    static BufferedReader br;

    // 목적지에서 다른 모든 노드이 최단거리 구함
    // 목적지를 제외한 다른 모든 노드 각각 X까지의 최단거리를 구해야 함 
    
    static int N, M, X; // X는 목적지  
    static int[] minDist;
    static int[] sumDist; 
    static int[] XDist; 
    static List<List<Node>> nodeList = new ArrayList<>();  
    
    static class Node{
    	int to; 
    	int cost; 
    	Node(int to, int cost){
    		this.to = to;
    		this.cost = cost; 
    	}
    }
    
    public static void main(String[] args) throws IOException {
        init();
        
        for(int i=1; i<=N; i++) {
        	Dijkstra(i);
        }
        
//        System.out.println(Arrays.toString(sumDist));
//        System.out.println(Arrays.toString(XDist));
        
        int answer = 0; 
        for(int i=1; i<=N; i++) {
        	if(answer < sumDist[i] + XDist[i]) {
        		answer = sumDist[i] + XDist[i];
        	}
        }
        
        System.out.println(answer);
    }

    
    static void Dijkstra(int start) {
    	int[] minDist = new int[N+1];
    	Arrays.fill(minDist, Integer.MAX_VALUE);
    	minDist[start] = 0; 
    	
    	PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->{
    		return o1.cost-o2.cost;
    	});
    	
    	pq.add(new Node(start,0));
    	
    	while(!pq.isEmpty()) {
    		Node curNode = pq.poll(); 
    		int curIdx = curNode.to; 
    		int curCost = curNode.cost; 
    		
    		if(minDist[curIdx] < curCost) continue; 
    		
    		if(start != X && curIdx == X) { // 시작노드가 X라면 X부터 다른 모든노드까지의 최단 거리를 알아야 하기 때문 
    			sumDist[start] = minDist[curIdx]; // 시작에서 X까지의 거리 
    			return; 
    		}
    		
        	for(int i=0; i<nodeList.get(curIdx).size(); i++) {
        		Node nxtNode = nodeList.get(curIdx).get(i);
        		int nxtIdx = nxtNode.to;
        		int nxtCost = nxtNode.cost;
        		
        		if(minDist[nxtIdx] > curCost + nxtCost) {
        			minDist[nxtIdx] = curCost + nxtCost;
        			pq.add(new Node(nxtIdx, minDist[nxtIdx]));
        		}
        		
        	}
    	}

    	if(start == X) {
    		XDist = minDist;
    	}
    	
    }
    

    
    static void init() throws NumberFormatException, IOException {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        X = Integer.parseInt(s[2]);
        
        sumDist = new int[N+1];
        XDist = new int[N+1];
        
        for(int i=0; i<=N; i++) {
        	nodeList.add(new ArrayList<>());
        }
        
        for(int i=0; i<M; i++) {
        	String[] s2 = br.readLine().split(" ");
        	int from = Integer.parseInt(s2[0]);
        	int to = Integer.parseInt(s2[1]);
        	int co = Integer.parseInt(s2[2]);
        	
        	nodeList.get(from).add(new Node(to, co));
        }
        
    }

    
}