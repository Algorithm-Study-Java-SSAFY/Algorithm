package code_test;


import java.util.*;
import java.io.*;


public class Main {
	
	static int N;
	static int[][] graph;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static HashMap<Integer,HashSet<Integer>> map = new HashMap<>();
	static Queue<Integer> queue = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		init();

		// 자리 배치 완료
		while(!queue.isEmpty()) {
			int student = queue.poll();
			int[] node = search(student);
			graph[node[0]][node[1]] = student;
		}
		
		//만족도 계산
		System.out.println(calculation());
		
	}
	// 만족도 계산
	static int calculation() {
		int result = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int sum = 0;
				for(int k=0;k<4;k++) { //네 방향에 대해서 조사
					int mx = i+dx[k];
					int my = j+dy[k];
					//인접한 학생이 좋아하는 학생이라면
					if(0<=mx&&mx<N&&0<=my&&my<N
							&& map.get(graph[i][j]).contains(graph[mx][my])) sum++;
				}
				if(sum>0) result += (int) Math.pow(10, sum-1);
			}
		}
		return result;
	}
	// 자리 탐색
	static int[] search(int student) {
		int[] node = new int[2]; 
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)-> {
			if(o1[2]!=o2[2]) return o2[2]-o1[2];// 정렬 기준 1.인접한 좋아하는 학생 수
			else if(o1[3]!=o2[3]) return o2[3]-o1[3];// 기준2. 빈칸 수
			else if(o1[0]!=o2[0]) return o1[0]-o2[0];// 기준3. 행
			else return o1[1]-o2[1];// 기준4. 열
		});
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(graph[i][j]==0) {
					int sum = 0; //인접한 좋아하는 학생 수
					int blank = 0; //인접한 빈칸 수
					for(int k=0;k<4;k++) {
						int mx = i+dx[k];
						int my = j+dy[k];
						if(0<=mx&&mx<N&&0<=my&&my<N) {//네 방향에 대하여 인접하는 칸이
							if(map.get(student).contains(graph[mx][my])) sum++;//좋아하는 학생일 때
							else if(graph[mx][my]==0) blank++;//빈 칸일 때
						}
					}
					pq.add(new int[] {i,j,sum,blank});// 행,열,인접한 좋아하는 학생,인접한 빈칸
				}
			}
		}
		int[] data = pq.poll(); // 가장 우선순위 높은 놈
		node[0] = data[0];
		node[1] = data[1];
		return node;
	}
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		
		for(int i=0;i<Math.pow(N, 2);i++) {
			String[] input = br.readLine().split(" ");
			map.put(Integer.parseInt(input[0]), new HashSet<>());
			queue.add(Integer.parseInt(input[0]));
			for(int j=1;j<5;j++) {
				map.get(Integer.parseInt(input[0])).add(Integer.parseInt(input[j]));
			}
		}
	}
}