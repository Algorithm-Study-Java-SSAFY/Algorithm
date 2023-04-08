import java.io.*;
import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Main {

	/*
	 * +1, -1 이동은 1초
	 * *2 이동은 0초 
	 */
    static BufferedReader br; 
    static int N, K; // 수빈이 위치, 동생 위치   
    static int[] minTime;
    static int answer; 
    
    static class Node{
    	int idx;
    	int time; 
    	
    	Node(int idx, int time){
    		this.idx = idx;
    		this.time = time; 
    	}
    }
    
    public static void main(String[] args) throws IOException {
        init();
        Dijkstra();
        System.out.println(answer);

    }

    static void Dijkstra() {
    	PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> {
    		return o1.time-o2.time;
    	});
    	
    	pq.add(new Node(N,0)); // 시작노드, 시간 
    	
    	while(!pq.isEmpty()) {
    		
    		Node outNode = pq.poll();
    		int curNode = outNode.idx;
    		int curTime = outNode.time;
    		
    		if(minTime[curNode] < curTime) {
    			continue;
    		}
    		
    		// 동생에게 도착
    		if(curNode == K) {
    			answer = curTime;
    			return; 
    		}
    		
    		// 인접노드 확인 *2, +1, -1
    		for(int i=0; i<3; i++) {
    				int nextNode = -1;
    				int nextTime = -1; 
    			if(i==0 && curNode*2 <= 100000) {
    				nextNode = curNode*2;
    				nextTime = 0;
    			}else if(i==1 && curNode+1 <= 100000) {
    				nextNode = curNode+1;
    				nextTime = 1;
    			}else if(i==2 && 0 <= curNode-1){
    				nextNode = curNode-1;
    				nextTime = 1;
    			}

    			if(nextNode != -1 && minTime[nextNode] > curTime + nextTime) {
    				minTime[nextNode] = curTime + nextTime;
    				pq.add(new Node(nextNode, minTime[nextNode]));
    			}
    		}
    		
    	}

    }

        
    
    
    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        K = Integer.parseInt(s[1]);
        
        minTime = new int[100001];
        for(int i=0; i<minTime.length; i++) {
        	minTime[i] = Integer.MAX_VALUE;
        }
    }

}