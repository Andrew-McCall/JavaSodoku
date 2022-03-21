import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.*;

public class Board extends JPanel implements KeyListener, MouseListener {

	private static final long serialVersionUID = -1258829533504593980L;
	
	private final static float FontSize = 36.0f;
	private final static int LineThickness = 4;
	private final static int BoxDimensions = 50;
	
	private int[] cursorCoords = new int[2];
	
	private Logic dataLogic;
	
	public Board(Logic data) {
		
		dataLogic = data;
		
        setPreferredSize(new Dimension(BoxDimensions*9, BoxDimensions*9));
        
        setBackground(new Color(225, 225, 225));

	}

	// Key Listener //
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() >= 49 && e.getKeyCode() < 59) { // Number Input

			dataLogic.writeNumber(cursorCoords[0], cursorCoords[1], e.getKeyCode() - 48, false);

		}else if (e.getKeyCode() == KeyEvent.VK_LEFT) {		// Arrow Controls
			cursorCoords[0] -= 1;
			if (cursorCoords[0] < 0) cursorCoords[0] = 8;
		}else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			cursorCoords[0] += 1;
			if (cursorCoords[0] > 8) cursorCoords[0] = 0;
		}else if (e.getKeyCode() == KeyEvent.VK_UP) {
			cursorCoords[1] -= 1;
			if (cursorCoords[1] < 0) cursorCoords[1] = 8;
		}else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			cursorCoords[1] += 1;
			if (cursorCoords[1] > 8) cursorCoords[1] = 0;
		}
	
		repaint();
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// could be in Clicked but Click is only classed if
		// press and release coords are the same.
		
		// 10 & 30 account for window border offset
		final int boxX = (e.getPoint().x-10) / BoxDimensions;			
		final int boxY = (e.getPoint().y-30) / BoxDimensions;

		if (boxX > 8 || boxX < 0 || boxY > 8 || boxY < 0) {
			return;
		}
		
		cursorCoords[0] = boxX;
		cursorCoords[1] = boxY;
					
		if (e.getButton() == 1){
			
			dataLogic.writeNumber(boxX, boxY, (dataLogic.getValue(boxX, boxY) + 1)%10, false);
				
		}else if (e.getButton() == 3){
			
			dataLogic.writeNumber(boxX, boxY, (dataLogic.getValue(boxX, boxY) + 9 )%10, false);
//			dataLogic.writeNumber(boxX, boxY, 0, false);

		}
		
		repaint();
		
	}
	
	// Graphics //
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
         
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
        
        // Numbers
        // Had issue with half the board checkers overwritng the text
        // g.setColor(new Color(100, 100, 100));
        g.setFont(g.getFont().deriveFont(FontSize));
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
            	
            	// Debug Lines (Meta as pencil Perma)
//		        g.setColor(new Color(180, 180, 180));
//                g.drawString(String.valueOf(dataLogic.getMeta(row,col)), row*BoxDimensions + BoxDimensions/3 , col*BoxDimensions + (int) FontSize);

            	if (dataLogic.getValue(row,col) != 0) {
            		
            		switch (dataLogic.getMeta(row,col)) {
            			case 0:	// Pencil
            		        g.setColor(new Color(180, 180, 180));
            				break;
            				
            			case 1: // Regular
            		        g.setColor(new Color(90, 90, 90));
            				break;
            				
            			case 3: // Permenent
            		        g.setColor(new Color(45, 45, 45));
            				break;
            				
            			case 2: // Error
            			case 4: 
            		        g.setColor(new Color(255, 45, 45));
            		}
            		
                    g.drawString(String.valueOf(dataLogic.getValue(row,col)), row*BoxDimensions + BoxDimensions/3 , col*BoxDimensions + (int) FontSize);
            	
            	}
            	
            }
        }
        
    }
	
	@Override
	public void keyTyped(KeyEvent e) {
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
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	// Getters
	public static float getFontsize() {
		return FontSize;
	}

	public static int getBoxdimensions() {
		return BoxDimensions;
	}
	
}
