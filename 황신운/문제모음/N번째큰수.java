package testBaek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class N번째큰수 {
	public static int N;
	public static Queue<Integer> q;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		q = new PriorityQueue<>(Collections.reverseOrder());
		
		for(int i=0; i<N; i++) {
			String[] tmp = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				q.offer(Integer.parseInt(tmp[j]));
			}
		}
		
		int answer = 0;
		for(int i=0; i<N; i++) answer = q.poll();
		System.out.println(answer);
	}

}
