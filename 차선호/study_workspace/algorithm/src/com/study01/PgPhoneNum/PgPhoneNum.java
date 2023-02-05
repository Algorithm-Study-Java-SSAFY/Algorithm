package com.study01.PgPhoneNum;

import java.io.*;
import java.util.*;

public class PgPhoneNum {

	
	public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book, (String s1, String s2) -> s1.length() - s2.length());
        HashSet<String> set = new HashSet<>();
        Loop1 :
        for(int i=0;i<phone_book.length;i++){
        	String phoneNum = "";
        	for(int j=0;j<phone_book[i].length();j++) {
        		phoneNum += phone_book[i].charAt(j);
        		if(set.contains(phoneNum)) {
        			answer=false;
        			break Loop1;
        		}
        	}
            set.add(phone_book[i]);
        }
        
        return answer;
    }

}
