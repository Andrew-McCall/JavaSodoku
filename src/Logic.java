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
				checkLegal(x, y);

				errors.forEach(e -> checkLegal(e[0], e[1]));
					
				}
			}
			
		}
	
	private void checkLegal (int x, int y) {
		
		int[] prevY = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		int[] prevX = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		
		int errorX = -1;
		int errorY = -1;
		
		for (int i = 0; i < 9; i++) {
			
			setError(x, i, false);
			setError(i, y, false);

			if (numbersValue[x][i] > 0) {
				if (prevX[numbersValue[x][i] - 1] != -1) {
					errorX = prevX[numbersValue[x][i] - 1];
					setError(x, i, true);
				}else {
					prevX[numbersValue[x][i] - 1] = i;
				}
			}
			
			if (numbersValue[i][y] > 0) {
				if (prevY[numbersValue[i][y] - 1] != -1) {
			     	errorY = prevY[numbersValue[i][y] - 1];
					setError(i, y, true);
				}else {
					prevY[numbersValue[i][y] - 1] = i;
				}
			}
			
		}
		
		
		
	}
	
	private void setError(int x, int y, boolean error) {
		
		
		if (numbersMeta[x][y] != 0) {
			if (numbersMeta[x][y] % 2 == 0) {
				numbersMeta[x][y] -= (error)?0:1;
			}else {
				numbersMeta[x][y] += (error)?1:0;
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
