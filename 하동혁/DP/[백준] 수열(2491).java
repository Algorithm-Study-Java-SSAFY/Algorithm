import java.io.*;
import java.util.*;
import java.util.concurrent.SynchronousQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		List<Integer> data = new ArrayList<>();
		while(st.hasMoreTokens()) {
			data.add(Integer.parseInt(st.nextToken()));
		}
		
		List<Integer> dp = new ArrayList<>(); 
		// 큰 순 n
		dp.add(1);
		for (int i=1; i<n; i++) {
			if (data.get(i)>=data.get(i-1)) {
				dp.add(dp.get(i-1)+1);
			}else {
				dp.add(1);
			}
		}
		
		// 작은 순 n
		dp.add(1);
		for (int i=1; i<n; i++) {
			if (data.get(i)<=data.get(i-1)) {
				dp.add(dp.get(i+n-1)+1);
			}else {
				dp.add(1);
			}
		}
		
		System.out.println(Collections.max(dp));
		
	}

}