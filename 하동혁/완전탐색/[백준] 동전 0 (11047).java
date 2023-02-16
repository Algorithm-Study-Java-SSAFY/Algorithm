import java.io.*;
import java.util.*;
import java.util.concurrent.SynchronousQueue;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nk = br.readLine().split(" ");
		int n = Integer.parseInt(nk[0]);
		int k = Integer.parseInt(nk[1]);
		
		int[] mlist = new int[n];
		for (int i=0; i<n; i++) {
			mlist[i] = Integer.parseInt(br.readLine());
		}
		
		int cnt = 0;
		for (int i=n-1; i>-1; i--) {
			int c = k/mlist[i];
			k -= c*mlist[i];
			cnt+=c;
		}
		System.out.println(cnt);
	}

}