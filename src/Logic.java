import java.util.ArrayList;

public class Logic {
	
	private int[][] numbersValue = new int [9][9];
	private int[][] numbersMeta = new int [9][9];

	private  ArrayList<int[]> errors = new ArrayList<int[]>();
	
	public Logic() {
		// TODO Auto-generated constructor stub
	}

	public void writeNumber(int x, int y, int value, boolean pencil) {
		
		if (numbersMeta[x][y] < 3) {
			numbersMeta[x][y] = pencil ? 0 : 1;
			numbersValue[x][y] = value;
			
			if (!pencil) {
				
				checkLegal(x,y);
					
				}
			}
			
		}
	
	private void checkLegal(int x, int y) {
		
		checkOne(x, y);
		
		for (int i = 0; i < 9; i++) {
			for (int z = 0; z < 9; z++) {
				if (numbersMeta[i][z] != 0 && numbersMeta[i][z] % 2 == 0){
					checkOne(i, z);
				}
			}
		}
		
		

	}
	
	private void checkOne (int x, int y) {
		
		setError(x, y, false);
		
		int[] prevY = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		int[] prevX = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
				
		for (int i = 0; i < 9; i++) {

			if (numbersValue[x][i] > 0) {
				if (prevX[numbersValue[x][i] - 1] != -1) {
					setError(x, i, true);
					setError(x, prevX[numbersValue[x][i] - 1], true);
				}else {
					prevX[numbersValue[x][i] - 1] = i;
				}
			}
			
			if (numbersValue[i][y] > 0) {
				if (prevY[numbersValue[i][y] - 1] != -1) {
					setError(i, y, true);
					setError(prevY[numbersValue[i][y] - 1], i, true);
				}else {
					prevY[numbersValue[i][y] - 1] = i;
				}
			}
			
		}
		
		
		
	}
	
	private void setError(int x, int y, boolean error) {
		
		if (numbersMeta[x][y] != 0) {
			if (error) {
				numbersMeta[x][y] += (numbersMeta[x][y] % 2 == 0)?0:1;
			}else{
				numbersMeta[x][y] -= (numbersMeta[x][y] % 2 == 0)?1:0;
			}
		}
	}
		
	
	// Getters
	public int getValue(int x, int y) {
		return numbersValue[x][y];
	}

	public int getMeta(int x, int y) {
		return numbersMeta[x][y];
	}
	
}
