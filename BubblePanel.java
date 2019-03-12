import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class BubblePanel extends JPanel {
	
	private ArrayList<Bubble> bubbleList;
	private int size = 30;
	private Timer timer;
	private final int DELAY = 33; // ms of delay for 30 fps
	
	
	public BubblePanel() {
		bubbleList = new ArrayList<Bubble>();
		
		addMouseListener(new BubbleListener());
		addMouseMotionListener(new BubbleListener());
		addMouseWheelListener(new BubbleListener());
		
		timer = new Timer(DELAY, new BubbleListener());
		
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(600, 400));
		
		timer.start();
	}
	
	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		
		//Draw all the bubbles from bubbleList
		for (Bubble bubble: bubbleList) {
			page.setColor(bubble.color);
			page.fillOval(bubble.x - bubble.size / 2, bubble.y - bubble.size / 2, bubble.size, bubble.size);
		}
		
		
		//Write a number of bubbles on the screen
		page.setColor(Color.GREEN);
		page.drawString("Count: " + bubbleList.size(), 5, 15);
	}
	
	private class BubbleListener implements MouseListener, MouseMotionListener, MouseWheelListener, ActionListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// add to bubbleList my mouse location
			bubbleList.add(new Bubble(arg0.getX(), arg0.getY(), size));
			repaint();
			timer.stop();
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			timer.start();
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			bubbleList.add(new Bubble(arg0.getX(), arg0.getY(), size));
			repaint();
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent arg0) {
			// Change bubble size when mouse wheel is moved
			size += arg0.getWheelRotation();
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// update location of the bubble on the screen
			for (Bubble bubble : bubbleList) {
				bubble.update();
			}
			//repaint the bubble
			repaint();
			
		}
		
	}
	
	
	
	private class Bubble{
		/**
		 * A bubble needs x and y location
		 * and size
		 */
		public int x;
		public int y;
		public int size;
		public Color color;
		public int xSpeed;
		public int ySpeed;
		private final int maxSpeed = 5;
		
		public Bubble(int newX, int newY, int newSize) {
			x = newX;
			y = newY;
			size = newSize;
			color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
			
			xSpeed = (int)(Math.random() * maxSpeed * 2 - maxSpeed);
			ySpeed = (int)(Math.random() * maxSpeed * 2 - maxSpeed);
			
			if(xSpeed == 0 && ySpeed == 0) {
				xSpeed = 1;
				ySpeed = 1;
			}
		}
		
		public void update() {
			x += xSpeed;
			y += ySpeed;
			
			//Collision detection with the edges of the panel
			if (x < 0 || x > getWidth()) {
				xSpeed = xSpeed * (-1);
			}
			if (y < 0 || y > getHeight()) {
				ySpeed = ySpeed * (-1);
			}
		}
	}
}
