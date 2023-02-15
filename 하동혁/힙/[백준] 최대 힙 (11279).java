import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for (int i=0; i<n; i++ ) {
			int data = Integer.parseInt(br.readLine());
			if (data == 0) {
				if(pq.size()>0) {
					sb.append(pq.remove()).append("\n");
				}else {
					sb.append(0).append("\n");
				}
			}else {
				pq.add(data);
			}
			
		}
		
		System.out.println(sb.toString());
		
		
	}

}