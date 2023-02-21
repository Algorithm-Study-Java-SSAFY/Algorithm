import java.io.*;

class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		System.out.println(fibonacci(n));
	}
	
	public static long fibonacci(int index) {
	    if (index < 0)
			return -1;
		if (index == 0)
			return 0;
		if (index == 1)
			return 1;
		
	    long[] list = new long[index + 1];
			
		list[0] = 0;
		list[1] = 1;
		for (int i = 2; i <= index; i++) {
	        list[i] = list[i - 1] + list[i - 2];
	    }
	    
	    return list[index];
	}
}