import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Random;

import javax.swing.*;

public class Sodoku {
	

	public static void main(String[] args) {
				
		JFrame window = new JFrame("Sodoku");
		Logic dataLogic = new Logic();
		
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Menu //
        JPanel menu = new JPanel(new GridLayout(4, 1, 10, 10));

        JLabel title = new JLabel("Sodoku Menu");
        title.setHorizontalTextPosition(JLabel.CENTER);

        menu.add(title, BorderLayout.CENTER);

//        menu.setLayout();
        
        JButton empty = new JButton("Empty Board");
        JButton random = new JButton("Random Board");
        JButton exit = new JButton("Exit");

        empty.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boardinnit(dataLogic, menu, window);
	        }
         });
        
        random.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dataLogic.loadGame(getGameData());
				boardinnit(dataLogic, menu, window);
	        }
         });
        
        exit.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {System.exit(0);}
         });
        
        menu.add(empty);
        menu.add(random);
        menu.add(exit);
        
        window.add(menu);
               
        window.setFocusable(true); 
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
                
	}
	
	public static int[] getGameData() {
		
		int[] output = new int[81];
		
		try {
			String result = "";
			final RandomAccessFile f = new RandomAccessFile(new File("").getAbsolutePath() + "/data/games.csv", "r");
		    Random rand = new Random(); //instance of random class
	        f.seek(rand.nextInt((int) (Math.floor(f.length()/81)*81)));
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
	
	private static void boardinnit(Logic dataLogic, JPanel menu, JFrame window) {
		Board board = new Board(dataLogic);
		menu.setVisible(false);
        window.add(board);
        window.addKeyListener(board);
        window.addMouseListener(board);
        window.pack();
        window.repaint();
	}
	
}
