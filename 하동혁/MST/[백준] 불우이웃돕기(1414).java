import java.util.*; 
import java.io.*;

/*
 *  각 컴퓨터를 모두 연결하려고 함. (만약 모두 연결할 수 없는 경우 -1을 출력)
 */

public class Main {
    static BufferedReader br;
    static int N; 
    static int lenSum; 
    static HashMap<Character, Integer> lenMap; // 비용 저장 
    static int[] parent; 
    static PriorityQueue<Edge> EdgePQ; 
    static int[][] visit; // 1-2, 2-1과 두 가지 경우를 구분하기 위함
    
    static class Edge{
    	int n1;
    	int n2;
    	int cost;
    	
    	Edge(int n1, int n2, int cost){
    		this.n1 = n1; 
    		this.n2 = n2;
    		this.cost = cost; 
    	}
    }
    
    public static void main(String[] args) throws IOException {
    	init();
    	
    	if(N==1) {
            if(N==1 && EdgePQ.size()!=0) {
            	System.out.println(lenSum);
            }else {
            	System.out.println(0);
            }
    	}else {
            MST();
//            for(int[] i : visit) {
//            	System.out.println(Arrays.toString(i));
//            }
            
            boolean tf = true;
            for(int i=0; i<N; i++) {
            	if(find(i) != 0) {
            		System.out.println(-1);
            		tf = false; 
            		break;
            	}	
            }
            
            if(tf) {
            	System.out.println(lenSum);
            }
    	}
    }
    
    static void MST() {
    	while(!EdgePQ.isEmpty()) {
    		Edge outEdge = EdgePQ.poll();
    		
    		// 현재 자신이 연결된 루트 노드가 서로 다르다면 연결한다. 
    		if(find(outEdge.n1) != find(outEdge.n2) && visitCheck(outEdge.n1,outEdge.n2)) {  
    			Union(outEdge.n1, outEdge.n2);
    			
    			lenSum -= outEdge.cost;
    			visit[outEdge.n1][outEdge.n2] = 1; 
    			visit[outEdge.n2][outEdge.n1] = 1;
    		}
    	}
    }
    
    static void Union(int n1, int n2) {
    	int p1 = parent[n1];
    	int p2 = parent[n2];
    	
    	if(p1 < p2) parent[p2] = p1;
    	else parent[p1] = p2;	
    }
    
    
    static int find(int n) {
    	if(parent[n] == n) return n; 
    	
    	return parent[n] = find(parent[n]);
    }
    
    
    static boolean visitCheck(int n1, int n2) {
    	if(visit[n1][n2] == 0 && visit[n2][n1]==0) {
    		return true;
    	}
    	
    	return false;
    }
    
    

    
    static void init() throws NumberFormatException, IOException {
    	
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        lenMap = new HashMap<>(); 
        EdgePQ = new PriorityQueue<>((o1,o2)->{
        	return o1.cost - o2.cost;
        }); 
        
    	lenSum = 0; 
    	parent = new int[N];
    	visit = new int[N][N];
    	
    	makeLenMap();
//        System.out.println(lenMap);
    	
        for(int i=0; i<N; i++) {
        	parent[i] = i;
        	String s = br.readLine();
           
            for(int j=0; j<s.length(); j++) {
            	char c = s.charAt(j);
            	
            	if(c != '0') {
                	lenSum += lenMap.get(c);
                	EdgePQ.add(new Edge(i,j,lenMap.get(c)));            		
            	}
            }   	
        }
        
        
    }
    
    
    static void makeLenMap() {
        
        for(int i=1; i<=26; i++) {
            char c = (char)(96+i);
            lenMap.put(c,i);
        }
        
        for(int i=27; i<=52; i++) {
            char c = (char)(38+i);
            lenMap.put(c,i);
        }
        
    }
    

        
}