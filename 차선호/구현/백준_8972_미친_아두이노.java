import java.util.*;
import java.io.*;

public class Main {
	
	static int R,C;
	static char[][] GRAPH;
	static int[] JONGSOO;
	static List<int[]> ENEMYS;
	static int[] OPS;
	static int[] dx = {0,1,1,1,0,0,0,-1,-1,-1};
	static int[] dy = {0,-1,0,1,-1,0,1,-1,0,1};
	static boolean isLose;
	static boolean[][] VISITED;
	static int cnt;
	static StringBuffer sb;
	
	public static void main(String[] args) throws Exception{
		init();
		for(int i=0;i<OPS.length;i++) {
			int op = OPS[i];
			moveJongsoo(op); //1번
			moveEnemys();
//			System.out.println(ENEMYS.size());
//			for(char[] a:GRAPH) System.out.println(Arrays.toString(a));
//			System.out.println("----------------------");
			if(isLose) {
				cnt = i+1;
				break;
			}
		}
		if(isLose) System.out.println("kraj "+cnt);
		else {
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++) {
					sb.append(GRAPH[i][j]);
				}
				if(i!=R-1) sb.append("\n");
			}
			System.out.println(sb);
		}
	}
	
	static void moveEnemys() {
		List<int[]> survivedList = new ArrayList<>();
		List<int[]> moveEnemys = new ArrayList<>();
		for(int i=0;i<ENEMYS.size();i++) {
			//가장 가까운 방향 어딘지 체크 3번
			int[] enemy = ENEMYS.get(i);
			int mx = enemy[0]+dx[1];
			int my = enemy[1]+dy[1];
			int distance = calculation(mx, my);
			for(int j=2;j<10;j++) {
				if(j==5) continue;
				//돌면서 최소 거리일 때의 mx,my남겨라
				if(distance > calculation(enemy[0]+dx[j],enemy[1]+dy[j])) {
					mx = enemy[0]+dx[j];
					my = enemy[1]+dy[j];
					distance = calculation(enemy[0]+dx[j],enemy[1]+dy[j]);
				}
			}
//			GRAPH[enemy[0]][enemy[1]] = '.';
			if(GRAPH[mx][my]=='M') { //삭제
				VISITED[mx][my] = true;
			}else { //아직까진 생존
				GRAPH[mx][my] = 'M';
				survivedList.add(new int[] {mx,my}); //일단 추가하고 나중에 돌면서 visited true면 최종 enemys에 추가 x
			}
		}
		for(int i=0;i<survivedList.size();i++) {
			if(survivedList.get(i)[0]==JONGSOO[0] && survivedList.get(i)[1]==JONGSOO[1]) { //4번
				isLose = true;
				return;
			}
			if(!VISITED[survivedList.get(i)[0]][survivedList.get(i)[1]]) {
				GRAPH[survivedList.get(i)[0]][survivedList.get(i)[1]] = 'R';
				moveEnemys.add(survivedList.get(i));
			}
			else { //5번
				GRAPH[survivedList.get(i)[0]][survivedList.get(i)[1]] = '.';
				VISITED[survivedList.get(i)[0]][survivedList.get(i)[1]] = false;
			}
		}
		for(int i=0;i<ENEMYS.size();i++) {
			GRAPH[ENEMYS.get(i)[0]][ENEMYS.get(i)[1]] = '.';
		}
		for(int i=0;i<moveEnemys.size();i++) {
			GRAPH[moveEnemys.get(i)[0]][moveEnemys.get(i)[1]] = 'R';
		}
		ENEMYS = moveEnemys;
	}
	
	static int calculation(int x, int y) {
		return Math.abs(x-JONGSOO[0])+Math.abs(y-JONGSOO[1]);
	}
	
	static void moveJongsoo(int op) {
		GRAPH[JONGSOO[0]][JONGSOO[1]] = '.';
		int mx = JONGSOO[0]+dx[op];
		int my = JONGSOO[1]+dy[op];
		JONGSOO[0] = mx;
		JONGSOO[1] = my;
		if(GRAPH[mx][my]=='R') isLose = true; //2번
		GRAPH[mx][my] = 'I';
	}
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] RC = br.readLine().split(" ");
		R = Integer.parseInt(RC[0]);
		C = Integer.parseInt(RC[1]);
		GRAPH = new char[R][C];
		JONGSOO = new int[2];
		ENEMYS = new ArrayList<>();
		for(int i=0;i<R;i++) {
			String input = br.readLine();
			for(int j=0;j<C;j++) {
				GRAPH[i][j] = input.charAt(j);
				if(GRAPH[i][j]=='I') {
					JONGSOO[0] = i;
					JONGSOO[1] = j;
				}else if(GRAPH[i][j]=='R') {
					ENEMYS.add(new int[] {i,j});
				}
			}
		}
		String ops = br.readLine();
		OPS = new int[ops.length()];
		for(int i=0;i<ops.length();i++) {
			OPS[i] = Integer.parseInt(String.valueOf(ops.charAt(i)));
		}
		isLose = false;
		VISITED = new boolean[R][C];
		cnt = -1;
		sb = new StringBuffer();
	}
}