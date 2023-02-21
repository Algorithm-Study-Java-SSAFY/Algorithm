/*
 * 숫자 배열이니까 문자열 배열로 바꿔주고.
 * 그대로 내림차순 정렬하면 자리수에 상관없이 큰 수가 앞에 오게 됨.
 * 9 33 15 51 => 9 51 33 15
 */
import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();

        String[] arr = new String[numbers.length];

        for(int i = 0; i < numbers.length; i++){
            //arr[i] = String.valueOf(numbers[i]);
            arr[i] = Integer.toString(numbers[i]);
        }
        //단순히 내림차순 정렬을 할게 아니고 둘이 붙어있을도 무엇이 더 큰지 따져보아야 한다.
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return (b + a).compareTo(a + b);
            }
        });

       // 모두 0이 들어왔을 경우
        if (arr[0].equals("0")){
            return "0";
        } else {
            for(int i = 0; i < arr.length; i++) {
                sb.append(arr[i]);
            }
        }
        String ans = sb.toString();
        return ans;
    }
}