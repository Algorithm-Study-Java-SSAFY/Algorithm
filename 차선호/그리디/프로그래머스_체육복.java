import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] nList = new int[n+1];
        for(int i=1;i<n+1;i++){
            nList[i] = 1;
        }
        for(int i=0;i<lost.length;i++){
            nList[lost[i]] --;
        }
        for(int i=0;i<reserve.length;i++){
            nList[reserve[i]] ++;
        }
        
        System.out.println(Arrays.toString(nList));
        
        for(int i=1;i<n+1;i++){
            if(nList[i]==0){
                if(nList[i-1]>1){
                    nList[i] ++;
                    nList[i-1] --;
                }else if(i != n && nList[i+1]>1){
                    nList[i] ++;
                    nList[i+1] --;
                }
            }
        }
        
        System.out.println(Arrays.toString(nList));
        int answer = 0;
        for(int i=1;i<n+1;i++){
            if(nList[i]!=0){
                answer++;
            }
        }
        
        return answer;
    }
}