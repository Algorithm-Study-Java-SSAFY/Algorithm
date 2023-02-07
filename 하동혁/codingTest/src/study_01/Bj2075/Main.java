package study_01.Bj2075;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		// �ִ��� 
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for (int i=0; i<n; i++) {
			// �� �� �Է¹޾Ƽ� " " ���� �ϳ� �������γ����� ��ٿ� ��� 
			StringTokenizer stk = new StringTokenizer(br.readLine()," ");
			for (int j=0; j<n; j++) {
				// ����� �ϳ��� ���� ť�� ��� 
				pq.add(Integer.parseInt(stk.nextToken()));
			}
		}
		
		for(int i=0; i<n-1; i++) {
			pq.remove();
		}
		System.out.println(pq.remove());
	}

}
