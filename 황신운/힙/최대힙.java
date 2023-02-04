import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n;
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                if (heap.peek() == null) {
                    System.out.println(0);
                } else {
                    System.out.println(heap.poll());
                }
            } else {
                heap.add(num);
            }
        }
    }
}
