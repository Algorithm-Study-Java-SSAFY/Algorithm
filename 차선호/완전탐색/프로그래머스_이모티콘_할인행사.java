import java.util.*;
import java.io.*;
import java.math.BigDecimal;

class Solution {
	
	static List<int[]> combs = new ArrayList<>();
	static int[] ARR = {10,20,30,40};
	
	public static void main(String[] args) {
//		
		int[][] users = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
		int[] emoticons = {1300, 1500, 1600, 4900};
		
		System.out.println(Arrays.toString(solution(users,emoticons)));
		
	}
	
    static int[] solution(int[][] users, int[] emoticons) {
        List<int[]> result = new ArrayList<>();
        combination(0,new int[emoticons.length]);
        for(int[] discount : combs) {
        	int[] prices = new int[emoticons.length];
        	for(int i=0;i<emoticons.length;i++) {
        		prices[i] = emoticons[i]*(100-discount[i])/100;
        	}
        	result.add(calculation(users, prices, discount));
        }

        Collections.sort(result,(o1,o2)->{
        	if(o1[0]!=o2[0]) return o2[0]-o1[0];
        	else return o2[1]-o1[1];
        });
        
        return result.get(0);
    }
    
    static int[] calculation(int[][] users, int[] prices, int[] discount) {
    	int service = 0;
    	int sales = 0;
    	for(int i=0;i<users.length;i++) {
    		int sum = 0;
    		for(int j=0;j<prices.length;j++) {
    			if(users[i][0] <= discount[j] ){
    				sum += prices[j];
    			}
    		}
    		if(sum >= users[i][1]) service ++;
    		else sales += sum;
    	}
    	
    	return new int[] {service,sales};
    	
    }
    
    static void combination(int depth, int[] arr) {
    	if(depth == arr.length) {
    		combs.add(arr.clone());
    		return;
    	}
    	
    	for(int i=0;i<4;i++) {
    		arr[depth] = ARR[i];
    		combination(depth+1,arr);  		
    	}
    }
}