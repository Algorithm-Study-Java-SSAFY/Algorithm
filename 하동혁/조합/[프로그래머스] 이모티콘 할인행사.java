class Solution {
    
  static int emoSize = 0; 
	static int[] combiArr; 
	static int[] percent = {10,20,30,40};
	static int[][] USER; 
	static int[] EMOTICONS;
	static int resJoin = 0, resPrice = 0; 
    
    public int[] solution(int[][] users, int[] emoticons) {
		USER = users; 
		EMOTICONS = emoticons;
		emoSize = emoticons.length;
		combiArr = new int[emoSize];
		
		combination(0);
		
		int[] res = new int[] {resJoin, resPrice}; 
		return res; 
    }
    
    	// 이모티콘 할인 퍼센트 조합 생성 
	static void combination(int deep) {
		if(deep == emoSize) {
			calculation();
			return; 
		}
		
		for (int i=0; i<percent.length; i++){
			combiArr[deep] = percent[i];
			combination(deep+1);
			combiArr[deep] = 0;
		}
	}
	
	// 계산 
	static void calculation() {
		
		int[] emoPrice = new int[emoSize]; // 할인된 가격 저장 
		for(int i=0; i<emoSize; i++) {
			emoPrice[i] = EMOTICONS[i] - (EMOTICONS[i]/100)*combiArr[i];
		}
		
		int totalJoin = 0; 
		int totalPrice = 0; 
		
		for(int i=0; i<USER.length; i++) {
			int userPer = USER[i][0];
			int userCut = USER[i][1];
			
			int buySum = 0; 
			
			for(int j=0; j<emoSize; j++) {
				if(combiArr[j] >= userPer) { // user가 원하는 할인률 보다 더 높을때 
					buySum += emoPrice[j];
				}
			}
			// user의 기준가 이상으로 이모티콘을 구매했다면 -> 이모티콘 플러스 가입 
			if(buySum >= userCut) {
				totalJoin += 1; 
			}else {
				totalPrice += buySum; 
			}
		}
		
		if(resJoin < totalJoin) {
			resJoin = totalJoin; 
			resPrice = totalPrice;
		}else if(resJoin ==  totalJoin && totalPrice > resPrice){
			resPrice = totalPrice;
		}
		
		
	}
}