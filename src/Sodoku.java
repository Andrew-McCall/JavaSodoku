import javax.swing.*;

public class Sodoku {
	
	public static void main(String[] args) {
		
		Logic dataLogic = new Logic(); 
		
		JFrame window = new JFrame("Sodoku");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Board board = new Board(dataLogic);
        window.add(board);
        window.addKeyListener(board);
        window.addMouseListener(board);
        
        window.setFocusable(true); 
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
                
	}

}
