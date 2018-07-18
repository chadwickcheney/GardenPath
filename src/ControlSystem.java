import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;

public class ControlSystem implements KeyListener, MouseListener, FocusListener, MouseMotionListener {

	private ControlEvent event;

	private static Map<Integer, String> CODES = initializeCodes();

	private static Map<Integer, String> initializeCodes() {
		Map<Integer, String> map = new HashMap<>();
		map.put(KeyEvent.VK_A, "left");
		map.put(KeyEvent.VK_D, "right");
		map.put(KeyEvent.VK_W, "up");
		map.put(KeyEvent.VK_S, "down");
		map.put(KeyEvent.VK_SHIFT, "laydown");
		map.put(KeyEvent.VK_F3, "guiToggle");
		map.put(KeyEvent.VK_SPACE, "space");
		map.put(MouseEvent.BUTTON1, "rclick");
		map.put(MouseEvent.BUTTON3, "lclick");
		map.put(MouseEvent.MOUSE_ENTERED, "entered");
		map.put(MouseEvent.MOUSE_EXITED, "exited");
		map.put(FocusEvent.FOCUS_GAINED, "focused");
		map.put(FocusEvent.FOCUS_LOST, "unfocused");
		map.put(MouseEvent.MOUSE_MOVED, "moving");
		return map;
	}

	private Map<String, Boolean> keys = new HashMap<>();

	public ControlSystem(ControlEvent event) {
		for (String value : CODES.values()) {
			keys.put(value, false);
		}
		this.event = event;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		String key = CODES.get(e.getKeyCode());
		//System.out.println("Key pressed: "+key);
		if (key != null) {
			event.set(key, true);
			keys.put(key, true);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		String key = CODES.get(e.getKeyCode());
		//System.out.println("key typed: " + key);
		if (key != null) {
			event.setToggle(key, true);
			keys.put(key, true);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		String key = CODES.get(e.getKeyCode());
		if (key != null) {
			event.set(key, false);
			keys.put(key, false);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String key = CODES.get(e.getButton());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		String key = CODES.get(MouseEvent.MOUSE_ENTERED);
		event.setMouse(e.getX(), e.getY());
		System.out.println(key);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		String key = CODES.get(MouseEvent.MOUSE_EXITED);
		System.out.println(key);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent arg0) {
		String key = CODES.get(FocusEvent.FOCUS_GAINED);
		System.out.println(key);
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		String key = CODES.get(FocusEvent.FOCUS_LOST);
		System.out.println(key);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		event.setMouse(e.getX(), e.getY());

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		String key = CODES.get(MouseEvent.MOUSE_MOVED);
		event.setMouse(e.getX(), e.getY());
	}
}
