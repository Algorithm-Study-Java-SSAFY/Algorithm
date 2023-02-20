import java.io.*;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.nio.channels.NonWritableChannelException;
import java.util.*;

import org.omg.Messaging.SyncScopeHelper;

public class Main {
	
	static StringBuffer sb;
	static List<Integer> res = new ArrayList<Integer>();
	static int[] arr; 
	static int n,m;
	public static void main(String[] args) throws NumberFormatException, IOException {
		/*
		 *  (1 ≤ M ≤ N ≤ 7)
		 * 하나의 수열에 같은 수를 여러번 골라도 됨 
		 * 중복되는 수열 여러번 출력 x
		 * 수열은 사전순으로 증가하는 순서로 출력
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuffer();
		String[]nm = br.readLine().split(" ");
		
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);
		arr = new int[m];
		
		find(0);
		System.out.println(sb);

	}
	
	static void find(int idx) {

		if (idx==m) {
			for(int n :arr) {
				sb.append(n+" ");
			}
			sb.append("\n");
			return;
		}
		for (int i=1; i<=n; i++) {
			arr[idx] = i; 
			find(idx+1);
			arr[idx]=0; // 자원 해제 
		}
	}

}