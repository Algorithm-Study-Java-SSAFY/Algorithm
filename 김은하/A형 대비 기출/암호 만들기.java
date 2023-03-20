import java.io.*;
import java.util.*;
import java.util.concurrent.SynchronousQueue;

public class Main {
	static int L, C;
	static List<String> Alphabet = new ArrayList<>();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i <C; i++) {
			Alphabet.add(st.nextToken());
		}
		Collections.sort(Alphabet);
		
		boolean[] visited = new boolean[C];
		
		combination(visited, 0, C, L);
		
		br.close();
		bw.close();
	}

	public static void combination(boolean visited[], int depth, int n, int r) throws IOException {
		if (r == 0) {
			// 모음 자음 계산
			printPass(visited);
			return;
		}
		if (depth == n)
			return;
		
		visited[depth] = true;
		combination(visited, depth+1, n, r-1);
		
		visited[depth] = false;
		combination(visited, depth+1, n, r);
	}
	
	public static void printPass(boolean visited[]) throws IOException {
		String answer = "";
		int cntVo = 0;
		int cntCor = 0;
		for (int i = 0; i < visited.length; i++) {
			if (visited[i]) {
				answer += Alphabet.get(i);
				if (Alphabet.get(i).equals("a") || Alphabet.get(i).equals("e") || Alphabet.get(i).equals("i") || Alphabet.get(i).equals("o") || Alphabet.get(i).equals("u")) {
					cntVo++;
				}
			}
		}
		cntCor = L - cntVo;
		if (cntVo >= 1 && cntCor >=2)
			bw.write(answer + "\n");
	}
}
