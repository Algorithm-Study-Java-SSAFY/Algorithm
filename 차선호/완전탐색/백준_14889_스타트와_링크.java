package code_test;

import java.io.*;
import java.util.*;

public class Solution {
	
	static List<Integer []> startList;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] graph = new int[N][N];
		for(int i=0;i<N;i++) {
			String[] data = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				graph[i][j] = Integer.parseInt(data[j]);
			}
		}
		
		startList = new ArrayList<>();
		
		Integer[] arr = new Integer[N]; //전체 사람
		for(int i=0;i<N;i++) {
			arr[i] = i;
		}
		int r = N/2; //팀 인원 수
		Integer[] output = new Integer[r];
		boolean[] visited = new boolean[N];
		combination(arr,output,visited,0,0,r);
		
		int[] result = new int[startList.size()/2];
		for(int i=0;i<startList.size()/2;i++) {
			List<Integer> sList = new ArrayList<>(Arrays.asList(startList.get(i)));
			List<Integer> lList = new ArrayList<>(Arrays.asList(arr));
			lList.removeAll(sList); // 전체에서 스타트팀 뺀 차집합
			int startSum = 0;
			int endSum = 0;	
			//System.out.println("======="+sList.toString()+" "+lList.toString());
			for(int k=0;k<sList.size();k++) {
				for(int m=k+1;m<sList.size();m++) {
					startSum += graph[sList.get(k)][sList.get(m)] + graph[sList.get(m)][sList.get(k)];
					endSum += graph[lList.get(k)][lList.get(m)] + graph[lList.get(m)][lList.get(k)];
				}
			} 
			result[i] = Math.abs(startSum-endSum);
		}
		
		Arrays.sort(result);
		System.out.println(result[0]);
		
	}
	
	static void combination(Integer arr[], Integer output[], boolean visited[], int start, int depth, int r) {
		
		if(r == depth) {
			Integer[] startOutput = output.clone();
			startList.add(startOutput);
			
			return;
		}
		
		for(int i=start;i<arr.length;i++) {
			
			output[depth] = arr[i];
			visited[i] = true;
			combination(arr,output,visited,arr[i]+1,depth+1,r);
			visited[i] = false;
		}
		
	}
}