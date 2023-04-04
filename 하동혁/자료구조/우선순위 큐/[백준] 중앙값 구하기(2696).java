import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br; 
    static StringBuilder sb; 

    static int T, N; 
    static PriorityQueue<Integer> leftPQ; 
    static PriorityQueue<Integer> rightPQ;
    static int center;
    
    static List<Integer> centerList; 
    static int centerCnt; 
    
    public static void main(String[] args) throws IOException {
        init();
    }
    
    static void calculation(int num1, int num2) {
    	int leftCnt = 0; 
    	int rightCnt = 0; 
    	
    	if(num1 < center) {
    		leftPQ.add(num1);
    		leftCnt++; 
    	}else if(num1 > center) {
    		rightPQ.add(num1);
    		rightCnt++;
    	}
    	
    	if(num2 < center) {
    		leftPQ.add(num2);
    		leftCnt++; 
    	}else if(num2 > center){
    		rightPQ.add(num2);
    		rightCnt++;
    	}
    	
    	if(leftCnt==1) { // left에 하나 들어가고 나머지 하나가 center와 똑같으면 그것은 right에 들어간다. 
        	if(num1 == center) {
        		rightPQ.add(num1);
        	}else if(num2 == center) {
        		rightPQ.add(num2);
        	}
    	}
    	
    	if(rightCnt==1) { // right에 하나 들어가고 나머지 하나가 center와 똑같으면 그것은 left에 들어간다. 
        	if(num1 == center) {
        		leftPQ.add(num1);
        	}else if(num2 == center) {
        		leftPQ.add(num2);
        	}
    	}
    	
    	
    	if(rightCnt==0 && leftCnt==0) { // num1, num2가 모두 center와 똑같을때 
    		leftPQ.add(num1);
    		rightPQ.add(num2);
    	}
    	
    	// 오른쪽에 2개가 들어갔으면 center를 왼쪽으로 넣어주고 오른쪽PQ 가장 작은 애를 빼서 center에 위치시킨다. 
    	// 왼쪼겡 2개가 들어갔으면 center를 오른쪽으로 넣어주고 왼쪽PQ의 가장 큰 애를 빼서 center에 위치  
    	if(rightCnt == 2) {
    		leftPQ.add(center);
    		center = rightPQ.poll();
    	}else if(leftCnt == 2) {
    		rightPQ.add(center);
    		center = leftPQ.poll();
    	}
    	
    	
    	centerList.add(center);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
        	sb = new StringBuilder();
        	
        	N = Integer.parseInt(br.readLine());
        	
        	leftPQ = new PriorityQueue<>(Collections.reverseOrder()); // 왼쪽은 가장 큰 부분이 움직임 
        	rightPQ = new PriorityQueue<>(); // 오른쪽은 가장 작은 부분이 움직임
        	centerList = new ArrayList<>(); 
        	
        	int loop = N/10; 
        	if(N%10 != 0) {
        		loop++;
        	}
        	
        	List<Integer> dataList = new ArrayList<>();
        	for(int i=0; i<loop; i++) {
        		String[] s = br.readLine().split(" ");
        		for(int j=0; j<s.length; j++) {
        			dataList.add(Integer.parseInt(s[j]));
        		}
        	}
        
        	centerList.add(dataList.get(0)); // 첫 center 바로 저장 
        	center = dataList.get(0);
        	for(int i=1; i<N; i=i+2) {
        		int num1 = dataList.get(i);
        		int num2 = dataList.get(i+1);
        		calculation(num1, num2);
        	}
        	
        	System.out.println(centerList.size());
        	int cnt=0; 
        	for(int i=0; i<centerList.size(); i++) {
        		sb.append(centerList.get(i) + " ");
        		cnt++;
        		if(cnt%10==0) {
        			sb.append("\n");	
        		}
        	}
        	System.out.println(sb);
        	
        }
           
    }
}