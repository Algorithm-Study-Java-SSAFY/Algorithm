import java.io.*;
import java.util.*;
import java.util.concurrent.SynchronousQueue;

public class Main {
	static int max_result = Integer.MIN_VALUE;
	static List<String> fomula;
	static String[] s;
	static HashMap<String, Integer> priority = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		priority.put("-", 1);
		priority.put("+", 1);
		priority.put("*", 2);

		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		s = str.split("");

		int operator_cnt = N / 2;
		boolean[] visited = new boolean[operator_cnt];

		addAndCalcul(new ArrayList<>());
		for (int i = 1; i <= 5 && i <= visited.length; i++) {
			combination(visited, 0, operator_cnt, i);
		}

		bw.write(String.valueOf(max_result));

		br.close();
		bw.close();
	}

	public static void combination(boolean[] visited, int depth, int n, int r) {
		if (r == 0) {
			List<Integer> selected = new ArrayList<>();
			for (int i = visited.length - 1, now = -1; i >= 0; i--) {
				if (visited[i]) {

					// 괄호가 중첩된다면 pass
					if (selected.size() >= 1 && selected.get(now) == i + 1) {
						selected.clear();
						break;
					}
					selected.add(i);
					now++;
				}
			}

			if (!selected.isEmpty()) {
				addAndCalcul(selected);
			}
			return;
		}

		if (depth == n)
			return;

		visited[depth] = true;
		combination(visited, depth + 1, n, r - 1);

		visited[depth] = false;
		combination(visited, depth + 1, n, r);
	}

	public static void addAndCalcul(List<Integer> idx_list) {
		int result = 0;
		fomula = new ArrayList<>();
		fomula.addAll(Arrays.asList(s));

		// 괄호 부분 먼저 계산
		while (!idx_list.isEmpty()) {
			int idx = idx_list.remove(0) * 2;
			int p1 = Integer.parseInt(fomula.get(idx));
			String o = fomula.get(idx + 1);
			int p2 = Integer.parseInt(fomula.get(idx + 2));

			fomula.add(idx, String.valueOf(Calculator(p1, o, p2)));
			fomula.remove(idx + 1);
			fomula.remove(idx + 1);
			fomula.remove(idx + 1);
		}

		// 전체 계산
		if (fomula.size() == 1) {
			result = Integer.parseInt(fomula.get(0));
		} else {
			int p1 = Integer.parseInt(fomula.remove(0));
			String o = fomula.remove(0);
			int p2 = Integer.parseInt(fomula.remove(0));
			result = Calculator(p1, o, p2);
			while (!fomula.isEmpty()) {
				o = fomula.remove(0);
				p2 = Integer.parseInt(fomula.remove(0));
				result = Calculator(result, o, p2);
			}
		}

		// 최댓값 업데이트
		max_result = result > max_result ? result : max_result;
	}

	// 사칙연산 함수
	public static int Calculator(int p1, String o, int p2) {
		if (o.equals("-")) {
			return p1 - p2;
		} else if (o.equals("+")) {
			return p1 + p2;
		} else {
			return p1 * p2;
		}
	}
}
