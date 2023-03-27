package testBaek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class 암호만들기 {
	public static int L, C;
	public static String[] word;
	public static List<String> passwords;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		L = Integer.parseInt(tmp[0]);
		C = Integer.parseInt(tmp[1]);
		passwords = new ArrayList<>();
		
		word = br.readLine().split(" ");
		
		Arrays.sort(word);
		
		String[] words = new String[L];
		getPassword(0,0,L, words);
		
		Collections.sort(passwords);
		
		for(int i=0; i<passwords.size(); i++)
			System.out.println(passwords.get(i));
		
	}
	public static void getPassword(int start, int level, int r, String[] words) {
		if(level == r) {
			String result = checkPassword(words);
			if(result.length() > 0) {
				passwords.add(result);
			}
			return;
		}
		for(int i=start; i<C; i++) {
			words[level] = word[i];
			getPassword(i+1, level+1, r, words);
		}
	}
	
	public static String checkPassword(String[] words) {
		int cntVowels = 0;
		int cntConsonant = 0;
		String password = "";
		
		for(int i=0; i<L; i++) {
			password += words[i];
			if(words[i].equals("a") || words[i].equals("e") || words[i].equals("i") || words[i].equals("o") || words[i].equals("u"))
				cntVowels++;
			else
				cntConsonant++;
		}
		
		if(cntVowels>=1 && cntConsonant >=2)
			return password;
		return "";
		
	}
}
