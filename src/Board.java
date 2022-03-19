import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.*;

import javax.swing.*;

public class Board extends JPanel implements KeyListener, MouseListener {
	
	private static final long serialVersionUID = -1258829533504593980L;
	
	private final static int FontSize = 22;
	private final static int LineThickness = 4;
	private final static int BoxDimensions = 50;
	
	private int[] cursorCoords = new int[2];
	private int[][] numbers = new int [9][9];
	
	public Board() {
		
        setPreferredSize(new Dimension(BoxDimensions*9, BoxDimensions*9));
        
        setBackground(new Color(225, 225, 225));

        
	}

	// Key Listener //
	@Override
	public void keyPressed(KeyEvent e) {
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(e.getKeyChar());		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	// Mouse Listener //
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Also works in Clicked but Click is only classed if
		// press and release coords are the same.
		if (e.getButton() == 1){
					
				// 10 & 30 account for window border offset
				final int boxX = (e.getPoint().x-10) / BoxDimensions;			
				final int boxY = (e.getPoint().y-30) / BoxDimensions;
	
				//	System.out.println("%d, %d".formatted(boxX, boxY));
				//	System.out.println("%d, %d".formatted(boxX/3, boxY/3));
	
				cursorCoords[0] = boxX;
				cursorCoords[1] = boxY;
				
				numbers[boxX][boxY] += 1;
								
				repaint();
				
			}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	// Graphics //
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setRenderingHint(
//            RenderingHints.KEY_TEXT_ANTIALIASING,
//            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//        g2d.setRenderingHint(
//            RenderingHints.KEY_RENDERING,
//            RenderingHints.VALUE_RENDER_QUALITY);
//        g2d.setRenderingHint(
//            RenderingHints.KEY_FRACTIONALMETRICS,
//            RenderingHints.VALUE_FRACTIONALMETRICS_ON);
//        
//        g2d.setFont(new Font("Lato", Font.PLAIN, 25));
//        
//        
        // Checkers
        g.setColor(new Color(200, 200, 200));
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
            
                if ((row + col) % 2 == 1) {
                	
                	g.fillRect(
                        col * BoxDimensions, 
                        row * BoxDimensions, 
                        BoxDimensions, 
                        BoxDimensions
                    );
                }
            }    
        }
        
        // Numbers
        // Had issue with half the board checkers overwritng the text
        g.setColor(new Color(255, 255, 255));
        g.setFont(g.getFont().deriveFont(24.0f));
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                g.drawString(String.valueOf(numbers[row][col]), row*BoxDimensions + BoxDimensions/4, col*BoxDimensions + BoxDimensions/2);
            }
        }
        
        // Black lines (Large 3x3)
        g.setColor(new Color(90, 90, 90));
        for (int row = 1; row < 3; row++) {
        	g.fillRect(
                row * BoxDimensions * 3 - LineThickness / 2,
                0,
                LineThickness, 
                BoxDimensions * 9
            );
        }
        
        for (int col = 1; col < 3; col++) {
        	g.fillRect(
    			0,
                col * BoxDimensions * 3 - LineThickness / 2,
                BoxDimensions * 9,
                LineThickness
            );
        }
        
        // Cursor
        g.drawRect(
    		cursorCoords[0]*BoxDimensions,
            cursorCoords[1]*BoxDimensions,
            BoxDimensions,
            BoxDimensions
        );
        
    }
	
}
