import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.*;

public class Board extends JPanel implements KeyListener, MouseListener, ActionListener {

	private static final long serialVersionUID = -1258829533504593980L;
	
	private final static float FontSize = 36.0f;
	private final static int LineThickness = 4;
	private final static int BoxDimensions = 50;
	
	private int time = 0;
	
	private int[] cursorCoords = {4, 4};
	
	private boolean pencil = true;
	private int selectedNo = 0;
	
	private Logic dataLogic;
	
	public Board(Logic data) {
		
		dataLogic = data;
		
        setPreferredSize(new Dimension(BoxDimensions*9, BoxDimensions*11));
        
        setBackground(new Color(225, 225, 225));
        
        Timer timer = new Timer(1000, this);
        timer.setRepeats(true);
        timer.start();
        
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {	
		time += 1;
		repaint();
	}
	
	// Key Listener //
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() >= 48 && e.getKeyCode() < 59) { // Number Input

			dataLogic.writeNumber(cursorCoords[0], cursorCoords[1], e.getKeyCode() - 48, false);
			selectedNo = e.getKeyCode() - 48;
			
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
		}else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			dataLogic.writeNumber(cursorCoords[0], cursorCoords[1], 0, false);
			selectedNo = 0;
		}
		
		repaint();
		
	}
	
	// Mouse Listener //
	@Override
	public void mouseReleased(MouseEvent e) {
		// could be in Clicked but Click is only classed if
		// press and release coords are the same.
		
		// 10 & 30 account for window border offset
		final int boxX = (e.getPoint().x-10) / BoxDimensions;			
		final int boxY = (e.getPoint().y-30) / BoxDimensions;
		
        int buttonSize = (int) (this.getWidth()/11); 
        int spacingSize = (int) (this.getWidth()*0.01);
        
		if (boxX > 8 || boxX < 0 || boxY > 8 || boxY < 0) {
			
			boolean buttonFound = false;
			
			for (int i = 0; i<10; i++) {
				if (boxY == 9 && e.getPoint().x-10 > (buttonSize+spacingSize)*i+spacingSize+2 && e.getPoint().x-10 < (buttonSize+spacingSize)*i+spacingSize+2+buttonSize) {
					selectedNo = i;
					buttonFound = true;
					break;
				}
					
			}
			
			if (!buttonFound) {
				if (e.getPoint().x-10 > spacingSize * 6 && e.getPoint().y-30 > BoxDimensions*10+spacingSize-4 && e.getPoint().x-10 < spacingSize * 6+buttonSize*4 && e.getPoint().y-30 < BoxDimensions*10+spacingSize-4+buttonSize) {
					pencil = true;
				}else if (e.getPoint().x-10 > BoxDimensions*5 + spacingSize * 4 && e.getPoint().y-30 > BoxDimensions*10+spacingSize-4 && e.getPoint().x-10 < BoxDimensions*5 + spacingSize * 4 + buttonSize*4 && e.getPoint().y-30 < BoxDimensions*10+spacingSize-4+buttonSize ) {
					pencil = false;
				}
			}
			
		} else {
			cursorCoords[0] = boxX;
			cursorCoords[1] = boxY;
						
			if (e.getButton() == 1){
				
				dataLogic.writeNumber(boxX, boxY, selectedNo, pencil);
					
			}else if (e.getButton() == 3){
				
				pencil = !pencil;	
				
			}
		
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
        
        // Mouse Extra //
        int buttonSize = (int) (this.getWidth()/11); 
        int spacingSize = (int) (this.getWidth()*0.01);
        for (int i = 0; i < 10; i++) {
            g.setColor(new Color(200, 200, 200));

        	if ((i) == selectedNo ) {
                g.setColor(new Color(120, 120, 120));
        	}
 
        	 g.fillRect(
        	    ((buttonSize+spacingSize)*i)+spacingSize+2,
        	    BoxDimensions*9+spacingSize,
        	    buttonSize,
        	    buttonSize
        	);
        	 
        	 if (i != 0) {
                 g.setColor(new Color(245, 245, 245));
                 g.drawString(String.valueOf(i), (int) (((buttonSize+spacingSize)*i)+(spacingSize*4)), BoxDimensions*9+(int) FontSize);
        	 }
         }
        
        g.setColor((pencil) ? new Color(120, 120, 120) : new Color(205, 205, 205));
        g.fillRect(
    	    spacingSize * 6,
    	    BoxDimensions*10+spacingSize-4,
    	    buttonSize*4,
    	    buttonSize
    	);
        g.setColor(new Color(245, 245, 245));
        g.drawString("Pencil", spacingSize * 14, BoxDimensions*10+spacingSize*8);

        g.setColor((!pencil) ? new Color(120, 120, 120) : new Color(205, 205, 205));
        g.fillRect(
        	BoxDimensions*5 + spacingSize * 4,
    	    BoxDimensions*10+spacingSize-4,
    	    buttonSize*4,
    	    buttonSize
    	);
        g.setColor(new Color(245, 245, 245));
        g.drawString("Pen", (int) BoxDimensions*6 + spacingSize * 5, BoxDimensions*10+spacingSize*8);
        
        g.setColor(new Color(120, 120, 120));
        g.setFont(g.getFont().deriveFont(FontSize*.85f));
        g.drawString("%d:%d".formatted(time/60, time%60), BoxDimensions*4+2, BoxDimensions*10+spacingSize*8);

    }
	
	// Getters
	public static float getFontsize() {
		return FontSize;
	}

	public static int getBoxdimensions() {
		return BoxDimensions;
	}

	
	// Unused Impl
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

	@Override
	public void keyTyped(KeyEvent e) {		
	}	
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
}
