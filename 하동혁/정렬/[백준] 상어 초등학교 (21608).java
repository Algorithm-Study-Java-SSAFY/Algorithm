import java.util.*;
import java.io.*;

public class Main {

	static int N; 
	static int[][] board; 
	static HashMap<Integer, HashSet<Integer>> likeList = new HashMap<>();
	static int[] outArr;
	
	static PriorityQueue<int[]> pq;
	static int[] DY = {1,-1,0,0};
	static int[] DX = {0,0,1,-1}; 

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		outArr = new int[N*N];
		for(int i=0; i<N*N; i++) {
			String[] s = br.readLine().split(" ");
			likeList.put(Integer.parseInt(s[0]), new HashSet<>());
			outArr[i] = Integer.parseInt(s[0]);
			for(int j=1; j<=4; j++) {
				likeList.get(Integer.parseInt(s[0])).add(Integer.parseInt(s[j]));
			}
		}

		for(int i=0; i<N*N; i++) {
			find(outArr[i]);
		}
		
		System.out.println(score());
	}

// 마지막 점수 계산 
	static int score() {
		int sum=0; 
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int idx = board[i][j];
				int cnt = 0;
				for(int k=0; k<4; k++) {
					int dy = i+DY[k];
					int dx = j+DX[k];
					if(0<=dy && dy<N && 0<=dx && dx<N) {
						if(likeList.get(idx).contains(board[dy][dx])) cnt++;
					}
				}
				
				if(cnt==1) sum += 1; 
				else if(cnt==2) sum += 10;
				else if(cnt == 3) sum += 100; 
				else if(cnt == 4) sum += 1000; 
			}
		}
		return sum;
	}
	
	static void find(int idx) {
// 우선순위 큐 (Heap) 사용해서 정렬 
		pq = new PriorityQueue<>((o1,o2)->{
			
			if(o1[0]!=o2[0]) return o2[0]-o1[0];  // 인접한 친구 오름차순 
			else if(o1[1]!=o2[1]) return o2[1]-o1[1]; // 빈칸 오름차순 
			else if(o1[2]!=o2[2]) return o1[2]-o2[2]; // y좌표 내림차순 
			else if(o1[3]!=o2[3]) return o1[3]-o2[3]; // x좌표 내림차순 
			else return 0; 
			
		}); 
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(board[i][j] == 0) {
					int[] data = new int[4]; // 인접한 친구, 빈칸 수, y, x
					data[2] = i; 
					data[3] = j;
					for(int k=0; k<4; k++) {
						int dy = i+DY[k];
						int dx = j+DX[k];
						if(0<=dy && dy<N && 0<=dx && dx<N) {
							if(likeList.get(idx).contains(board[dy][dx])) data[0]++; 
							else if(board[dy][dx] == 0) data[1]++; 
						}
					}
					pq.add(data);
				}

			}
		}
		
		int [] top = pq.poll();
		board[top[2]][top[3]] = idx;
		
	}

}