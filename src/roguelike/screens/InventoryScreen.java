package roguelike.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import roguelike.entities.Creature;
import roguelike.entities.Item;

public class InventoryScreen implements Screen {
	protected Creature player;
	private PlayScreen playScreen;
	private String letters = "abcdefghijklmnopqrst";
	
	public InventoryScreen(Creature player, PlayScreen playScreen) {
		this.player = player;
		this.playScreen = playScreen;
	}

	@Override
	public void displayOutput(AsciiPanel terminal) {
		int i = 5;
		for(int j = 0; j < player.inventory().size(); j++) {
			int k = i + j;
			if(player.inventory().get(j) != null)
				terminal.writeCenter("<" + letters.charAt(j) + "> - " + player.inventory().get(j).name(), k);
			else
				terminal.writeCenter("<" + letters.charAt(j) + "> - " + "Nothing", k);
		}
		terminal.writeCenter("Press X to go back to the game", i + player.inventory().size() + 5);
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		if(key.getKeyCode() >= 0x41 && key.getKeyCode() <= 0x54) {
			int index = key.getKeyCode() - 0x41;
			if(player.inventory().get(index) != null)
				player.inventory().get(index).use(player);
		}
		
		switch (key.getKeyCode()){
			case KeyEvent.VK_X: return this.playScreen;
	    }
		
		return this;
	}

}
