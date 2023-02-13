import java.io.*;
import java.io.IOException;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// Scanner로 입력 받을 시 시간초과 발생
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(bf.readLine());
			if (x == 0) {
				if (!maxHeap.isEmpty()) {
					System.out.println(maxHeap.poll());
				} else {
					System.out.println("0");
				}
			} else {
				maxHeap.add(x);
			}
			
		}
	}

}
