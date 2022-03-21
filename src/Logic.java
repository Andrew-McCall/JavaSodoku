public class Logic {
	
	private int[][] numbersValue = new int [9][9];
	private int[][] numbersMeta = new int [9][9];
	
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
				
		for (int i = 0; i < 9; i++) {
			for (int z = 0; z < 9; z++) {
				if ((numbersMeta[i][z] == 2 || numbersMeta[i][z] == 4) && numbersValue[i][z] != 0 ){
					setError(i, z, false);
					checkStraights(i, z);
				}
			}
		}
		
		checkStraights(x, y);

	}
	
	private void checkStraights (int x, int y) {
		
		boolean isError = false;
		
		for (int i = 0; i < 9; i++) {

			if (y != i && numbersValue[x][i] == numbersValue[x][y]) {
				setError(x, i, true);
				isError = true;
			}
			
			if (x != i && numbersValue[i][y] == numbersValue[x][y]) {
				setError(i, y, true);
				isError = true;
			}
			
		}
		
		if (isError) {
			setError(x, y, true);
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
