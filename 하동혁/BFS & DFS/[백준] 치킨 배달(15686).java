import java.util.*;
import java.io.*;

public class Main {
	
	static int n,m;
	static int[][] data;
	static List<int[]> store = new ArrayList<int[]>(); 
	static List<int[]> sList = new ArrayList<int[]>(); 
	static int[] visited; 
	static int[] make;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nm = br.readLine().split(" ");
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);
		data = new int[n][n];
		for(int i=0; i<n; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<n; j++) data[i][j] = Integer.parseInt(s[j]);
		}
		
		// 치킨집 좌표 탐색 
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(data[i][j] == 2) {
					int[] xy = {i,j};
					store.add(xy);
				}
			}
		}
		
		// 치킨집 조합 생성 - index로 m개 단위로 
		visited = new int[store.size()];
		make = new int[m];
		makeSearch(0,0);
		
		
		// 집에서 부터 치킨집까지의 최단거리 계산                                                                                                               
		List<Integer> minDis = new ArrayList<>();
		for (int i=0; i<sList.size(); i++) {
			int[][] xy = new int[m][2];
			for (int j=0; j<m; j++) {
				xy[j][0] = store.get(sList.get(i)[j])[0];
				xy[j][1] = store.get(sList.get(i)[j])[1];
			}
			
			minDis.add(searchDis(xy));
		}
		
		Collections.sort(minDis);
		System.out.println(minDis.get(0));
		
		
	}
	// 집에서 부터 치킨집까지의 최단거리 계산  
	static int searchDis(int[][] xy) {
		int totalDis =0; 
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(data[i][j]==1) {
					totalDis+=minDisSearch(i,j, xy);
				}
			}
		}
		return totalDis;
	}
	
	static int minDisSearch(int y, int x, int[][] xy) {
		int md = n*n;
		for (int i=0; i<xy.length; i++) {
			int dis = Math.abs(y-xy[i][0])+Math.abs(x-xy[i][1]);
			if(md > dis) {
				md = dis; 
			}
		}
		return md; 
	}
	
	// 치킨집 조합 생성 
	static void makeSearch(int k, int s) {
		if (k==m) {
//			System.out.println(Arrays.toString(make));
			sList.add(make.clone());
			return;
		}
		
		for (int i=s; i<store.size(); i++) {
				make[k]=i;
				makeSearch(k+1, i+1);
				make[k]=0;	
		}
	}
	
}
