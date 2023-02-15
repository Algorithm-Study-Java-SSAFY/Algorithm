import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		int n=5;
		int[] lost = {2,4};
		int[] reserve = {3};
		
		int[][] student = new int[n+2][2]; // {학생 number, 체육복 여부}
		student[0][1] = -1;
		student[n+1][1] = -1;
		
		for (int i=1; i<n+1; i++) { 
			student[i][0] = i;
			student[i][1] = 1;
		}
		
		for (int i : lost) { student[i][1] -=1; }
		for (int i : reserve) { student[i][1] += 1;}

		
		for (int i=1; i<n+1; i++) {
			if(student[i][1]==0) { // 체육복이 없다면 => 앞 사람 확인
				if(student[i-1][1]==2) { // 앞 사람 확인
					student[i][1] += 1;
					student[i-1][1] -=1;
				}else if(student[i+1][1]==2) { // 뒷 사람 확인
					student[i][1] += 1;
					student[i+1][1] -=1;
				}
			}
		}
		
		int answer = 0;
		for (int i=1; i<n+1; i++) {
			if(student[i][1]>=1 ) {
				answer+=1;
			}
		}
		System.out.println(answer);
	
	}


}