package SWExpert.메모장프로그램;

class UserSolution {
	int H;
	int W;
	
	String str;
	int N;
	
	int row;
	int col;

	void init(int H, int W, char mStr[]) {
		this.H = H;
		this.W = W;
		int idx = 0;
		str = "";
		row = 0;
		col = 0;
		while (idx < mStr.length && mStr[idx] != '\0') {
			this.str += mStr[idx++];
		}
		this.N = str.length();
	}

	void insert(char mChar) {
		int idx = (row) * W + (col);
		if(idx < N) {
			str = str.substring(0, idx) + mChar + str.substring(idx);
		} else {
			str += mChar;
		}
		init(H, W, str.toCharArray());
		row = row + (col + 1) / 4;
		col = (col + 1) % 4;
		print();
	}

	char moveCursor(int mRow, int mCol) {
		int idx = (mRow - 1) * W + (mCol - 1);
		if(idx < N) {
			row = mRow - 1;
			col = mCol - 1;
			return str.charAt(idx);
		}
		row = N / W;
		col = N % W;
		return '$';
	}

	int countCharacter(char mChar) {
		int ret = 0;
		int idx = (row) * W + (col);
		for(int i = idx; i < N; i++) {
			if(mChar == str.charAt(i)) {
				ret++;
			}
		}
		return ret;
	}
	
	void print() {
		System.out.println(str);
	}
}