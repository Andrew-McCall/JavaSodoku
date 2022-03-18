import javax.swing.*;

public class Sodoku {
	
	public static void main(String[] args) {
		
//		JFrame window = new JFrame("Sodoku");
//
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        window.setResizable(false);
//        window.pack();
//        window.setLocationRelativeTo(null);
//        window.setVisible(true);
//        
        Board board = new Board(42);
        System.out.println(board.test);
        
        board.add(-40);
        
        System.out.println(board.test);
        
	}

}
