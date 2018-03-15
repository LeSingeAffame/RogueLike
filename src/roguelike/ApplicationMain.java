package roguelike;

import javax.swing.JFrame;
import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/* Screens */
import roguelike.screens.Screen;
import roguelike.screens.StartScreen;

import roguelike.sound.*;

public class ApplicationMain extends JFrame implements KeyListener {
	public static int panelWidth;
	public static int panelHeight;
	
    private static final long serialVersionUID = 1060623638149583738L;

    private AsciiPanel terminal;
    private Screen screen;

    public ApplicationMain(){
        super();
        
        panelWidth = 90;
        panelHeight = 50;
        
        terminal = new AsciiPanel(panelWidth, panelHeight);
        add(terminal);
        pack();
        screen = new StartScreen();
        addKeyListener(this);
        
        repaint();
    }

    public void repaint(){
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

    public void keyPressed(KeyEvent e) {
        screen = screen.respondToUserInput(e);
        repaint();
    }

    public void keyReleased(KeyEvent e) { }

    public void keyTyped(KeyEvent e) { }

    public static void main(String[] args) {
        ApplicationMain app = new ApplicationMain();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}