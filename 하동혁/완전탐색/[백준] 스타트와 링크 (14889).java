import java.io.*;
import java.util.*;

public class Main {
	static int n = 0;
	static int dn = 0;
	static int[][] data;
	static List<List<Integer>> arr;
	static List<Integer> allTeam;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dn = n / 2;

		data = new int[n][n];
		allTeam = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				data[i][j] = Integer.parseInt(stk.nextToken());
			}
			allTeam.add(i);

		}

		// -----------------------------------------------------------------

		// 조합 생성
		arr = new ArrayList<>();
		List<Integer> subList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			makeList(subList, i);
		}

		// 비교
		int minData = detailmake();
		System.out.println(minData);

	}

	// 1차 조합
	static void makeList(List<Integer> mlist, int k) {
		List<Integer> clist = new ArrayList<>();
		clist.addAll(mlist);
		clist.add(k);

		if (clist.size() == dn) {
			arr.add(clist);
		} else {
			for (int i = k + 1; i < n; i++) {
				makeList(clist, i);
			}
		}
	}
	


	static int detailmake() {
		
		int minNum = 100*2*dn;
		for (int i=0; i<arr.size()/2; i++) {
			List<Integer> team1 = new ArrayList<>(arr.get(i));
			List<Integer> team2 = new ArrayList<>(allTeam);
			team2.removeAll(team1);

			int sum1=0;
			int sum2=0;
			for(int j=0; j<dn-1; j++) {
				for(int k=j+1; k<dn; k++) {
					sum1 += data[team1.get(j)][team1.get(k)] + data[team1.get(k)][team1.get(j)];
					sum2 += data[team2.get(j)][team2.get(k)] + data[team2.get(k)][team2.get(j)];
				}
			}
			

			if(minNum > Math.abs(sum1-sum2)) {
				minNum =  Math.abs(sum1-sum2);
			}

		}

		return minNum;

	}

}