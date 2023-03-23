import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int max;
	static String[] words;
	static HashMap<String, Integer> hashmap = new HashMap<>();
	static List<String> order;
	static List<String> num = new ArrayList<>(Arrays.asList("9", "8", "7", "6", "5", "4", "3", "2", "1", "0"));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		words = new String[N];

		for (int i = 0; i < words.length; i++) {
			words[i] = br.readLine();
			int temp = 1;
			String[] c = words[i].split("");
			for (int j = c.length-1; j >=0; j--) {
				hashmap.put(c[j], hashmap.getOrDefault(c[j], 0) + temp);
				temp *= 10;
			}
		}
		
		order = new ArrayList<>(hashmap.keySet());
		Collections.sort(order, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return hashmap.get(o2).compareTo(hashmap.get(o1));
			}
		});
		
		for (int i = 0; i < order.size(); i++) {
			for (int j = 0; j < words.length; j++) {
				words[j] = words[j].replaceAll(order.get(i), num.get(i));
			}
		}
		
		for (int i = 0; i < words.length; i++) {
			max += Integer.parseInt(words[i]);
		}

		bw.write(String.valueOf(max));

		br.close();
		bw.close();
	}
}
