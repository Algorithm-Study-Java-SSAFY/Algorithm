import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static HashMap<String, Integer> degree = new HashMap<>();
	static HashMap<String, List<String>> descendent = new HashMap<>();
	static HashMap<String, List<String>> child = new HashMap<>();
	static List<String> progenitor = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			String name = st.nextToken();
			degree.put(name, 0);
			descendent.put(name, new ArrayList<>());
			child.put(name, new ArrayList<>());
		}

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String d = st.nextToken();
			String p = st.nextToken();
			degree.put(d, degree.getOrDefault(d, 0) + 1);
			descendent.get(p).add(d);
		}

		TopologicalSort();
		
		List<String> nameOrder = new ArrayList<>(child.keySet());
		Collections.sort(nameOrder);
		Collections.sort(progenitor);
		bw.write(progenitor.size() + "\n");
		bw.write(progenitor.toString().replaceAll("[\\[\\],]", "") + "\n");
		for (String n : nameOrder) {
			bw.write(n + " " + child.get(n).size() + " ");
			Collections.sort(child.get(n));
			bw.write(child.get(n).toString().replaceAll("[\\[\\],]", ""));
			bw.write("\n");
		}

		br.close();
		bw.close();
	}

	public static void TopologicalSort() {
		Queue<String> q = new LinkedList<>();

		for (String d : degree.keySet()) {
			if (degree.get(d) == 0) {
				q.add(d);
				progenitor.add(d);
			}
		}

		while (!q.isEmpty()) {
			String cur = q.poll();

			if (descendent.get(cur) != null) {
				for (String c : descendent.get(cur)) {
					degree.put(c, degree.get(c) - 1);
					if (degree.get(c) == 0) {
						q.add(c);
						child.get(cur).add(c);
					}
				}
			}
		}
	}
}
