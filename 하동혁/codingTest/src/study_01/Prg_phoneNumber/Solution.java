package study_01.Prg_phoneNumber;

import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        // 문자열 길이 순으로 정렬 - phone_book (100만)
        // key 값을 전화번호 값으로 작은 순으로 넣는다. 
        // 이때 넣을 전화번호를 1자리 수 부터 1씩 증가해 가며 접두어가 겹치는 키가 있는지 확인한다. 
        
        Arrays.sort(phone_book, Comparator.comparing(String::length));
        
        HashMap<String, Integer> hm = new HashMap<>();
        outLoop :
        for (int i=0; i<phone_book.length; i++){
            String check = "";
            for(int j=0; j < phone_book[i].length(); j++){
                check = check+phone_book[i].charAt(j);

                if(hm.containsKey(check)){
                    answer = false;
                    break outLoop;
                }
            }
            hm.put(phone_book[i],0);
        }
        
        
        return answer;
    }
}
