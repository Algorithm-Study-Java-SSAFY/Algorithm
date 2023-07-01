package 스터디.중간값말하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;

    // https://nahwasa.com/entry/%EB%B0%B1%EC%A4%80-1655-%EC%9E%90%EB%B0%94-%EA%B0%80%EC%9A%B4%EB%8D%B0%EB%A5%BC-%EB%A7%90%ED%95%B4%EC%9A%94-BOJ-1655-Java
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < N; i++) {
            int cur = Integer.parseInt(in.readLine());

            if(maxHeap.size() == minHeap.size()) {
                maxHeap.add(cur);
            } else {
                minHeap.add(cur);
            }

            if(!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                int min = minHeap.poll();
                int max = maxHeap.poll();
                minHeap.add(max);
                maxHeap.add(min);
            }
            answer.append(maxHeap.peek()).append("\n");
        }
        System.out.print(answer);
    }
}
