import java.util.*;
import java.io.*;



class Node{
	int h;
	int num;
	//R이 홀수일 때 꺼낼 큐
	Queue<Integer> leftQueue; //말단 애들은 이것만 사용
	//R이 짝수일 때 꺼낼 큐
	Queue<Integer> rightQueue;
	
	Node(int h, int num){
		this.h = h;
		this.num = num;
		leftQueue = new LinkedList<>();
		rightQueue = new LinkedList<>();
	}
}

public class Main {
	
	static int H,K,R,TOTAL,DAY,ANSWER;
	static HashMap<Integer, Node> MAP;
	
	public static void main(String[] args) throws Exception {
		
		
		init();
		solution();
		
	}
	
	static void solution() {
		DAY = 1;
		ANSWER = 0;
		while(DAY<=R) {
			if(DAY%2==0) {
				evenWork();
			}else {
				oddWork();
			}
			DAY++;
		}
		
		System.out.println(ANSWER);
		
	}
	
	
	static void evenWork() {
		for(int i=1;i<TOTAL;i++) {
			Node node = MAP.get(i);
			if(!node.rightQueue.isEmpty()) {
				int workNum = node.rightQueue.poll();
				if(i==1) ANSWER += workNum;
				else {
					if(i%2==0) MAP.get(i/2).leftQueue.add(workNum);
					else MAP.get(i/2).rightQueue.add(workNum);
				}
			}
		}
	}
	
	static void oddWork() {
		for(int i=1;i<TOTAL;i++) {
			Node node = MAP.get(i);
			if(!node.leftQueue.isEmpty()) {
				int workNum = node.leftQueue.poll();
				if(i==1) ANSWER += workNum;
				else {
					if(i%2==0) MAP.get(i/2).leftQueue.add(workNum);
					else MAP.get(i/2).rightQueue.add(workNum);
				}
			}
		}
	}
	
	static void initMap() {
		MAP = new HashMap<>();
		TOTAL = (int) Math.pow(2,H+1);
		for(int i=1;i<TOTAL;i++) {
//			System.out.println(i+"-->"+(H-log2(i)));
			MAP.put(i, new Node(H-log2(i),i));
		}
	}
	
	public static int log2(int x) {
        return (int)(Math.log(x) / Math.log(2));
    }
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] HKR = br.readLine().split(" ");
		H = Integer.parseInt(HKR[0]);
		K = Integer.parseInt(HKR[1]);
		R = Integer.parseInt(HKR[2]);
		
		initMap();
		
		
		for(int i=TOTAL/2;i<TOTAL;i++) {
			String[] input = br.readLine().split(" ");
			for(int j=0;j<K;j++) {
				MAP.get(i).leftQueue.add(Integer.parseInt(input[j]));
			}
		}
		
	}
}