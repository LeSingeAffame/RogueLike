package roguelike.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import roguelike.entities.Creature;

public class EquipmentScreen implements Screen {
	private PlayScreen playScreen;
	private Creature player;
	
	public EquipmentScreen(Creature player, PlayScreen playScreen) {
		this.playScreen = playScreen;
		this.player = player;
	}

	@Override
	public void displayOutput(AsciiPanel terminal) {
		int begin = 5;
		if(player.rightHand() != null)
			terminal.writeCenter("<Right Hand> - " + player.rightHand().name(), begin);
		else
			terminal.writeCenter("<Right Hand> - empty", begin);
		
		if(player.leftHand() != null)
			terminal.writeCenter("<Left Hand> - " + player.leftHand().name(), begin + 1);
		else
			terminal.writeCenter("<Left Hand> - empty", begin + 1);
		
		terminal.writeCenter("Press X to go back to the game", 25);
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch (key.getKeyCode()){
			case KeyEvent.VK_X: return this.playScreen;
	    }
		return this;
	}

}
