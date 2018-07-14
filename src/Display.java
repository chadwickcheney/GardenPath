import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Display {
	// VARIABLES
	// This

	// LISTS

	// INSTANCES
	private Main main;

	public Display(Main main) {
		this.main = main;
	}

	public void WindowInit() {
		JFrame frame = new JFrame(main.title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(main.Width, main.Height));
		frame.setMaximumSize(new Dimension(main.Width, main.Height));
		frame.setMinimumSize(new Dimension(main.Width, main.Height));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setBounds(-1920, -200, -100, -100);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.add(main);
		frame.setFocusable(true);
		frame.pack();
		frame.addKeyListener(main.controlSystem);
		main.addKeyListener(main.controlSystem);
		main.addMouseListener(main.controlSystem);
		main.addFocusListener(main.controlSystem);
		main.addMouseMotionListener(main.controlSystem);

		//hideCursor(frame);
	}

	private void hideCursor(JFrame frame) {
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		frame.getContentPane().setCursor(blankCursor);
	}
}
