import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br;
	static int N, M;
	static HashMap<Integer, Node> nMap;
	static List<HashSet<Integer>> answer;

	static class Node {
		List<Integer> big;
		List<Integer> small;

		Node() {
			big = new ArrayList<>();
			small = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		init();

		for (int i = 1; i <= N; i++) {
			findBig(i, i);
			findSmall(i, i);
		}

		for (int i = 1; i <= N; i++) {
//			System.out.println(answer.get(i));
			System.out.println(N - answer.get(i).size() - 1);
		}

	}

	static void findBig(int point, int n) {
		for (int i : nMap.get(n).big) {
			if (!answer.get(point).contains(i)) {
				answer.get(point).add(i);
				findBig(point, i);
			}
		}
	}

	static void findSmall(int point, int n) {
		for (int i : nMap.get(n).small) {
			if (!answer.get(point).contains(i)) {
				answer.get(point).add(i);
				findSmall(point, i);
			}
		}
	}

	static void init() throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		nMap = new HashMap<>();
		for (int i = 1; i <= N; i++) {
			nMap.put(i, new Node());
		}

		answer = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			answer.add(new HashSet<>());
		}

		for (int i = 0; i < M; i++) {
			String[] s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			
			nMap.get(a).small.add(b);
			nMap.get(b).big.add(a);

		}

	}

}
