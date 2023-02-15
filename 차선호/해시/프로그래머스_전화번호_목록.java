public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book, (String s1, String s2) -> s1.length() - s2.length()); // 문자열 길이로 정렬
        HashSet<String> set = new HashSet<>();
        Loop1 :
        for(int i=0;i<phone_book.length;i++){
        	String phoneNum = "";
        	for(int j=0;j<phone_book[i].length();j++) {
        		phoneNum += phone_book[i].charAt(j); //문자 하나하나 붙여준다.
        		if(set.contains(phoneNum)) { //집합에 있으면
        			answer=false;
        			break Loop1; //이중 반복문 탈출
        		}
        	}
            set.add(phone_book[i]);
        }
        
        return answer;
    }