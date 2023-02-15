import java.io.*;
import java.io.IOException;
import java.util.*;

public class Main {

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      int[] A = new int[N];
      for (int i = 0; i < A.length; i++) {
         A[i] = Integer.parseInt(st.nextToken());
      }
      int M = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());
      int[] confirmList = new int[M];
      for (int i = 0; i < confirmList.length; i++) {
         confirmList[i] = Integer.parseInt(st.nextToken());
      }
      
      Arrays.sort(A);
      
      for (int i = 0; i < confirmList.length; i++) {
         if (Arrays.binarySearch(A, confirmList[i]) >= 0)
            System.out.println(1);
         else
            System.out.println(0);
      }
      
   }
}
