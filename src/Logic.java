
public class Logic {
	
	private int[][] numbersValue = new int [9][9];
	private int[][] numbersMeta = new int [9][9];

	public Logic() {
		// TODO Auto-generated constructor stub
	}

	public void writeNumber(int x, int y, int value, boolean pencil) {
		
		if (numbersMeta[x][y] < 4) {
			
			numbersMeta[x][y] = pencil ? 0 : 1;
			numbersValue[x][y] = value;
			
			if (!pencil && value != 0) {
				checkLegal(x, y);;
			}
			
		}
		
		
		
	}
	
	private void checkLegal (int x, int y) {
	
		
	}
	
	// Getters
	public int getValue(int x, int y) {
		return numbersValue[x][y];
	}

	public int getMeta(int x, int y) {
		return numbersMeta[x][y];
	}
	

}
