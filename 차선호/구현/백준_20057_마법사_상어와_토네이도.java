package code_test;


import java.util.*;
import java.io.*;


public class Main {
	
	static int N;
	static int[][] graph;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int[] start = new int[2];
	static int dir, distance, flag, nowDistance;
	static int answer = 0;
	
	
	public static void main(String[] args) throws Exception {
		input();
		
		init();
		
		while(true){
			moveStart(); // 토네이도 이동
			if(!check(start)) break;
			//System.out.println(start[0]+"/"+start[1]+"//"+distance);
			windSend(); // 모래 흩날려라
//			for(int[] a:graph) {
//				System.out.println(Arrays.toString(a));
//			}
			//System.out.println(answer);
		}
		
		System.out.println(answer);
		
		
	}
	
	//토네이도 이동
	static void moveStart() {
		if(nowDistance == distance) {
			dir = (dir+1)%4;
			nowDistance = 0;
			flag++;
		}
		if(flag==2) {
			distance++;
			flag = 0;
		}
		nowDistance++;
		start[0] += dx[dir];
		start[1] += dy[dir];
	}
	
	//모래 흩날려라
	static void windSend() {
		int x = start[0];
		int y = start[1];
		int send = graph[x][y];
		graph[x][y]=0;
		int sum = 0; 
		int out = send;
		//어차피 제거될 모래
		sum += (int) ((double)send*0.01);
		sum += (int) ((double)send*0.01);
		sum += (int) ((double)send*0.02);
		sum += (int) ((double)send*0.02);
		sum += (int) ((double)send*0.07);
		sum += (int) ((double)send*0.07);
		sum += (int) ((double)send*0.1);
		sum += (int) ((double)send*0.1);
		sum += (int) ((double)send*0.05);
		
		
		if(dir==0) { //왼쪽으로 이동해왔다.
			if(check(new int[] {x-1,y+1})) {
				graph[x-1][y+1] += (int) ((double)send*0.01);
				out -= (int) ((double)send*0.01);
			}
			if(check(new int[] {x+1,y+1})) {
				graph[x+1][y+1] += (int) ((double)send*0.01);
				out -= (int) ((double)send*0.01);
			}
			if(check(new int[] {x-1,y})) {
				graph[x-1][y] += (int) ((double)send*0.07);
				out -= (int) ((double)send*0.07);
			}
			if(check(new int[] {x-2,y})) {
				graph[x-2][y] += (int) ((double)send*0.02);
				out -= (int) ((double)send*0.02);
			}
			if(check(new int[] {x+1,y})) {
				graph[x+1][y] += (int) ((double)send*0.07);	
				out -= (int) ((double)send*0.07);	
			}
			if(check(new int[] {x+2,y})) {
				graph[x+2][y] += (int) ((double)send*0.02);	
				out -= (int) ((double)send*0.02);
			}
			if(check(new int[] {x-1,y-1})) {
				graph[x-1][y-1] += (int) ((double)send*0.1);
				out -= (int) ((double)send*0.1);
			}
			if(check(new int[] {x+1,y-1})) {
				graph[x+1][y-1] += (int) ((double)send*0.1);
				out -= (int) ((double)send*0.1);
			}
			if(check(new int[] {x,y-2})) {
				graph[x][y-2] += (int) ((double)send*0.05);
				out -= (int) ((double)send*0.05);
			}
			if(check(new int[] {x,y-1})) {
				graph[x][y-1] += send-sum;
				out -= send-sum;
			}
		}else if(dir==1) { //아래쪽으로 이동해왔다.
			if(check(new int[] {x-1,y-1})) {
				graph[x-1][y-1] += (int) ((double)send*0.01);
				out -= (int) ((double)send*0.01);
			}
			if(check(new int[] {x-1,y+1})) {
				graph[x-1][y+1] += (int) ((double)send*0.01);
				out -= (int) ((double)send*0.01);
			}
			if(check(new int[] {x,y-1})) {
				graph[x][y-1] += (int) ((double)send*0.07);
				out -= (int) ((double)send*0.07);
			}
			if(check(new int[] {x,y+2})) {
				graph[x][y+2] += (int) ((double)send*0.02);
				out -= (int) ((double)send*0.02);
			}
			if(check(new int[] {x,y+1})) {
				graph[x][y+1] += (int) ((double)send*0.07);
				out -= (int) ((double)send*0.07);	
			}
			if(check(new int[] {x,y-2})) {
				graph[x][y-2] += (int) ((double)send*0.02);
				out -= (int) ((double)send*0.02);	
			}
			if(check(new int[] {x+1,y+1})) {
				graph[x+1][y+1] += (int) ((double)send*0.1);
				out -= (int) ((double)send*0.1);
			}
			if(check(new int[] {x+1,y-1})) {
				graph[x+1][y-1] += (int) ((double)send*0.1);
				out -= (int) ((double)send*0.1);
			}
			if(check(new int[] {x+2,y})) {
				graph[x+2][y] += (int) ((double)send*0.05);
				out -= (int) ((double)send*0.05);
			}
			if(check(new int[] {x+1,y})) {
				graph[x+1][y] += send-sum;
				out -= send-sum;
			}
		}else if(dir==2) { //오른쪽으로 이동해왔다.
			if(check(new int[] {x-1,y-1})) {
				graph[x-1][y-1] += (int) ((double)send*0.01);
				out -= (int) ((double)send*0.01);
			}
			if(check(new int[] {x+1,y-1})) {
				graph[x+1][y-1] += (int) ((double)send*0.01);
				out -= (int) ((double)send*0.01);
			}
			if(check(new int[] {x-1,y})) {
				graph[x-1][y] += (int) ((double)send*0.07);
				out -= (int) ((double)send*0.07);
			}
			if(check(new int[] {x-2,y})) {
				graph[x-2][y] += (int) ((double)send*0.02);
				out -= (int) ((double)send*0.02);	
			}
			if(check(new int[] {x+1,y})) {
				graph[x+1][y] += (int) ((double)send*0.07);
				out -= (int) ((double)send*0.07);	
			}
			if(check(new int[] {x+2,y})) {
				graph[x+2][y] += (int) ((double)send*0.02);	
				out -= (int) ((double)send*0.02);	
			}
			if(check(new int[] {x-1,y+1})) {
				graph[x-1][y+1] += (int) ((double)send*0.1);
				out -= (int) ((double)send*0.1);
			}
			if(check(new int[] {x+1,y+1})) {
				graph[x+1][y+1] += (int) ((double)send*0.1);
				out -= (int) ((double)send*0.1);
			}
			if(check(new int[] {x,y+2})) {
				graph[x][y+2] += (int) ((double)send*0.05);
				out -= (int) ((double)send*0.05);
			}
			if(check(new int[] {x,y+1})) {
				graph[x][y+1] += send-sum;
				out -= send-sum;
			}
		}else { //위쪽으로 이동해왔다.
			if(check(new int[] {x+1,y-1})) {
				graph[x+1][y-1] += (int) ((double)send*0.01);
				out -= (int) ((double)send*0.01);
			}
			if(check(new int[] {x+1,y+1})) {
				graph[x+1][y+1] += (int) ((double)send*0.01);
				out -= (int) ((double)send*0.01);
			}
			if(check(new int[] {x,y-1})) {
				graph[x][y-1] += (int) ((double)send*0.07);
				out -= (int) ((double)send*0.07);
			}
			if(check(new int[] {x,y-2})) {
				graph[x][y-2] += (int) ((double)send*0.02);
				out -= (int) ((double)send*0.02);	
			}
			if(check(new int[] {x,y+1})) {
				graph[x][y+1] += (int) ((double)send*0.07);	
				out -= (int) ((double)send*0.07);
			}
			if(check(new int[] {x,y+2})) {
				graph[x][y+2] += (int) ((double)send*0.02);	
				out -= (int) ((double)send*0.02);	
			}
			if(check(new int[] {x-1,y+1})) {
				graph[x-1][y+1] += (int) ((double)send*0.1);
				out -= (int) ((double)send*0.1);
			}
			if(check(new int[] {x-1,y-1})) {
				graph[x-1][y-1] += (int) ((double)send*0.1);
				out -= (int) ((double)send*0.1);
			}
			if(check(new int[] {x-2,y})) {
				graph[x-2][y] += (int) ((double)send*0.05);
				out -= (int) ((double)send*0.05);
			}
			if(check(new int[] {x-1,y})) {
				graph[x-1][y] += send-sum;
				out -= send-sum;
			}
		}
		
		answer += out;
	}
	
	static boolean check(int[] node) {
		if(0<=node[0]&&node[0]<N&&0<=node[1]&&node[1]<N) return true;
		else return false;
	}
	
	//초기값 세팅
	static void init() {
		start[0] = N/2;
		start[1] = N/2;//출발점
		dir = 0; //초기 이동 방향
		distance = 1; // 초기 가는 직선 거리
		nowDistance = 0;
		flag = 0; // 가는 직선 거리 결정
	}
	
	//초기 입력
	static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N =  Integer.parseInt(br.readLine());
		graph = new int[N][N];
		
		for(int i=0;i<N;i++) {
			String[] input = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				graph[i][j] = Integer.parseInt(input[j]);
			}
		}
	}
}