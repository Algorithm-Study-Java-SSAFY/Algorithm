package study_01.Prg_phoneNumber;

import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        // ���ڿ� ���� ������ ���� - phone_book (100��)
        // key ���� ��ȭ��ȣ ������ ���� ������ �ִ´�. 
        // �̶� ���� ��ȭ��ȣ�� 1�ڸ� �� ���� 1�� ������ ���� ���ξ ��ġ�� Ű�� �ִ��� Ȯ���Ѵ�. 
        
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
