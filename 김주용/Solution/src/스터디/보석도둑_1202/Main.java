package 스터디.보석도둑_1202;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

class Juel implements Comparable<Juel> {
    int idx = -1, weight, price;

    public Juel(int weight, int prices) {
        this.weight = weight;
        this.price = prices;
    }

    public Juel(int idx, int weight, int price) {
        this.idx = idx;
        this.weight = weight;
        this.price = price;
    }

    @Override
    public int compareTo(Juel o) {
        if (this.weight == o.weight) {
            return o.price - this.price;
        }
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Juel{" + idx +
                "weight=" + weight +
                ", price=" + price +
                '}';
    }
}
public class Main {

    static int N, K;
    static Juel[] juels;
    static int[] bags;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        K = Integer.parseInt(line[1]);

        juels = new Juel[N];
        for(int i = 0; i < N; i++) {
            line = in.readLine().split(" ");
            int M = Integer.parseInt(line[0]);
            int V = Integer.parseInt(line[1]);
            juels[i] = new Juel(M, V);
        }

        bags = new int[K];
        for(int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(in.readLine());
        }
        solution();
    }

    // 작은 가방에 먼저
    public static void solution() {
        Arrays.sort(bags);
        Arrays.sort(juels);
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        long answer=  0;
        int idx = 0;
        for(int i = 0; i < K; i++) {
            while (idx < N && juels[idx].weight <= bags[i]) {
                pq.add(juels[idx].price);
                idx++;
            }

            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
        }
        System.out.println(answer);
    }
}
