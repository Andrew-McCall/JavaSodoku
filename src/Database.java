import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Random;

import javax.swing.JButton;

public class Database implements ActionListener {

	public String difficulty;
	
	public Database() {
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		difficulty = ((JButton) e.getSource()).getText() + ".csv";
		Sodoku.getDataLogic().loadGame(getGameData());
		Sodoku.boardinnit();
	}

	public void newGame() {
		Sodoku.getDataLogic().loadGame(getGameData());
	}
	
	private int[] getGameData() {
		
		int[] output = new int[81];
		
		try {
			String result = "";
			final RandomAccessFile f = new RandomAccessFile(new File("").getAbsolutePath() + "/data/"+ difficulty, "r");
		    Random rand = new Random();
	        f.seek(rand.nextInt((int) (Math.floor(f.length()/82)*82)));
	        f.readLine();
		    result = f.readLine();
		    f.close();
		    
		    
		    for (int i = 0; i < result.length(); i++) {
		    	output[i] =  Character.getNumericValue(result.charAt(i))  ;
		    } 
		    
	    } catch (Exception e) {
	      System.out.println("Could not Load Game");
	      e.printStackTrace();
	    }
		
		return output;
	}
	
}
