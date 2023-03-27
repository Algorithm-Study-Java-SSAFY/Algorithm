package testBaek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 여행가자 {
	public static int N, M;
	public static int[] map;
	public static int[] travelPlan;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		map = new int[N + 1];
		travelPlan = new int[M];

		// 모든 노드 본인을 parent로 초기화: union make set
		for (int i = 1; i <= N; i++)
			map[i] = i;
		
		// 돌아가면서 1인 경우   union find로 연결된 부분 찾기
		for (int i = 1; i <= N; i++) {
			String[] tmp = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				if (tmp[j].equals("1")) solve(i,j+1);
					
			}
		}
		String[] tp = br.readLine().split(" ");
		// 방문하려는 곳들 
		for(int i=0; i<M; i++) travelPlan[i] = Integer.parseInt(tp[i]);

//		for (int i = 1; i <= N; i++)
//			System.out.print(map[i] + " ");
//		System.out.println("");
		boolean check = true;	// 만약 방문하려는 곳의 parent 값이 다르면 연결 X
		for(int i=0; i<M-1; i++) {
			if(map[travelPlan[i]] != map[travelPlan[i+1]]) {
				check = false;
				break;
			}
		}

		if (check)
			System.out.println("YES");
		else
			System.out.println("NO");
	}

	public static void solve(int i, int j) {
		// 두 노드의 parent 값 찾기. union: 두 개의 집합을 하나의 집합으로 합치는 연산 
		int iP = findTravel(i);
		int jP = findTravel(j);
	
		if(iP != jP) {
			if(iP < jP) map[jP] = iP;
			else if (iP> jP) map[iP] = jP;
		}
	}

	// 병합된 집합의 루트 노드를 반환: union find
	public static int findTravel(int x) {
		if (map[x] == x) {
			return x;
		} else {
			return map[x] = findTravel(map[x]);
		}
	}
}
