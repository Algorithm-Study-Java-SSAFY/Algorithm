import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br; 
	static int N; // 눈덩이 개수 
	static int[] snow; 
	
	static HashSet<Integer> sumSet; 
	static List<Integer> sumList;
	static HashMap<Integer, List<int[]>> hm; 
	
	static List<int[]> subList; 
	static List<int[]> subList2; 
	static int minDif = Integer.MAX_VALUE; 
	
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        init();
        createMap();
        Collections.sort(sumList);
        
        checkSelf(); 
        if(minDif == 0) {
        	System.out.println(0);
        }else {
        	checkDif();
        	System.out.println(minDif);
        }
        
        
    }
    
    // @@@ 2번 확인 @@@
    static void checkDif() {
    	out:for(int i=0; i<sumList.size(); i++) {
    		for(int j=i+1; j<sumList.size(); j++) {
    			
    			if(minDif > Math.abs(sumList.get(i) - sumList.get(j))){
    				boolean tf = check2(sumList.get(i), sumList.get(j));
//    				System.out.println("sum: " +sumList.get(i) +" / " + sumList.get(j) +"  /  dif: "+ Math.abs(sumList.get(i) - sumList.get(j)));
    				if(tf) {
    					minDif = Math.abs(sumList.get(i) - sumList.get(j));
    					continue out; // 차이가 작은 순으로 올라가기 때문에 가장 낮은 곳에서 찾다면 다음 i로 진행하면된다. 
    				}
    				
    			}else {
    				continue out; // 어느 시점에 i, j 값이 minDif보다 크다면 j가 커지면 무조건 minDif보다 크기 때문에 바로 다음 i로 진행한다. 
    			}
    			
    		}
    	}
    }
    
    static boolean check2(int sum1, int sum2) {
    	subList = hm.get(sum1);
    	subList2 = hm.get(sum2);
    	
    	for(int i=0; i<subList.size(); i++) {
    		int[] a = subList.get(i);
    		for(int j=0; j<subList2.size(); j++){
    			int[] b = subList2.get(j);
    			
    			if(a[0] != b[0] && a[1] != b[1] && a[0] != b[1] && a[1] != b[0]) {
//    				System.out.println("if: "+ a[0]+","+a[1] +"  /  " +b[0]+","+b[1]);
    				return true; 
    			}
    		}
    	}
    	
    	return false; 
    }
    


    // @@@ 1번 확인 @@@
    // 동일한 합 중에 다른 눈덩이를 선택하는 경우가 있는가 
    // 자기 자신 체크하는 경우로 최악 179700번 (한 번) 돈다. 
    static void checkSelf() {
    	for(int i=0; i<sumList.size(); i++) {
    		if(hm.get(sumList.get(i)).size() > 1) { // 적어도 두개 이상은 있어야 비교할 수 있음 
        		subList = hm.get(sumList.get(i));
        		boolean tf = check(); 
        		
        		if(tf) {
        			minDif = 0; // 동일한 sum을 비교하기 때문에 해당되는 것이 있다면 그 차이는 0
        			return; 
        		}
        		
    		}
    		else continue;	
    	}
    }
    
    static boolean check() {
    	for(int i=0; i<subList.size(); i++) {
    		int[] a = subList.get(i);
    		for(int j=i+1; j<subList.size(); j++) {
    			int[] b = subList.get(j);
    			if(a[0] != b[0] && a[1] != b[1] && a[0] != b[1] && a[1] != b[0]) return true; 
    		}
    	}
    	return false;  
    }
    
    
    static void createMap() {
    	
    	for(int i=0; i<N; i++) {
    		for(int j=i+1; j<N; j++) {
    			int sum = snow[i] + snow[j];
    			sumSet.add(sum);
    			
    			if(!hm.containsKey(sum)) {
    				hm.put(sum, new ArrayList<>());
    				hm.get(sum).add(new int[] {i,j});
    			}else {
    				hm.get(sum).add(new int[] {i,j});
    			}
    		}
    	}
    	
    	sumList = new ArrayList<>(sumSet);
    	
    }

    
    static void init() throws NumberFormatException, IOException {
    	N = Integer.parseInt(br.readLine());
    	
    	String[] s = br.readLine().split(" ");
    	snow = new int[N];
    	for(int i=0; i<N; i++) {
    		snow[i] = Integer.parseInt(s[i]);
    	}
    	
    	Arrays.sort(snow);
    	
    	sumSet = new HashSet<>();
    	sumList = new ArrayList<>(); 
    	hm = new HashMap<>(); 
    
    }
}