import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Sodoku {

//	private String difficulty = "";
	
	private static JFrame window;
	private static Logic dataLogic;
	private static JPanel menu;
	private static Database database;
	
	public static void main(String[] args) {
				
		window = new JFrame("Sodoku");
		dataLogic = new Logic();
		database = new Database();
		
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Menu //
        menu = new JPanel(new GridLayout(6, 1, 15, 15));

        JLabel title = new JLabel("Sodoku Menu");
        title.setHorizontalTextPosition(JLabel.CENTER);

        menu.add(title, BorderLayout.CENTER);

//        menu.setLayout();
        
        JButton empty = new JButton("Empty Board");
        
        JButton easy = new JButton("Easy");
        JButton intermediate = new JButton("Intermediate");
        JButton expert = new JButton("Expert");

        JButton exit = new JButton("Exit");

        empty.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boardinnit();
	        }
         });
        
        easy.addActionListener( new Database() );
        intermediate.addActionListener( new Database() );
        expert.addActionListener( new Database() );

        exit.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {System.exit(0);}
         });
        
        menu.add(empty);
        
        menu.add(easy);
        menu.add(intermediate);
        menu.add(expert);
        
        menu.add(exit);
        
        window.add(menu);
               
        window.setFocusable(true); 
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
	}	
	
	public static void boardinnit() {
		Board board = new Board(dataLogic);
		menu.setVisible(false);
        window.add(board);
        window.addKeyListener(board);
        window.addMouseListener(board);
        window.pack();
        window.repaint();
	}
	
	// Getters and Setters
	public static Database getDatabase() {
		return database;
	}

	public static void setDatabase(Database database) {
		Sodoku.database = database;
	}

	public static Logic getDataLogic() {
		return dataLogic;
	}

	public static void setDataLogic(Logic dataLogic) {
		Sodoku.dataLogic = dataLogic;
	}



	
}
