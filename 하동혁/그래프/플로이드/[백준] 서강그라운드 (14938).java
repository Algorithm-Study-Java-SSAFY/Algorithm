import java.util.*;
import java.io.*;


public class Main {
    
	/*
	 * 각 지역은 양방향 통행이 가능 
	 * 낙하 지점을 포함해서 m 이내의 모든 지역에 도달 가능 한 경우를 탐색 
	 */
	
	static BufferedReader br;
    static int N,M,R; 
    static int[] score;
    static int[][] dist;
    static int[] result;
    
    public static void main(String[] args) throws IOException {
    	init();
    	Floyd(); 
    	
//    	for(int[] i : dist) {
//    		System.out.println(Arrays.toString(i));
//    	}System.out.println();
    	
    	resultCalculation(); 
    }
    
    static void resultCalculation() {
    	for(int i=1; i<=N; i++) {
    		for(int j=1; j<=N; j++) {
    			if(dist[i][j] <= M) { // 수색 범위 이내에 있다면 점수 합산
    				result[i] += score[j];
    			}
    		}
    	}
    	
    	Arrays.sort(result);
    	System.out.println(result[N]);
    }
    
    static void Floyd() {
    	
    	for(int i=1; i<=N; i++) { // 거쳐가는 지점
    		for(int j=1; j<=N; j++) { // 시작 지점 
    			
    			// 출발 -> 거쳐가는 지점에 연결 정보가 없는 경우 
    			if(dist[j][i] == Integer.MAX_VALUE) continue; 
    			
    			for(int k=1; k<=N; k++) { // 도착 지점
    				
    				// 거쳐가는 지점 -> 도착 지점 연결 정보가 없는 경우 
    				if(dist[i][k] == Integer.MAX_VALUE) continue;
    				
    				// 기존의 (출발 -> 도착) 보다 새로운 (출발 -> 거쳐 + 거쳐 + 도착)의 거리가 더 짧으면 갱신 
    				if(dist[j][k] > dist[j][i] + dist[i][k]) {
    					dist[j][k] = dist[j][i] + dist[i][k];
    				}
    				
    			}
    			
    		}
    	}
    }
    

    
    static void init() throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        R = Integer.parseInt(s[2]);
        
        score = new int[N+1]; 
        dist = new int[N+1][N+1]; 
        result = new int[N+1];
        
        
        String[] s2 = br.readLine().split(" ");
        for(int i=1; i<=N; i++) {
        	score[i] = Integer.parseInt(s2[i-1]);
        }
        
        for(int i=1; i<=N; i++) {
        	for(int j=1; j<=N; j++) {
        		dist[i][j] = Integer.MAX_VALUE;
        		if(i==j) dist[i][j] = 0; // 자기 자신의 거리는 0 
        	}
        }
        	

        
        for(int i=0; i<R; i++) {
        	String[] s3 = br.readLine().split(" ");
        	int n1 = Integer.parseInt(s3[0]);
        	int n2 = Integer.parseInt(s3[1]);
        	int dis = Integer.parseInt(s3[2]);
        	if(dist[n1][n2] > dis) dist[n1][n2] = dis;
        	if(dist[n2][n1] > dis) dist[n2][n1] = dis; 
         }
        
    }
    
    

    

        
}