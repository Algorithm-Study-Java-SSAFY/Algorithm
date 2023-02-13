import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

class Solution2 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            maxHeap.add(x);
        }

        while(!maxHeap.isEmpty()) {
            bw.write(maxHeap.poll().toString());
        }

    }
}