import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());	
		int minSection = 0;
		
		st = new StringTokenizer(br.readLine());
		HashSet<Integer> removeDupl = new HashSet<>();
		for (int i = 0; i < N; i++) {
			removeDupl.add(Integer.parseInt(st.nextToken()));
		}
		
		List<Integer> sensor = new ArrayList<>(removeDupl);
		Collections.sort(sensor);
		
		List<Integer> gap = new ArrayList<>();
		for (int i = 0; i < sensor.size()-1; i++) {
			gap.add(sensor.get(i+1)-sensor.get(i));
		}
		
		Collections.sort(gap, Collections.reverseOrder());
		for (int i = K-1; i < gap.size(); i++) {
			minSection += gap.get(i);
		}
		
		bw.write(String.valueOf(minSection));
		
		br.close();
		bw.close();
	}
}
