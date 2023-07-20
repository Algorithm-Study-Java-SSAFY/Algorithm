import java.util.*;
import java.io.*;



public class Main {
	
	static int S, ANSWER;
	static int[][] CNT;
	
	
	public static void main(String[] args) throws Exception {
		
		init();
		
		solution();
		
		System.out.println(ANSWER);
	}
	
	static void solution() {
		
		dijkstra();
	}
	
	static void dijkstra() {
		CNT = new int[3001][3001]; // [화면에 이모티콘 개수][클립보드에 이모티콘 개수]
		for(int i=0;i<3001;i++) {
			for(int j=0;j<3001;j++) {
				CNT[i][j] = Integer.MAX_VALUE;
			}
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)-> {
			return o1[2]-o2[2]; //연산횟수 작은 순
		});
		pq.add(new int[] {1,0,0});
		CNT[1][0] = 0;
		
		while(!pq.isEmpty()) {
			int[] node = pq.poll();
			int screen = node[0];
			int clipboard = node[1];
			int cnt = node[2];
			
//			System.out.println(Arrays.toString(node));
			
			if(screen == S) {
				ANSWER = cnt;
				return;
			}
			
			if(screen > 1030) continue;
			
			//1번 연산
			if(cnt+1<CNT[screen][screen]) {
				CNT[screen][screen] = cnt+1;
				pq.add(new int[] {screen,screen,cnt+1});
			}
			//2번 연산
			if(clipboard!=0 && cnt+1<CNT[screen+clipboard][clipboard]) {
				CNT[screen+clipboard][clipboard] = cnt+1;
				pq.add(new int[] {screen+clipboard,clipboard,cnt+1});
			}
			//3번 연산
			if(screen!=0 && cnt+1<CNT[screen-1][clipboard]) {
				CNT[screen-1][clipboard] = cnt+1;
				pq.add(new int[] {screen-1,clipboard,cnt+1});
			}
		}
		
	}
	
	
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
		ANSWER = Integer.MAX_VALUE;
	}
}