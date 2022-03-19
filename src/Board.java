import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.*;

public class Board extends JPanel implements KeyListener, MouseListener {
	

	private final static int BoxDimensions = 45;
	
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
		if (e.getButton() == 1){
			System.out.println(e.getPoint());
			
			
			// 10 & 30 account for window extras
			final int boxX = (e.getPoint().x-10) / BoxDimensions;
			System.out.println(boxX);
			
			final int boxY = (e.getPoint().y-30) / BoxDimensions;
			System.out.println(boxY);
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
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

        final int SIZE = 45;
        
        g.setColor(new Color(214, 214, 214));
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if ((row + col) % 2 == 1) {
                    g.fillRect(
                        col * SIZE, 
                        row * SIZE, 
                        SIZE, 
                        SIZE
                    );
                }
            }    
        }
        
        
    }
	
}
