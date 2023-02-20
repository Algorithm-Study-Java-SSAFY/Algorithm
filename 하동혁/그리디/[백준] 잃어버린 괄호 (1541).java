import java.io.*;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.nio.channels.NonWritableChannelException;
import java.util.*;

import org.omg.Messaging.SyncScopeHelper;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		/*
		 *  - 연산자가 등장하는 부분부터 괄호'(' + 또 다른 -연산자가 나타나는 부분까지 ')' 
		 *  
		 *  가장 처음과 마지막은 문자이기 때문에 첫 번째 숫자에 -연산자가 나타나지 않는다.  
		 *  즉, 첫 번째 숫자는 양수 
		 */

		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		String[] data = br.readLine().split("-");
		
		int res = 0; 
		String[] subData = data[0].split("\\+");  // +, *, ^ 문자로 문자열을 나눌땐 \\ 을 붙여야 한다. 
		for(String s : subData) { // -로 나누어지기 전 첫 번재 숫자들은 모두 양수 => 바로 결과에 + 
			res += Integer.parseInt(s);
		}
	
		
		for (int i=1; i<data.length; i++) {
			
			subData = data[i].split("\\+");
			for(String s : subData) {
				res += -(Integer.parseInt(s));
			}
		}
		
		System.out.println(res);
	}

}