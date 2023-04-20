import java.util.*; 
import java.io.*;

/*
 *  벽을 부수면 빈방으로 변함 
 * 	가로 M, 세로 N
 *  0 빈방, 1 벽 
 *  0,0 -> N-1, M-1 까지 가는데 파괴해야 하는 벽의 최소 개수 
 *  
 *  최소 경로가 아닌 벽을 최소로 부셔야 하는 경우이다. 
 */

public class Main {
    static BufferedReader br;
    static int N, M; 
    
    static int[][] board; 
    static int[][] visited;
    static int[] DY = {1,-1,0,0};
    static int[] DX = {0,0,1,-1}; 
    static int answer = 0; 
    
    public static void main(String[] args) throws IOException {
        init();
        find();
        System.out.println(answer);
    }
    
    
    static void find() {
    	// int[] = {y,x,벽파괴 스택}
    	PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) ->{
    		return o1[2]-o2[2];
    	});
    	
    	pq.add(new int[] {0,0,0});
    	
    	while(!pq.isEmpty()) {
    		int[] out = pq.poll();
    		
    		if(out[0] == N-1 && out[1] == M-1) {
    			answer = out[2];
    			return; 
    		}
    		
    		for(int i=0; i<4; i++) {
    			int dy = out[0] + DY[i];
    			int dx = out[1] + DX[i];
    			
    			if(0<=dy && dy<N && 0<=dx && dx<M) {
    				if(board[dy][dx] == 0 && visited[dy][dx] > out[2]) {
    					pq.add(new int[] {dy,dx,out[2]});
    					visited[dy][dx] = out[2];
    				}
    				else if(board[dy][dx] == 1 && visited[dy][dx] > out[2]+1){
    					pq.add(new int[] {dy,dx,out[2]+1});
    					visited[dy][dx] = out[2]+1;
    				}
    				
    			}
    		}
    	}
    	
    }
    

    
    static void init() throws NumberFormatException, IOException {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        M = Integer.parseInt(s[0]);
        N = Integer.parseInt(s[1]);
        
        board = new int[N][M];
        visited = new int[N][M];
        
        for(int i=0; i<N; i++) {
        	String[] s2 = br.readLine().split("");
        	for(int j=0; j<M; j++) {
        		board[i][j] = Integer.parseInt(s2[j]);
        		visited[i][j] = Integer.MAX_VALUE;
        	}
        }

    }
    
        
}