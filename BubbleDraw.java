import javax.swing.JFrame;

public class BubbleDraw extends JFrame {

	public static void main(String[] args) {
		// Set up the frame for bubbledraw app
		
		JFrame frame = new JFrame("Bubble Draw");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new BubblePanel());
		frame.pack();
		frame.setVisible(true);
	}

}
