package study_01.Prg_phoneNumber;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m = new Main();
		m.test();
	}
	
	public boolean test() {
		String phone_book[] = {"119", "97674223", "1195524421"};
		
        Arrays.sort(phone_book);
        System.out.println(Arrays.toString(phone_book));
        // 정렬 결과 : [119, 1195524421, 97674223]

        int i = 1;
        while (i < phone_book.length) {
            if (phone_book[i].indexOf(phone_book[i - 1]) == 0) {
                return false;
            }
            i++;
        }
        return true;
	}
}
