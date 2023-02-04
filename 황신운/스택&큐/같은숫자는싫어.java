import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
        Stack<Integer> stack = new Stack<>();
        
        stack.push(arr[0]);
        for(int i=1; i<arr.length; i++){
            int num = stack.peek();
            if(num != arr[i]){
                stack.push(arr[i]);
            }
        }
        answer = new int[stack.size()];
        for(int index = stack.size()-1; index >=0 ; index--){
            answer[index] = stack.pop();
        }
        return answer;
    }
}