package tyler.bounce;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3827999055406046215L;

	public Window(int width, int height, String title, Core sys) {
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(sys);
		frame.setVisible(true);
		sys.start();
	}
}

