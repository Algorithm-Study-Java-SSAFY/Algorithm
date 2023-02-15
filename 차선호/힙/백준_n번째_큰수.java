import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] graph = new int[N][N];
		
		for(int i=0;i<N;i++) {
			String[] data = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				graph[i][j] = Integer.parseInt(data[j]);
			}
		}
		
		
		PriorityQueue<Integer> maxHip = new PriorityQueue<Integer>(Collections.reverseOrder());
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				maxHip.add(graph[i][j]);
			}
		}
		
		int answer = 0;
		for(int k=0;k<N;k++) {
			answer = maxHip.poll();
		}
		System.out.println(answer);
	}
}