import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        Scanner sc = new Scanner(System.in);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String N = br.readLine();
        Integer num = Integer.parseInt(N);

        while (num > 0) {
            String xStr = br.readLine();
            Integer x = Integer.parseInt(xStr);

            if (x > 0) {
                maxHeap.add(x);
            } else {
                if (maxHeap.size() == 0) {
                    System.out.println('0');
                } else {
                    System.out.println(maxHeap.poll());
                }
            }

            num--;
        }
    }
}