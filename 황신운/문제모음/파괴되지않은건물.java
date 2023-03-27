package testBaek;

public class 파괴되지않은건물 {
	public static void main(String[] args) {
		int[][] board = {{1,2,3},{4,5,6},{7,8,9}};
		int[][] skill = {{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}};
		int result = solution(board, skill);
		System.out.println(result);
	}
    public static int solution(int[][] board, int[][] skill) {
        int answer = solve(board, skill);
        return answer;
    }
    
    public static int solve(int[][] board, int[][] skill){
        int[][] sumArr = new int[board.length+1][board[0].length+1];
        for(int i=0; i<skill.length; i++){
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            if(type == 1){
                degree *= -1;
            }
            sumArr[r1][c1] += degree;
            sumArr[r1][c2+1] -= degree;
            sumArr[r2+1][c1] -= degree;
            sumArr[r2+1][c2+1] += degree;
        }
        
        int notBroke = getBuilding(board, sumArr);
        return notBroke;
    }
    public static int getBuilding(int[][] board, int[][] sumArr){
        int cnt = 0;
        for(int i=0; i<sumArr.length; i++){
            for(int j=1; j<sumArr[0].length; j++){
                sumArr[i][j] += sumArr[i][j-1];
            }
        }
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                System.out.print(sumArr[i][j] +" ");
            }
            System.out.println("");
        }
        
        for(int j=0; j<sumArr[0].length; j++){
            for(int i=1; i<sumArr.length; i++){
                sumArr[i][j] += sumArr[i-1][j];
            }
        }

        
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(board[i][j] + sumArr[i][j] > 0) cnt++;
            }
        }
        
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                System.out.print(sumArr[i][j] +" ");
            }
            System.out.println("");
        }
        return cnt;
    }
}
